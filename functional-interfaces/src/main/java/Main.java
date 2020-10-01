import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        //пришлось сделать методы класса Handler static
        // потому что компилятор ругался на вызов нестатического метода
        //из статического контекста, надеюсь это не сильно страшно
        Consumer<Handler> consumer = wrap(Handler::handle);
        Consumer<Handler> anotherConsumer = wrap(Handler::handleAnotherWay);
        Consumer<Handler> andAnotherConsumer = wrap(Handler::handleAndAnotherWay);

        Handler handler = new Handler();

        consumer.accept(handler); //expected output: 1
        anotherConsumer.accept(handler); //expected output: 2
        andAnotherConsumer.accept(handler); //expected output: 3
    }


    private static <T> Consumer<T> wrap(ConsumerWrapper<T, Exception> consumerWrapper) {
        return i -> {
            try {
                consumerWrapper.wrapConsumer();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }

}
