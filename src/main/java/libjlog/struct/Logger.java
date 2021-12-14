package libjlog.struct;

import libjlog.DefaultLogger;

public interface Logger {
    /**
     * Returns a default logger
     *
     * @param prefix The prefix to use when logging
     * @return The new default logger
     */
    static Logger getDefault(String prefix) {
        return new DefaultLogger(prefix);
    }

    /**
     * Logs a standard string to console
     *
     * @param text The string
     */
    void info(String text);

    /**
     * Prints a warning to console
     *
     * @param text The string
     */
    void warn(String text);

    /**
     * Prints an error to console
     *
     * @param text The text
     */
    void error(String text);

    /**
     * Prints a debug message to console
     *
     * @param text The text
     */
    void debug(String text);

    /**
     * Formats a string, so it will be colored with the specified RGB value
     *
     * @param text The string
     * @param r    The red component, 0 - 1
     * @param g    The green component, 0 - 1
     * @param b    The blue component, 0 - 1
     * @return The modified string, that will be colored once printed
     * @throws IllegalArgumentException When any color value is above 1 or below 0
     */
    String colored(String text, float r, float g, float b);
}
