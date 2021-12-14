package libjlog;

import libjlog.struct.Logger;
import libjlog.struct.LoggerTheme;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DefaultLogger implements Logger {
    static final  DateFormat  df    = new SimpleDateFormat("y-M-d H:m:s.S");
    private final String      prefix;
    boolean termSupportsTruecolor = true;
    boolean usePrefix             = true;
    private       LoggerTheme theme = LoggerTheme.getDefault();

    /**
     * Constructs a new logger and uses the calling class name as prefix
     */
    public DefaultLogger() {
        prefix = getCallerClass();
    }

    /**
     * Constructs a new logger and uses a custom value as prefix
     *
     * @param prefix The prefix to use, set null to disable prefix
     */
    public DefaultLogger(String prefix) {
        if (prefix == null) {
            usePrefix = false;
            this.prefix = "";
        } else {
            this.prefix = prefix;
        }
    }

    private static String getCallerClass() {
        return CallingClass.instance().getCallingClasses()[3].getSimpleName();
    }

    /**
     * Sets whether to use color in the logging output (set this to false if you don't have a modern terminal emulator)
     *
     * @param termSupportsTruecolor The new value
     */
    public void setUseColor(boolean termSupportsTruecolor) {
        this.termSupportsTruecolor = termSupportsTruecolor;
    }

    /**
     * Sets the theme of this logger
     *
     * @param theme The new theme
     */
    public void setTheme(LoggerTheme theme) {
        this.theme = theme;
    }

    private String formatNewlines(String linePrefix, int insetLength, String in) {
        in = in.trim();
        String[] split = in.split("\n");
        String[] rt = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            rt[i] = " ".repeat(insetLength) + linePrefix + split[i];
        }
        return String.join("\n", rt).substring(insetLength);
    }

    private void doLogDefault(String type, String text, LoggerTheme.ThemeEntry theme) {
        String formatted = String.format("%s %s [%s]", type, df.format(System.currentTimeMillis()), Thread.currentThread().getName());
        String pre = usePrefix ? (prefix + " - ") : "";
        text = formatNewlines(pre, formatted.length() + 1, text);
        System.out.println(colored(String.format("%s %s", formatted, text), theme.red(), theme.green(), theme.blue()));
    }

    @Override public void info(String text) {
        doLogDefault("[INFO ]", text, theme.getInfo());
    }

    @Override public void warn(String text) {
        doLogDefault("[WARN ]", text, theme.getWarn());
    }

    @Override public void error(String text) {
        doLogDefault("[ERROR]", text, theme.getError());
    }

    @Override public void debug(String text) {
        doLogDefault("[DEBUG]", text, theme.getDebug());
    }

    private boolean invalidRGB(float i) {
        return !(i >= 0 && i <= 1);
    }

    @Override public String colored(String text, float r, float g, float b) {
        if (invalidRGB(r)) {
            throw new IllegalArgumentException("r should be within 0 and 1");
        }
        if (invalidRGB(g)) {
            throw new IllegalArgumentException("g should be within 0 and 1");
        }
        if (invalidRGB(b)) {
            throw new IllegalArgumentException("b should be within 0 and 1");
        }

        if (!termSupportsTruecolor) {
            return text;
        }

        int cr = (int) Math.floor(r * 255);
        int cg = (int) Math.floor(g * 255);
        int cb = (int) Math.floor(b * 255);

        String[] split = text.split("\n");
        String[] rt = new String[split.length];

        for (int i = 0; i < split.length; i++) {
            rt[i] = String.format("\033[38;2;%d;%d;%dm%s\033[0m", cr, cg, cb, split[i]);
        }

        return String.join("\n", rt);
    }
}
