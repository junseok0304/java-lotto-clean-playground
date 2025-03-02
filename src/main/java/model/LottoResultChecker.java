package model;

import java.util.*;

public class LottoResultChecker {
    private static final Map<Integer, Integer> WINNING_PRIZE_MAP = Map.of(
            3, 5000,
            4, 50000,
            5, 1500000,
            6, 2000000000
    );

    private static final int BONUS_MATCH_PRIZE = 30000000;

    public static Map<String, Integer> checkWinningResults(List<Lotto> purchasedLottos, List<Integer> winningNumbers, int bonusNumber) {
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put("3개 일치 (5000원)", 0);
        result.put("4개 일치 (50000원)", 0);
        result.put("5개 일치 (1500000원)", 0);
        result.put("5개 일치, 보너스 볼 일치(30000000원)", 0);
        result.put("6개 일치 (2000000000원)", 0);

        int totalPrize = 0;
        Set<Integer> winningSet = new HashSet<>(winningNumbers);

        for (Lotto lotto : purchasedLottos) {
            Set<Integer> lottoNumbers = new HashSet<>(lotto.getNumbers());
            int matchCount = (int) lottoNumbers.stream().filter(winningSet::contains).count();
            boolean hasBonus = lottoNumbers.contains(bonusNumber);

            if (matchCount == 6) {
                totalPrize += WINNING_PRIZE_MAP.get(6);
                result.put("6개 일치 (2000000000원)", result.get("6개 일치 (2000000000원)") + 1);
            } else if (matchCount == 5 && hasBonus) {
                totalPrize += BONUS_MATCH_PRIZE;
                result.put("5개 일치, 보너스 볼 일치(30000000원)", result.get("5개 일치, 보너스 볼 일치(30000000원)") + 1);
            } else if (WINNING_PRIZE_MAP.containsKey(matchCount)) {
                totalPrize += WINNING_PRIZE_MAP.get(matchCount);
                String key = matchCount + "개 일치 (" + WINNING_PRIZE_MAP.get(matchCount) + "원)";
                result.put(key, result.getOrDefault(key, 0) + 1);
            }
        }

        result.put("totalPrize", totalPrize);
        return result;
    }

    public static double calculateProfitRate(int totalPrize, int purchaseAmount) {
        return purchaseAmount == 0 ? 0 : (double) totalPrize / purchaseAmount;
    }
}
