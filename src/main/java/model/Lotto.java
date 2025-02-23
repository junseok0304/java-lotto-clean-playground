package model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_CREATE_SIZE = 6;
    public static final int LOTTO_PRICE = 1000;
    public static final List<Integer> LOTTO_NUMBER_POOL =
            IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
                    .boxed()
                    .collect(Collectors.toList());

    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = createLottoNumbers();
    }

    public static int getTicketCount(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            return -1;
        }
        return purchaseAmount / LOTTO_PRICE;
    }

    private List<Integer> createLottoNumbers() {
        List<Integer> shuffledNumbers = new ArrayList<>(LOTTO_NUMBER_POOL);
        Collections.shuffle(shuffledNumbers);
        return shuffledNumbers.subList(0, LOTTO_CREATE_SIZE);
    }

    public static List<Lotto> generateLottoTickets(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Lotto());
        }
        return tickets;
    }

    public List<Integer> getNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }
}
