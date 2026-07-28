package jp.amaterasu_hyouka.core.datapack;

import jp.amaterasu_hyouka.core.util.FileUtil;
import org.jetbrains.annotations.NotNull;

public record DataPackFile(@NotNull String name, @NotNull String content) {
    public DataPackFile {
        FileUtil.requireSinglePathName(name);
    }
}
