package study;

import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListOfTest {

    @Test
    @DisplayName("ListOf의 불변성 실험. 값을 추가할수도 변경할수도 없다. -> of()의 반환값이 ImmutableCollections")
    void ListOf() {
        List<Integer> list = List.of(1, 3, 5, 7, 11, 15);

        assertThatThrownBy(() ->
                list.add(20)).isInstanceOf(UnsupportedOperationException.class);

        assertThatThrownBy(() ->
                list.set(0, 20)).isInstanceOf(UnsupportedOperationException.class);

        Integer num = list.get(0) + 3;
        assertThat(num).isNotEqualTo(list.get(0));
    }
}
