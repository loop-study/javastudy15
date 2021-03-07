package study;

import java.util.function.Function;

// 인터페이스 상속받아 사용하기,
// Function<arg type, return type> -> 입력값이 하나
public class Plus10 implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
