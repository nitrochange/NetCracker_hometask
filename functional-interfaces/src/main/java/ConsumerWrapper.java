import java.io.IOException;

@FunctionalInterface
public interface ConsumerWrapper<T, E extends Exception> {
    
    void wrapConsumer() throws E;
}
