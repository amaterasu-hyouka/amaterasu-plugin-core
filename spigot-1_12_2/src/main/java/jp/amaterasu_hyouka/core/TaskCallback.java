package jp.amaterasu_hyouka.core;

public final class TaskCallback {
    private static final Runnable EMPTY = () -> {};

    private final Runnable onSuccess; public Runnable onSuccess(){return onSuccess;}
    private final Runnable onError;   public Runnable onError()  {return onError;}

    public TaskCallback(Runnable onSuccess, Runnable onError) {
        this.onSuccess = onSuccess != null ? onSuccess : EMPTY;
        this.onError = onError != null ? onError : EMPTY;
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
