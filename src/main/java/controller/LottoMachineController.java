package controller;

import model.Lotto;
import model.LottoMachine;
import model.LottoResultChecker;
import config.LottoValidator;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachineController {
    public void run() {
        int purchaseAmount;

        while (true) {
            try {
                purchaseAmount = InputView.getPurchaseAmount();
                LottoValidator.validatePurchaseAmount(purchaseAmount);
                break;
            } catch (IllegalArgumentException e) {
                ResultView.printInvalidAmountMessage();
            }
        }

        int totalTicketCount = LottoMachine.getTicketCount(purchaseAmount);

        int manualTicketCount;
        while (true) {
            try {
                manualTicketCount = InputView.getManualTicketCount();
                LottoValidator.validateManualTicketCount(manualTicketCount);
                if (manualTicketCount > totalTicketCount) {
                    ResultView.printInvalidManualTicketMessage();
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                ResultView.printInvalidManualCountMessage();
            }
        }

        int autoTicketCount = totalTicketCount - manualTicketCount;

        List<List<Integer>> manualNumbers;
        while (true) {
            try {
                List<String> inputNumbers = InputView.getManualLottoNumbers(manualTicketCount);
                manualNumbers = inputNumbers.stream()
                        .map(LottoValidator::validateAndParseLottoNumbers)
                        .collect(Collectors.toList());
                break;
            } catch (IllegalArgumentException e) {
                ResultView.printInvalidLottoNumbersMessage();
            }
        }

        List<Lotto> tickets = LottoMachine.generateLottos(manualNumbers, purchaseAmount);
        List<String> formattedTickets = tickets.stream().map(Lotto::toString).collect(Collectors.toList());

        ResultView.printOrderTickets(manualTicketCount, autoTicketCount);
        ResultView.printTickets(formattedTickets);

        List<Integer> winningNumbers;
        while (true) {
            try {
                String input = InputView.getWinningNumbers();
                winningNumbers = LottoValidator.validateAndParseLottoNumbers(input);
                break;
            } catch (IllegalArgumentException e) {
                ResultView.printInvalidWinningNumbersMessage();
            }
        }

        int bonusNumber;
        while (true) {
            try {
                bonusNumber = InputView.getBonusNumber();
                LottoValidator.validateBonusNumber(bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                ResultView.printInvalidBonusNumberMessage();
            }
        }

        Map<String, Integer> results = LottoResultChecker.checkWinningResults(tickets, winningNumbers, bonusNumber);
        int totalPrize = results.get("totalPrize");
        double profitRate = LottoResultChecker.calculateProfitRate(totalPrize, purchaseAmount);

        ResultView.printWinningStatistics(results, profitRate);
    }
}
