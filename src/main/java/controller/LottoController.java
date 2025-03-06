package controller;

import java.util.stream.Collectors;
import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void run() {
        int purchaseAmount;
        int ticketCount;

        do {
            purchaseAmount = InputView.getPurchaseAmount();
            ticketCount = Lotto.getTicketCount(purchaseAmount);
            if (ticketCount == -1) {
                ResultView.printInvalidAmountMessage();
            }
        } while (ticketCount == -1);

        List<Lotto> tickets = Lotto.generateLottoTickets(ticketCount);

        ResultView.printOrderTickets(ticketCount);
        ResultView.printTickets(formatTickets(tickets));
    }

    private List<String> formatTickets(List<Lotto> tickets) {
        return tickets.stream()
                .map(lotto -> lotto.getNumbers()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ", "[", "]")))
                .toList();
    }
}
