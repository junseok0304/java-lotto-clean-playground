package model;

import config.LottoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int LOTTO_PRICE = LottoConstants.LOTTO_PRICE.getValue();
    private static final List<Integer> LOTTO_NUMBER_POOL =
            IntStream.rangeClosed(LottoConstants.MIN_NUMBER.getValue(), LottoConstants.MAX_NUMBER.getValue())
                    .boxed()
                    .toList();

    public static int getTicketCount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
        return purchaseAmount / LOTTO_PRICE;
    }

    private static Lotto generateAutoLotto() {
        List<Integer> shuffledNumbers = new ArrayList<>(LOTTO_NUMBER_POOL);
        Collections.shuffle(shuffledNumbers);
        return new Lotto(shuffledNumbers.subList(0, LottoConstants.LOTTO_SIZE.getValue()));
    }

    public static List<Lotto> generateLottos(List<List<Integer>> manualNumbers, int purchaseAmount) {
        int totalTicketCount = getTicketCount(purchaseAmount);
        int manualCount = manualNumbers.size();
        int autoCount = totalTicketCount - manualCount;

        if (manualCount > totalTicketCount) {
            throw new IllegalArgumentException("구매 가능한 로또 개수를 초과했습니다.");
        }

        List<Lotto> lottos = new ArrayList<>();
        manualNumbers.forEach(numbers -> lottos.add(new Lotto(numbers)));
        for (int i = 0; i < autoCount; i++) {
            lottos.add(generateAutoLotto());
        }

        return lottos;
    }
}
