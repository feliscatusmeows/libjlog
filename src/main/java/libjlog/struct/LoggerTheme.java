package libjlog.struct;

public interface LoggerTheme {
    private static float ensureColorIsValid(float in) {
        return Math.min(1f, Math.max(in, 0f));
    }

    static LoggerTheme getDefault() {
        return new LoggerTheme() {
            @Override public ThemeEntry getNormal() {
                return new ThemeEntry(1, 1, 1);
            }

            @Override public ThemeEntry getWarn() {
                return new ThemeEntry(0.654902f, 0.47843137f, 0.03137255f);
            }

            @Override public ThemeEntry getError() {
                return new ThemeEntry(0.78039217f, 0.2627451f, 0.13333334f);
            }

            @Override public ThemeEntry getDebug() {
                return new ThemeEntry(0.13333334f, 0.78039217f, 0.74509805f);
            }
        };
    }

    ThemeEntry getNormal();

    ThemeEntry getWarn();

    ThemeEntry getError();

    ThemeEntry getDebug();

    record ThemeEntry(float red, float green, float blue) {
        @Override public float red() {
            return ensureColorIsValid(red);
        }

        @Override public float green() {
            return ensureColorIsValid(green);
        }

        @Override public float blue() {
            return ensureColorIsValid(blue);
        }
    }
}
