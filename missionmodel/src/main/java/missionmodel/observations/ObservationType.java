package missionmodel.observations;

public enum ObservationType {

    IMAGING("Imaging"),
    TIMING("Timing"),
    SPECTROSCOPY("Spectroscopy"),
    SLEW("Slew");

    private final String type;
    ObservationType(String type) { this.type = type; }
    public String getType() { return type; }

}
