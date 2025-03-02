package model;

import config.LottoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != LottoConstants.LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        for (int number : numbers) {
            if (number < LottoConstants.MIN_NUMBER.getValue() || number > LottoConstants.MAX_NUMBER.getValue()) {
                throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
            }
        }
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
