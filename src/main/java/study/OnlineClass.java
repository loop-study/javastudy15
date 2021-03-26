package study;

import java.util.Optional;

public class OnlineClass {
    private int id;
    private String title;
    private boolean closed;

    // 여기에 옵셔널 쓰는것도 비권장이다. 설계에 문제가 생긴다.
    public Progress progress;

    public OnlineClass(int id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

//    public Progress getProgress() {
//        if(this.progress == null) {
//            throw new IllegalArgumentException();
//        }
//        return progress;
//    }
    //Optional<T> 반환타입만 권장한다.
    public Optional<Progress> getProgress() {
        //ofNullable
        //of 의 차이점은?
//        return Optional.of(progress);
        return Optional.empty();
    }

    public void setProgress(Optional<Progress> progress) {
        // 외부에서 null 들어오면 npe 발생, 한번 더 null 체크해야함. 비권장.
        progress.ifPresent(p -> this.progress = p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
