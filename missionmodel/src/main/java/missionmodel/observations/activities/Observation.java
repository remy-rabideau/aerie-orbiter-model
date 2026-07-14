package missionmodel.observations.activities;

import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.DiscreteEffects;
import gov.nasa.jpl.aerie.merlin.framework.annotations.ActivityType;
import gov.nasa.jpl.aerie.merlin.framework.annotations.Export.Parameter;
import gov.nasa.jpl.aerie.merlin.protocol.types.Duration;
import missionmodel.Mission;
import missionmodel.observations.*;
import missionmodel.power.pel.Imager_State;

import static gov.nasa.jpl.aerie.merlin.protocol.types.Duration.SECONDS;

/**
 * ACROSS observation.
 * Collects data for the exposure duration, then powers down.
 *
 * Maps to ACROSS ObservationType.IMAGING. Parameters map to ACROSS fields:
 *   targetName               -> object_name
 *   exposure                 -> exposure_time
 *   description              -> description
 */
@ActivityType("Observation")
public class Observation {

  @Parameter
  public InstrumentName instrument;

  @Parameter
  public ObservationType type;

  @Parameter
  public String targetName = "UNKNOWN";

  @Parameter
  public String description = "";

  @Parameter
  public Duration exposure = Duration.duration(60, SECONDS);

  @Parameter
  public int dataBin = 0;          // which onboard bin to store the image in

  @Parameter
  public double dataRateBps = 2e6;

  @ActivityType.EffectModel
  public void run(Mission model) {

    InstrumentModel instrumentModel = model.instruments.get(instrument.getInstrumentUUID());
    if (instrumentModel == null) {
      throw new RuntimeException("Unknown instrument: " + instrument.getInstrumentName()
              + ". Valid instruments: " + model.instruments.keySet());
    }

    TelescopeModel telescope = model.telescopeModel;

    // 1. Record that we're pointing. Pointing position is already set after slewing
    DiscreteEffects.set(model.telescopeModel.pointingState, PointingState.TRACKING);

    // 2. Power the instrument (raises PEL load -> both batteries drain) and set science mode.
    DiscreteEffects.set(model.pel.imagerState, Imager_State.ON);
    DiscreteEffects.set(instrumentModel.instrumentState, InstrumentState.ON);

    // 3. Mark telescope as actively observing.
    DiscreteEffects.set(telescope.pointingState, PointingState.OBSERVING);

    // 4. Accrue data into the onboard bin over the exposure.
    //    receive(rate, duration) ramps the bin at `rate` bps for `duration`, and
    //    blocks the activity for that duration -- so it provides the exposure delay too.
    model.data.getOnboardBin(dataBin).receive(dataRateBps, exposure);

    // 5. Power down and return the instrument to idle.
    DiscreteEffects.set(model.pel.imagerState, Imager_State.OFF);
    DiscreteEffects.set(instrumentModel.instrumentState, InstrumentState.OFF);
    DiscreteEffects.set(telescope.pointingState, PointingState.IDLE);

  }

}
