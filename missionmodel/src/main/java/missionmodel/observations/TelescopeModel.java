package missionmodel.observations;

import gov.nasa.jpl.aerie.contrib.serialization.mappers.DoubleValueMapper;
import gov.nasa.jpl.aerie.contrib.serialization.mappers.EnumValueMapper;
import gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.Registrar;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete;

import static gov.nasa.jpl.aerie.contrib.metadata.UnitRegistrar.withUnit;
import static gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource.resource;
import static gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete.discrete;

/**
 * Models where the telescope is pointed and whether it is slewing or tracking.
 *
 * Follows the same shape as {@code RadarModel}: plain mutable state resources that the
 * activities drive. The {@code Slew} activity moves the pointing; the observation
 * activities record the pointing they observe at, so the timeline shows the boresight
 * RA/Dec alongside the battery and data resources.
 */
public class TelescopeModel {

  /** Whether the telescope is idle, slewing to a new target, or tracking one. */
  public MutableResource<Discrete<PointingState>> pointingState;

  /** Current boresight right ascension (degrees, 0–360). */
  public MutableResource<Discrete<Double>> pointingRa;

  /** Current boresight declination (degrees, -90–90). */
  public MutableResource<Discrete<Double>> pointingDec;

  /** Roll (position angle) about the boresight axis (degrees, -180–180). */
  public MutableResource<Discrete<Double>> pointingAngle;

  public TelescopeModel(Registrar registrar) {
    pointingState = resource(discrete(PointingState.IDLE));
    registrar.discrete("telescope.pointingState", pointingState,
        new EnumValueMapper<>(PointingState.class));

    pointingRa = resource(discrete(0.0));
    registrar.discrete("telescope.pointingRa", pointingRa,
        withUnit("deg", new DoubleValueMapper()));

    pointingDec = resource(discrete(0.0));
    registrar.discrete("telescope.pointingDec", pointingDec,
        withUnit("deg", new DoubleValueMapper()));

    pointingAngle = resource(discrete(0.0));
    registrar.discrete("telescope.pointingAngle", pointingAngle,
            withUnit("deg", new DoubleValueMapper()));
  }
}
