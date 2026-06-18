package missionmodel.observations;

public enum BandpassType {
    ENERGY("ENERGY"),
    WAVELENGTH("WAVELENGTH"),
    FREQUENCY("FREQUENCY"),
    NONE("NONE");

    private final String bandpassType;

    BandpassType(String bandpassType) {
        this.bandpassType = bandpassType;
    }

    public String getBandpassType() { return this.bandpassType; }

}
