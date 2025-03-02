package controller;

import model.Lotto;
import model.LottoMachine;
import model.LottoResultChecker;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachineController {
    public void run() {
        int purchaseAmount = InputView.getPurchaseAmount();
        int totalTicketCount = LottoMachine.getTicketCount(purchaseAmount);

        int manualTicketCount = InputView.getManualTicketCount();
        int autoTicketCount = totalTicketCount - manualTicketCount;

        List<List<Integer>> manualNumbers = InputView.getManualLottoNumbers(manualTicketCount);
        List<Lotto> tickets = LottoMachine.generateLottos(manualNumbers, purchaseAmount);

        List<String> formattedTickets = tickets.stream()
                .map(Lotto::toString)
                .collect(Collectors.toList());

        ResultView.printOrderTickets(manualTicketCount, autoTicketCount);
        ResultView.printTickets(formattedTickets);

        List<Integer> winningNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber();

        Map<String, Integer> results = LottoResultChecker.checkWinningResults(tickets, winningNumbers, bonusNumber);
        int totalPrize = results.get("totalPrize");
        double profitRate = LottoResultChecker.calculateProfitRate(totalPrize, purchaseAmount);

        ResultView.printWinningStatistics(results, profitRate);
    }
}
