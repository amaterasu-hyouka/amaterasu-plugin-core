package jp.amaterasu_hyouka.core.util;

import java.nio.file.Path;

public final class FileUtil {
    private FileUtil() {}

    public static String requireSinglePathName(String name) {
        Path path = Path.of(name);
        if (name.isBlank() || path.isAbsolute() || path.getNameCount() != 1 || name.equals(".") || name.equals("..")) {
            throw new IllegalArgumentException("不正なパス名です: " + name);
        }
        return name;
    }
}
