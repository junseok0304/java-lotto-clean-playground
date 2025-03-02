package view;

import java.util.List;
import java.util.Map;

public class ResultView {
    
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

        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                profitRate, profitRate >= 1 ? "이득" : "손해");
    }
}
