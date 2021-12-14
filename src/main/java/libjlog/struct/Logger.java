package libjlog.struct;

import libjlog.DefaultLogger;

public interface Logger {
    void info(String text);

    void warn(String text);

    void error(String text);

    void debug(String text);

    String colored(String text, float r, float g, float b);

    static Logger getDefault(String prefix) {
        return new DefaultLogger(prefix);
    }
}
