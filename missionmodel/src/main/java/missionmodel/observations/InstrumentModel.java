package missionmodel.observations;

import gov.nasa.jpl.aerie.contrib.serialization.mappers.DoubleValueMapper;
import gov.nasa.jpl.aerie.contrib.serialization.mappers.EnumValueMapper;
import gov.nasa.jpl.aerie.contrib.serialization.mappers.StringValueMapper;
import gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.Registrar;
import gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete;
import static gov.nasa.jpl.aerie.contrib.metadata.UnitRegistrar.withUnit;
import static gov.nasa.jpl.aerie.contrib.streamline.core.MutableResource.resource;
import static gov.nasa.jpl.aerie.contrib.streamline.modeling.discrete.Discrete.discrete;

/**
 * Models the science instrument's current observing mode.
 *
 * Mirrors {@code RadarModel}: a single mutable state resource ({@link #observationMode})
 * drives a derived data-rate resource ({@link #instrumentDataRate}). The observation
 * activities flip the mode at the start/end of an observation; the rate indicator
 * follows automatically.
 *
 * Power for the instrument is handled separately via the PEL's {@code imagerState}
 * (see the observation activities), exactly as the radar activities use {@code radarState}.
 */
public class InstrumentModel {

  public MutableResource<Discrete<InstrumentState>> instrumentState;

  public final String name;
  public final String uuid;
  public final TelescopeModel parentTelescope;
  public final BandpassType bandpassType;
  public final String bandpassUnit;
  public final Double bandMin;
  public final Double bandMax;
  public final Double tResolution;
  public final Double emResPower;
  public final Double maxDataRate;

  public InstrumentModel(Registrar registrar, InstrumentConfig config) {

    this.name = config.name();
    this.uuid = config.uuid();

    this.instrumentState = resource(discrete(InstrumentState.OFF));
    registrar.discrete("instrument." + name + ".state", instrumentState,
        new EnumValueMapper<>(InstrumentState.class));

    this.parentTelescope = config.telescope();
    this.bandpassType    = config.bandpassType();
    this.bandpassUnit    = config.bandpassUnit();
    this.bandMin         = config.bandMin();
    this.bandMax         = config.bandMax();
    this.tResolution     = config.tResolution();
    this.emResPower      = config.emResPower();
    this.maxDataRate     = config.maxDataRate();

    registrar.discrete("instrument." + name + ".bandpassType",
            resource(discrete(bandpassType.getBandpassType())), new StringValueMapper());

    registrar.discrete("instrument." + name + ".bandpassUnit",
            resource(discrete(bandpassUnit)), new StringValueMapper());

    registrar.discrete("instrument." + name + ".bandMin",
            resource(discrete(bandMin)), withUnit(bandpassUnit, new DoubleValueMapper()));

    registrar.discrete("instrument." + name + ".bandMax",
            resource(discrete(bandMax)), withUnit(bandpassUnit, new DoubleValueMapper()));

    registrar.discrete("instrument." + name + ".tResolution",
            resource(discrete(tResolution)), withUnit("secs", new DoubleValueMapper()));

    registrar.discrete("instrument." + name + ".emResPower",  // spectral resolving power is unitless
            resource(discrete(emResPower)), withUnit("", new DoubleValueMapper()));

    registrar.discrete("instrument." + name + ".maxDataRate",
            resource(discrete(maxDataRate)), withUnit("bps", new DoubleValueMapper()));

  }
}
