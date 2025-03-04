package model;

import config.WinningRank;
import java.util.*;

public class LottoResultChecker {

    public static Map<String, Integer> checkWinningResults(List<Lotto> purchasedLottos, List<Integer> winningNumbers, int bonusNumber) {
        Map<String, Integer> result = initializeResultMap();
        int totalPrize = 0;
        Set<Integer> winningSet = new HashSet<>(winningNumbers);

        for (Lotto lotto : purchasedLottos) {
            totalPrize += updateResults(result, lotto, winningSet, bonusNumber);
        }

        result.put("totalPrize", totalPrize);
        return result;
    }

    private static Map<String, Integer> initializeResultMap() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (WinningRank rank : WinningRank.values()) {
            result.put(rank.getDescription(), 0);
        }
        return result;
    }

    private static int updateResults(Map<String, Integer> result, Lotto lotto, Set<Integer> winningSet, int bonusNumber) {
        int matchCount = (int) lotto.getNumbers().stream().filter(winningSet::contains).count();
        boolean hasBonus = lotto.getNumbers().contains(bonusNumber);

        Optional<WinningRank> rank = WinningRank.findByMatchCount(matchCount, hasBonus);
        if (rank.isEmpty()) {
            return 0;
        }

        WinningRank winningRank = rank.get();
        String key = winningRank.getDescription();
        result.put(key, result.getOrDefault(key, 0) + 1);

        return winningRank.getPrize();
    }

    public static double calculateProfitRate(int totalPrize, int purchaseAmount) {
        if (purchaseAmount == 0) {
            return 0.0;
        }
        return (double) totalPrize / purchaseAmount;
    }
}
