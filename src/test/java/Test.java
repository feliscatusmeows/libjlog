import libjlog.DefaultLogger;
import libjlog.struct.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {
    @org.junit.jupiter.api.Test public void logging() {
        DefaultLogger logger = (DefaultLogger) Logger.getDefault(null);
        logger.setUseColor(true);
        logger.info("Hello world\nit also supports newlines");
        logger.warn("Please don't do it again\nI don't like it\nPlease dont do that");
        try {
            throw new Exception("Oh no");
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            logger.error("Caught exception in nowhereland\n" + error);
        }
        logger.debug("Hit breakpoint 1\nThis is a good sign");
    }
}
