package study;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalMain {

    public static void main(String[] args) {

        /**
         5부 Optional
         Optional 소개
         자바 프로그래밍에서 NullPointerException을 종종 보게 되는 이유
         null을 리턴하니까! && null 체크를 깜빡했으니까!

         메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
         예외를 던진다. (비싸다, 스택트레이스를 찍어두니까.)
         null을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클리어인트 코드가 주의해야 한다.)
         (자바 8부터) Optional을 리턴한다. (클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.)

         Optional
         오직 값 한 개가 들어있을 수도 없을 수도 있는 컨네이너.

         주의할 것
         리턴값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자.)
         Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
         프리미티브 타입용 Optional을 따로 있다. OptionalInt, OptionalLong,...
         Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말 것.

         참고
         https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
         https://www.oracle.com/technical-resources/articles/java/java8-optional.html
         이팩티브 자바 3판, 아이템 55 적절한 경우 Optional을 리턴하라.

         Optional API

         Optional 만들기
         Optional.of()
         Optional.ofNullable()
         Optional.empty()

         Optional에 값이 있는지 없는지 확인하기
         isPresent()
         isEmpty() (Java 11부터 제공)

         Optional에 있는 값 가져오기
         get()
         만약에 비어있는 Optional에서 무언가를 꺼낸다면??

         Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라.
         ifPresent(Consumer)
         예) Spring으로 시작하는 수업이 있으면 id를 출력하라.

         Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.
         orElse(T)
         예) JPA로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.

         Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라.
         orElseGet(Supplier)
         예) JPA로 시작하는 수업이 없다면 새로 만들어서 리턴하라.

         Optional에 값이 있으면 가졍고 없는 경우 에러를 던져라.
         orElseThrow()

         Optional에 들어있는 값 걸러내기
         Optional filter(Predicate)

         Optional에 들어있는 값 변환하기
         Optional map(Function)
         Optional flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional인 경우에 사용하면 편리하다.

         */
        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Optional<Progress> progress = spring_boot.getProgress();
        // null 체크를 항상 할순 없다. 놓칠 수 있다.
        // null 리턴하는게 문제다. 자바8 이전엔 null 대안이 없었다.
//        if (progress != null) {
//            System.out.println(progress.get().getStudyDuration());
//        }

        // 원시값을 넣에도 박싱, 언박싱이 벌어짐. 성능 문제.
//        Optional.of(10); // 비권장
//        OptionalInt.of(10); // 숫자 같은 원시값은 이걸로 사용하자.

        // 여기도 null 에러 발생.
//        progress.ifPresent(p -> System.out.println(p));


        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        // findFirst() 인데 왜 옵셔널이 감싸졌을까?
        // -> 없을 수도 있기 때문에.
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jap"))
                .findFirst();

//        boolean present = optional.isPresent();  // 있으면 true, 없으면 false
//        System.out.println("present = " + present);

        // get() 으로 값을 꺼낼 수 있음. null 일 수도 있기 때문에 존재여부부터 확인이 필함.
        // Exception in thread "main" java.util.NoSuchElementException: No value present
        // 확인 후 꺼내라고 에러 발생함.
//        OnlineClass onlineClass = optional.get();
//        System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle());

        // 확인하면 에러 발생 안함.
//        if (optional.isPresent()) {
//            OnlineClass onlineClass = optional.get();
//            System.out.println("onlineClass.getTitle() = " + onlineClass.getTitle());
//        }

        // 사용 권장 사항.
//        optional.ifPresent(oc -> System.out.println(oc.getTitle()));
        // orElse -> 새로운 클래스를 만드는 코드 실행하고 있으면 꺼내오고 아니면 실행된 코드의 값 반환
        // 옵셔널에 랩핑된게 있더라도 무조건 실행되는 코드, 뭔가 찝찝.
//        OnlineClass onlineClass = optional.orElse(createNewClasses());

        // orElseGet -> 랩핑된게 있으면 꺼내오고 없으면 코드를 실행하여 반환.
//        OnlineClass onlineClass = optional.orElseGet(OptionalMain::createNewClasses);

        // 만약에 만들어서 대체해줄 수 없다면 에러를 반환해야한다.
        // orElseThrow -> 없으면 에러발생. 람다, 함수형 인터페이스로 사용가능
//        OnlineClass onlineClass = optional.orElseThrow(IllegalStateException::new);

        // filter -> 해당이 되면 값이 반환되고 없으면 옵셔널이 반환됨.
//        Optional<OnlineClass> onlineClass =
//                optional.filter(OnlineClass::isClosed);
////              optional.filter(oc -> !oc.isClosed());
//
//        System.out.println("onlineClass.isEmpty() = " + onlineClass.isEmpty());
//        System.out.println("onlineClass.isPresent() = " + onlineClass.isPresent());

        // map 으로 변환하는 타입이 옵셔널이다.
//        Optional<Integer> integer = optional.map(OnlineClass::getId);
//        System.out.println("integer.isPresent() = " + integer.isPresent());

        // 만약에 옵셔널이 반환되는데 map 에서도 또 옵셔널로 반환해주면?
        // 옵셔널을 옵셔널로 감싼다. 되게 복잡해짐.
        // 양파처럼 계속 까서 가져와야함.
//        Optional<Optional<Progress>> progress1 = optional.map(OnlineClass::getProgress);
//        Optional<Progress> progress11 = progress1.orElseThrow();
//        progress11.orElseThrow();

        // 스트림의 flatMap 과는 많이 다르다.
        // flatMap -> 반환하는게 optional 로 감싸져 있다면 까주고 반환해준다.
        Optional<Progress> progress1 = optional.flatMap(OnlineClass::getProgress);
//        Progress progress2 = progress1.orElseThrow();

//        Optional<Optional<Progress>> progress11 = optional.map(OnlineClass::getProgress);
//        Optional<Progress> progress12 = progress11.orElse(Optional.empty());


    }

    private static OnlineClass createNewClasses() {
        return new OnlineClass(10, "New Class", false);
    }
}
