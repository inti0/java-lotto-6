package lotto.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.lotto.Lotto;
import lotto.lotto.LottoBuyer;
import lotto.lotto.win.WinResult;
import lotto.view.LottoView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *     - 1등: 6개 번호 일치 / 2,000,000,000원
 *     - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
 *     - 3등: 5개 번호 일치 / 1,500,000원
 *     - 4등: 4개 번호 일치 / 50,000원
 *     - 5등: 3개 번호 일치 / 5,000원
 */
public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void 계산기_테스트() {
        //given
        Map<WinResult, Integer> map = new HashMap<>();
        map.put(WinResult.FIFTH, 1);
        int pay = 1000;

        double value = calculator.calculateRateOfReturn(map, pay);

        assertThat(value).isEqualTo((5000-pay)*100/pay);
    }
}
