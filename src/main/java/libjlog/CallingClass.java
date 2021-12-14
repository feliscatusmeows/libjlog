package libjlog;

class CallingClass extends SecurityManager {
    public static final CallingClass instance = new CallingClass();

    private CallingClass() {
    }

    public static CallingClass instance() {
        return instance;
    }

    public Class<?>[] getCallingClasses() {
        return getClassContext();
    }
}
