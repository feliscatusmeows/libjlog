import libjlog.struct.Logger;
import libjlog.DefaultLogger;

public class Test {
    @org.junit.jupiter.api.Test
    public void logging() {
        Logger logger = Logger.getDefault("Main");
        logger.info("Yes\nalso newlines");
        logger.warn("Oops\nalso\nmultiple newlines");
        logger.error("Oh no\noh shit indeed");
        logger.debug("dont mind me\njust a sneaky debug log");
    }
}
