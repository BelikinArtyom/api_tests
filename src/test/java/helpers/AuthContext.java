package helpers;

public class AuthContext {
    private static final ThreadLocal<String> token = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setToken(String value) {
        token.set(value);
    }

    public static String getToken() {
        return token.get();
    }

    public static void setUserId(String value) {
        userId.set(value);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        token.remove();
        userId.remove();
    }
}
