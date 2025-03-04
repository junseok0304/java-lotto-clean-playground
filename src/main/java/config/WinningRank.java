package config;

import java.util.Arrays;
import java.util.Optional;

public enum WinningRank {
    MATCH_3(3, 5000, "3개 일치 (5000원)"),
    MATCH_4(4, 50000, "4개 일치 (50000원)"),
    MATCH_5(5, 1500000, "5개 일치 (1500000원)"),
    MATCH_5_BONUS(5, 30000000, "5개 일치, 보너스 볼 일치(30000000원)"),
    MATCH_6(6, 2000000000, "6개 일치 (2000000000원)"),
    NO_MATCH(0, 0, "꽝");

    private final int matchCount;
    private final int prize;
    private final String description;

    WinningRank(int matchCount, int prize, String description) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.description = description;
    }

    public int getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<WinningRank> findByMatchCount(int matchCount, boolean hasBonus) {
        if (matchCount == 5 && hasBonus) {
            return Optional.of(MATCH_5_BONUS);
        }
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst();
    }
}
