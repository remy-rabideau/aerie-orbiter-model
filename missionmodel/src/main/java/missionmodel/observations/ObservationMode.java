package missionmodel.observations;

/**
 * Science observing modes for the instrument, mirroring {@code RadarDataCollectionMode}.
 * Each mode carries a nominal data rate (Mbps) used for the {@code instrument.dataRate}
 * indicator resource. The three active modes correspond to the ACROSS observation types
 * IMAGING, TIMING, and SPECTROSCOPY (SLEW is a pointing maneuver, handled by the telescope).
 */
public enum ObservationMode {
  OFF(0.0),           // Mbps
  IMAGING(4.0),
  TIMING(0.5),
  SPECTROSCOPY(2.0);

  private final double dataRate;

  ObservationMode(double dataRate) {
    this.dataRate = dataRate;
  }

  public double getDataRate() {
    return dataRate;
  }
}
