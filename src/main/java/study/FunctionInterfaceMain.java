package study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/**
 * 15주차 함수형 프로그래밍, 람다
 */
public class FunctionInterfaceMain {

    public static void main(String[] args) {
        /**
         * 함수형 인터페이스란?
         */
        // 자바8 이전, 익명 내부 클래스 방식
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("Hello");
//            }
//        };

        // 자바8 부터, 람다형, void doIt
//        RunSomething runSomething = () -> System.out.println("Hello");
//        runSomething.doIt();

//        RunSomething runSomething = (number) -> number + 10;
//
//        // 같은 값을 넣었을 때 같은 값이 나와야함
//        System.out.println(runSomething.doIt(1));
//        System.out.println(runSomething.doIt(1));
//        System.out.println(runSomething.doIt(1));
//
//        System.out.println(runSomething.doIt(2));
//        System.out.println(runSomething.doIt(2));
//        System.out.println(runSomething.doIt(2));

        // 만약에 외부에서 참조하면?
//        int outNumber = 10;

//        RunSomething runSomething = new RunSomething() {
//            int baseNumber = 10;
//
//            @Override
//            public int doIt(int number) {
//                baseNumber++;
//                return number + baseNumber;
//            }
//        };
//
//        RunSomething runSomething = (number) -> {
//// 람다에 사용되는 외부 변수값이 변경되려니 컴파일 에러 발생, 참조는 가능.
//// 해당 변수는 final 해야한다
////            outNumber++;
//            return number + outNumber;
//        };
//
//        System.out.println(runSomething.doIt(1));
//        System.out.println(runSomething.doIt(1));
//        System.out.println(runSomething.doIt(1));

//        Plus10 plus10 = new Plus10();
//        System.out.println(plus10.apply(1));

        // 한줄은 return 생략가능
//        Function<Integer, Integer> plus10 = (number) -> number + 10;
//        Function<Integer, Integer> multiply2 = (number) -> number * 2;
//        System.out.println("plus10.apply(1) = " + plus10.apply(1));

        // 함수 조합
//        System.out.println(multiply2.apply(1));

        // compose : 입력한 함수를 먼저 실행 후 앞의 함수 실행
//        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
//        System.out.println("multiply2AndPlus10 = " + multiply2AndPlus10.apply(10));

        // andThen : 앞의 플러스 10 진행 후 반환된 결과값으로 곱2 실행
//        System.out.println(plus10.andThen(multiply2).apply(10));

        // Consumer<T:인자타입> : 인자를 받아서 실행함, 반환 없음.
//        Consumer<Integer> printT = (number) -> System.out.println(number);
//        printT.accept(10);

        // Supplier<T:반환타입> : 인자가 없이, 반환만 한다
//        Supplier<Integer> get10 = () -> 10;
//        System.out.println(get10.get());

        // Predicate<T:인자타입> : 받은 인자값으로 내부로직에 의해서 true, false 반환
        // and, or 조합이 가능
//        Predicate<String> startsWithLoop = (s) -> s.startsWith("loop");
//        Predicate<Integer> isEven = (i) -> i % 2 == 0;

        // Function<Integer, Integer> 같이 인자와 반환이 동일한 경우 UnaryOperator<T> 로 사용할 수 있다
//        UnaryOperator<Integer> plus100 = (i) -> i + 100;
//        System.out.println(isEven.test(plus100.apply(100)));

//        BiFunction<Integer, Integer, Integer> getSum = (a, b) -> a + b;
//        BinaryOperator<Integer> getSum = (a, b) -> a + b;

//        FunctionInterfaceMain foo = new FunctionInterfaceMain();
//        foo.run();

        //===================
        //    메소드 레퍼런스
        //===================
//        Function<Integer, String> intoString = (i) -> "number";
//        UnaryOperator<String> hi = (s) -> "hi " + s; // Greeting.hi 와 동일
//        UnaryOperator<String> hi = Greeting::hi; // :: 콜론 2개로 사용함

//        Greeting greeting = new Greeting();
//        UnaryOperator<String> hi = greeting::hello;
//
//        // 생성자도 참조가 가능함
//        Supplier<Greeting> newGreeting = Greeting::new; // 실제 인스턴스가 아님
//        Greeting greeting1 = newGreeting.get();// 인스턴스 생성이 됨
//
//        UnaryOperator<String> hello = greeting::hello;
//        System.out.println(hello.apply("loop"));
//
//        Function<String, Greeting> loopGreeting = Greeting::new; // 생성자(String name)를 참조중
//        Greeting macbook = loopGreeting.apply("macbook");
//        macbook.printName();

//        String[] names = {"loop", "macbook", "java", "apple"};
//        // 옛날 정렬 방법
////        Arrays.sort(names, new Comparator<String>() {
////            @Override
////            public int compare(String o1, String o2) {
////                return 0;
////            }
////        });
//
//        // 메소드 레퍼런스 방식 (람다 방식도 가능)
//        Arrays.sort(names, String::compareToIgnoreCase);
//        System.out.println(Arrays.toString(names));
    }

    private void run() {
        // 자바8 부터 final 생략가능. -> 사실상 변경이 안될 경우 생략 가능함
        // 이펙티브 파이널이라고 함
        int baseNumber = 10;

        // 로컬 클래스 - 쉐도잉 가능
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // 외부 변수를 가려서 쉐도잉됨
                System.out.println("로컬 클래스 baseNumber = " + baseNumber);
            }
        }

        LocalClass lc = new LocalClass();
        lc.printBaseNumber();

        // 익명 클래스능 - 쉐도잉 가능
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println("익명 클래스 baseNumber = " + baseNumber);
            }
        };

        integerConsumer.accept(10);

        // Consumer<Integer> printInt
        // 익명 혹은 내부 클래스에서 외부변수를 사용하려면 final 적용이 되어야함
        // 람다 - 쉐도잉 불가능, 같은 스코프를 가지기 때문, 동일한 이름의 변수 선언 불가능
        // i -> baseNumber 변경도 못함 같은 변수가 존재하기 때문
        IntConsumer printInt = (i) -> {
//            int baseNumber = 11; // 에러 발생.
            System.out.println("람다 = " + (i + baseNumber));
        };

        printInt.accept(10);
    }
}
