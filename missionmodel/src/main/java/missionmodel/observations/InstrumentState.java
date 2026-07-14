package missionmodel.observations;

public enum InstrumentState {
    ON("ON"),
    OFF("OFF");

    private final String state;
    InstrumentState(String state) { this.state = state; }
    public String getState() { return this.state; }
}
