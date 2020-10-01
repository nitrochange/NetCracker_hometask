import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testConsumerWrap() throws Exception {
        ConsumerWrapper consumerWrapper1 = Handler::handle;
        ConsumerWrapper consumerWrapper2 = Handler::handleAnotherWay;
        ConsumerWrapper consumerWrapper3 = Handler::handleAndAnotherWay;
        Assertions.assertThrows(IOException.class, () -> consumerWrapper1.wrapConsumer());
        Assertions.assertThrows(IOException.class, () -> consumerWrapper2.wrapConsumer());
        Assertions.assertThrows(IOException.class, () -> consumerWrapper3.wrapConsumer());

    }
}