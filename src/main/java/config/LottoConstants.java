package config;

public enum LottoConstants {
    MIN_NUMBER(1),
    MAX_NUMBER(45),
    LOTTO_SIZE(6),
    LOTTO_PRICE(1000);

    private final int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
