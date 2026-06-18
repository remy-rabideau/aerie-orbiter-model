package missionmodel.observations;

public record InstrumentConfig(
        String name,
        String uuid,
        TelescopeModel telescope,
        BandpassType bandpassType,
        String bandpassUnit,
        Double bandMin,
        Double bandMax,
        Double tResolution,
        Double emResPower,
        Double maxDataRate
) {
}
