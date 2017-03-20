package pl.kokokoko.domain;

public enum TownFuelPrice {

    WARSAW(0.03f),
    GDANSK(0.01f),
    KATOWICE(0.02f);

    private final Float value;

    TownFuelPrice(Float value) {
        this.value = value;
    }

    public Float value() {
        return value;
    }
}
