package jp.amaterasu_hyouka.core.util;

public class ArrayUtil {
    private ArrayUtil(){}

    public static <T> boolean isValidIndex(T[] array, int index) {
        return array != null && index >= 0 && index < array.length;
    }

    public static <T> T getOrDefault(T[] array, int index, T defaultValue) {
        if (!isValidIndex(array, index)) {
            return defaultValue;
        }
        return array[index];
    }
}
