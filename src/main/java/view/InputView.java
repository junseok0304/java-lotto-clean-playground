package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                int amount = Integer.parseInt(scanner.nextLine().trim());
                if (amount <= 0) {
                    throw new IllegalArgumentException("금액은 0보다 커야 합니다.");
                }
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println("유효한 금액을 입력하세요. (1000원 단위의 숫자)");
            }
        }
    }

    public static int getManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        while (true) {
            try {
                int count = Integer.parseInt(scanner.nextLine().trim());
                if (count < 0) {
                    throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
                }
                return count;
            } catch (IllegalArgumentException e) {
                System.out.println("유효한 개수를 입력하세요. (0 이상의 숫자)");
            }
        }
    }

    public static List<List<Integer>> getManualLottoNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            while (true) {
                try {
                    System.out.printf("로또 번호 %d/%d 입력: ", i + 1, count);
                    String input = scanner.nextLine().trim();
                    List<Integer> numbers = parseLottoNumbers(input);
                    manualNumbers.add(numbers);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("1~45 사이의 중복되지 않은 6개의 숫자를 입력하세요.");
                }
            }
        }
        return manualNumbers;
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                return parseLottoNumbers(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("1~45 사이의 중복되지 않은 6개의 숫자를 입력하세요.");
            }
        }
    }

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        while (true) {
            try {
                int bonus = Integer.parseInt(scanner.nextLine().trim());
                if (bonus < 1 || bonus > 45) {
                    throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다.");
                }
                return bonus;
            } catch (IllegalArgumentException e) {
                System.out.println("1~45 사이의 보너스 번호를 입력하세요.");
            }
        }
    }

    private static List<Integer> parseLottoNumbers(String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }

        if (numbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }

        return new ArrayList<>(uniqueNumbers);
    }
}
