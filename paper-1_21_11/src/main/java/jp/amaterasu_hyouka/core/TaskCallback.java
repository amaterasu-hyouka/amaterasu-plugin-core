package jp.amaterasu_hyouka.core;

public record TaskCallback(
        Runnable onSuccess,
        Runnable onError
) {
    private static final Runnable EMPTY = () -> {};

    public TaskCallback {
        onSuccess = onSuccess != null ? onSuccess : EMPTY;
        onError = onError != null ? onError : EMPTY;
    }

    public static TaskCallback empty() {
        return new TaskCallback(EMPTY, EMPTY);
    }

    public static TaskCallback of(
            Runnable onSuccess,
            Runnable onError
    ) {
        return new TaskCallback(onSuccess, onError);
    }

    public void success() {
        onSuccess.run();
    }
    public void error() {
        onError.run();
    }

    public void complete(boolean success) {
        Runnable callback = success ? onSuccess : onError;
        callback.run();
    }
}
