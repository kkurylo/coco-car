package pl.kokokoko.domain;

public enum AverageFuelPrice {

    PETROL(1.74f),
    DIESEL(2.30f),
    LPG(4.62f);

    private final Float value;

    AverageFuelPrice(Float v) {
        value = v;
    }

    public Float value() {
        return value;
    }
}
