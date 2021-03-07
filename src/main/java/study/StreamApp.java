package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp {

    public static void main(String[] args) {
        // stream -> 오퍼레이션들의 모음이라고 보면 됨
        // 스트림은 데이터들을 소스로 활용해서 처리한다, 원본 변경 안함.
//        List<String> names = new ArrayList<>();
//        names.add("apple");
//        names.add("banana");
//        names.add("loop");
//        names.add("macbook");
//
////        Stream<String> namesStream = names.stream().map(s -> s.toUpperCase());
//        // 중개형 오페레이터는 lazy 하다, 종료형 오퍼레이터가 오기전까진 실행이 안됨.
//        // 종료형 오퍼레이터가 오기전까지 중개형 오퍼레이터는 무의미해진다.
//        List<String> collect = names.stream().map((s) -> {
//            // 출력 안됨. 그냥 정의가 된거임
//            System.out.println("s = " + s);
//            return s.toUpperCase();
//        }).collect(Collectors.toList());
//        System.out.println("==============");
//
//        Stream<String> namesStream = names.stream()
//                .map(String::toUpperCase);
//
//        // 옛날 병렬처리
//        for (String name : names) {
//            if (name.length() > 3) {
//                System.out.println("name = " + name);
//            }
//        }
//
//        // 스트림을 이용한 병렬처리
//        // parallelStream 사용상황 : 데이터가 방대한 경우.
//        // 그냥 stream 을 사용하자
//        List<String> collect1 = names.stream().map(s -> {
//            System.out.println("s  = " + s + " " + Thread.currentThread().getName());
//            return s.toUpperCase();
//        }).collect(Collectors.toList());

        // stream API 연습
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring"))
                .forEach(c -> System.out.println("c.getTitle() = " + c.getTitle()));

        System.out.println("휴업된 수업");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed)) // 반대 어찌함? Predicate not 이요
                .forEach(c -> System.out.println("c.getTitle() = " + c.getTitle()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(c -> c.getTitle())
//                .forEach(s -> System.out.println("title = " + s));
                .forEach(System.out::println);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        List<List<OnlineClass>> totalClasses = new ArrayList<>();
        totalClasses.add(springClasses);
        totalClasses.add(javaClasses);

        totalClasses.stream()
//                .flatMap(list -> list.stream())
                .flatMap(Collection::stream)
                .forEach(c -> System.out.println("c.getId() = " + c.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1) // 중개형이라 무한루프 발생안됨 정의만됨. 종료형이 있어야됨.
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        javaClasses.stream()
                .filter(j -> j.getTitle().indexOf("Test") > -1)
                .forEach(j -> System.out.println("j.getTitle() = " + j.getTitle()));
        boolean testClass = javaClasses.stream()
                .anyMatch(c -> c.getTitle().contains("Test")); // anyMatch boolean 을 반환
        System.out.println("testClass = " + testClass);


        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
//        List<OnlineClass> spring = springClasses.stream()
//                .filter(c -> c.getTitle().contains("spring"))
//                .collect(Collectors.toList());

        List<String> spring = springClasses.stream()
                .filter(c -> c.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        spring.forEach(System.out::println);
    }
}
