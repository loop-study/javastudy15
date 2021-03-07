package study;

@FunctionalInterface
public interface RunSomething {

    // 추상 메서드가 하나만 있으면 함수형 인터페이스
//    void doIt();
    int doIt(int number);

    // 추상메서드가 2개라면 컴파일 에러가 발생한다
    // void doIt2();

//    static void printName() {
//        System.out.println("loop");
//    }
//
//    default void printAge() {
//        System.out.println("3살~");
//    }
}
