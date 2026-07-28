package jp.amaterasu_hyouka.core.datapack;

import jp.amaterasu_hyouka.core.util.FileUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * このクラスの書き込み・削除メソッドはファイル操作をするので、非同期スレッドから呼び出すこと
 * Bukkit APIを非同期スレッドで呼び出さないよう、引数はWorldではなくメインスレッドで取得したworldPathを受け取る
 */
public final class DataPackWriter {
    private final String dataPackName;
    private final String namespaceName;
    private final DataPackResourceType resourceType;

    // /world/datapacks/{dataPackName}/data/{namespaceName}/{resourceType}/example.json
    public DataPackWriter(@NotNull String dataPackName, @NotNull String namespaceName, @NotNull DataPackResourceType resourceType) {
        this.dataPackName = FileUtil.requireSinglePathName(dataPackName);
        this.namespaceName = FileUtil.requireSinglePathName(namespaceName);
        this.resourceType = resourceType;
    }

    public void ensureWrite(@NotNull Path worldPath, @NotNull DataPackFile file) throws IOException {
        Path outputPath = getOutputPath(worldPath);
        Files.createDirectories(outputPath);

        PackMcMeta.ensure(getDataPackPath(worldPath));

        Files.writeString(getFilePath(worldPath, file.name()), file.content(), StandardCharsets.UTF_8);
    }

    public boolean exists(@NotNull Path worldPath, @NotNull String fileName) {
        return Files.isRegularFile(getFilePath(worldPath, fileName));
    }

    public boolean delete(@NotNull Path worldPath, @NotNull String fileName) throws IOException {
        return Files.deleteIfExists(getFilePath(worldPath, fileName));
    }
    public void deleteAll(@NotNull Path worldPath) throws IOException {
        Path outputPath = getOutputPath(worldPath);
        if (Files.notExists(outputPath)) {
            return;
        }

        try (Stream<Path> paths = Files.walk(outputPath)) {
            for (Path path : paths.filter(path -> !path.equals(outputPath)).sorted(Comparator.reverseOrder()).toList()) {
                Files.delete(path);
            }
        }
    }

    public Path getFilePath(@NotNull Path worldPath, @NotNull String fileName) {
        return getOutputPath(worldPath).resolve(FileUtil.requireSinglePathName(fileName));
    }
    public Path getOutputPath(@NotNull Path worldPath) {
        return getDataPackPath(worldPath).resolve("data").resolve(namespaceName).resolve(resourceType.getDirectoryPath()).normalize();
    }

    private Path getDataPackPath(@NotNull Path worldPath) {
        return getWorldRootPath(worldPath).resolve("datapacks").resolve(dataPackName);
    }
    private Path getWorldRootPath(@NotNull Path worldPath) {
        return worldPath.toAbsolutePath().normalize();
    }
}
