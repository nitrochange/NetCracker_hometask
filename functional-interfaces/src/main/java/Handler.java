import java.io.IOException;

public class Handler {

    static void handle() throws IOException {
        throw new IOException("1");
    }

    static void handleAnotherWay() throws IOException {
        throw new IOException("2");
    }

    static void handleAndAnotherWay() throws IOException {
        throw new IOException("3");
    }
}
