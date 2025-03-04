package view;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static void printInvalidAmountMessage() {
        System.out.println("올바른 금액을 입력해 주세요! (1000원 단위로 입력)");
    }

    public static void printInvalidManualTicketMessage() {
        System.out.println("수동 구매 개수가 전체 구매 개수를 초과할 수 없습니다.");
    }

    public static void printInvalidManualCountMessage() {
        System.out.println("올바른 개수를 입력하세요! (0 이상의 숫자)");
    }

    public static void printInvalidLottoNumbersMessage() {
        System.out.println("1~45 사이의 중복되지 않은 6개의 숫자를 입력하세요.");
    }

    public static void printInvalidWinningNumbersMessage() {
        System.out.println("1~45 사이의 중복되지 않은 6개의 당첨 번호를 입력하세요.");
    }

    public static void printInvalidBonusNumberMessage() {
        System.out.println("1~45 사이의 보너스 번호를 입력하세요.");
    }

    public static void printOrderTickets(int manualCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, autoCount);
    }

    public static void printTickets(List<String> tickets) {
        tickets.forEach(System.out::println);
    }

    public static void printWinningStatistics(Map<String, Integer> results, double profitRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            if (!entry.getKey().equals("totalPrize")) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + "개");
            }
        }

        String profitStatus = "손해";
        if (profitRate >= 1) {
            profitStatus = "이득";
        }

        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                profitRate, profitStatus);
    }
}
