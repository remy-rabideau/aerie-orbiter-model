package missionmodel.observations.activities;

import static gov.nasa.jpl.aerie.merlin.framework.ModelActions.delay;
import static gov.nasa.jpl.aerie.merlin.protocol.types.Duration.SECONDS;

import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.DiscreteEffects;
import gov.nasa.jpl.aerie.merlin.framework.annotations.ActivityType;
import gov.nasa.jpl.aerie.merlin.framework.annotations.Export.Parameter;
import gov.nasa.jpl.aerie.merlin.framework.annotations.Export.Validation;
import gov.nasa.jpl.aerie.merlin.protocol.types.Duration;
import missionmodel.Mission;
import missionmodel.observations.PointingState;

/**
 * SLEW maneuver -- repoints the telescope to a new target. Collects no light, so no
 * bandpass or data; just changes the pointing over the slew duration.
 *
 * Maps to ACROSS ObservationType.SLEW. Parameters map to ACROSS fields:
 *   targetName    -> object_name
 *   ra, dec       -> pointing_position (the destination)
 *   pointingAngle -> pointing_angle (roll about boresight)
 *   description   -> description
 */
@ActivityType("Slew")
public class Slew {

  @Parameter
  public double ra = 0.0;          // degrees, 0–360

  @Parameter
  public double dec = 0.0;         // degrees, -90–90

  @Parameter
  public double pointingAngle = 0.0; // roll about boresight, degrees

  @Parameter
  public Duration duration = Duration.duration(60, SECONDS);

  @Validation("RA must be within [0, 360] degrees")
  @Validation.Subject("ra")
  public boolean validateRa() {
    return ra >= 0.0 && ra <= 360.0;
  }

  @Validation("Dec must be within [-90, 90] degrees")
  @Validation.Subject("dec")
  public boolean validateDec() {
    return dec >= -90.0 && dec <= 90.0;
  }

  @ActivityType.EffectModel
  public void run(Mission model) {

    // Begin the maneuver.
    DiscreteEffects.set(model.telescopeModel.pointingState, PointingState.SLEWING);

    // Time spent moving to the new attitude.
    delay(duration);

    // Arrive on target and begin tracking.
    DiscreteEffects.set(model.telescopeModel.pointingRa, ra);
    DiscreteEffects.set(model.telescopeModel.pointingDec, dec);
    DiscreteEffects.set(model.telescopeModel.pointingAngle, pointingAngle);
    DiscreteEffects.set(model.telescopeModel.pointingState, PointingState.TRACKING);

  }
}
