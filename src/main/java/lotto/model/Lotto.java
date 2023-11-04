package lotto.model;

import java.util.Arrays;
import java.util.List;
import lotto.config.AppConfig;
import lotto.model.win.Rank;

public class Lotto {
    private final List<Integer> numbers;
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validate(distinct(numbers));
        validate(validRange(numbers));
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != AppConfig.LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    private List<Integer> distinct(List<Integer> numbers) {
        return numbers.stream().distinct().toList();
    }

    private List<Integer> validRange(List<Integer> numbers) {
        return numbers.stream().filter(integer -> integer <= AppConfig.LOTTO_NUMBER_MAX_RANGE)
                .filter(integer -> integer >= AppConfig.LOTTO_NUMBER_MIN_RANGE)
                .toList();
    }

    public Rank checkResult(Lotto targetLotto, int bonus) {
        List<Integer> target = targetLotto.numbers;
        int count = checkWithTarget(target);
        boolean canBeSecond = checkBonusIfUsable(count, bonus);

        return getWinResult(count, canBeSecond);
    }

    private int checkWithTarget(List<Integer> target) {
        return (int) numbers.stream()
                .filter(num -> target.contains(num))
                .count();
    }

    private boolean checkBonusIfUsable(int count, int bonus) {
        if(count == Rank.THIRD.matchedCount) {
            return numbers.contains(bonus);
        }
        return false;
    }

    private Rank getWinResult(int count, boolean canBeSecond) {
        return Arrays.stream(Rank.values())
                .filter(winResult -> winResult.matchedCount == count)
                .filter(winResult -> winResult.bonus == canBeSecond)
                .findAny()
                .orElse(Rank.FAIL);
    }

    public boolean contains(int num){
        return numbers.contains(num);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
