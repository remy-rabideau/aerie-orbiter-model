package missionmodel.observations;

public enum InstrumentName {

    // NOTE: These must match your spacecraft's instrument's ACROSS-registered short name
    Xtend("Xtend", "0c94d3eb-fcad-4918-8e6b-4b2ce5a00d79"),
    Resolve("Resolve", "8d9a6fe9-330b-4ba6-83d3-506a1609b199");

    private final String instrumentName;
    private final String instrumentUUID;

    InstrumentName(String name, String uuid) { this.instrumentName = name; this.instrumentUUID = uuid; }
    public String getInstrumentName() { return this.instrumentName; }
    public String getInstrumentUUID() { return this.instrumentUUID; }

}
