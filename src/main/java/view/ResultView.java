package view;

import java.util.List;

public class ResultView {

    public static void printInvalidAmountMessage() {
        System.out.println("1000원 단위로 입력해주세요.");
    }

    public static void printOrderTickets(int ticketCount) {
        System.out.println();
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    public static void printTickets(List<String> formattedTickets) {
        for (String ticket : formattedTickets) {
            System.out.println(ticket);
        }
    }
}
