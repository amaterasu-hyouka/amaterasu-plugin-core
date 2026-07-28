package jp.amaterasu_hyouka.core.datapack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

final class PackMcMeta {
    private PackMcMeta(){}

    private static final String FILE_NAME = "pack.mcmeta";
    private static final int MAJOR_FORMAT = 94;
    private static final int MINOR_FORMAT = 1;

    static void ensure(Path dataPackPath) throws IOException {
        Path packMcMetaPath = dataPackPath.resolve(FILE_NAME);
        if (Files.exists(packMcMetaPath)) {
            return;
        }
        Files.writeString(packMcMetaPath, createContent(), StandardCharsets.UTF_8);
    }

    private static String createContent() {
        return """
                {
                  "pack": {
                    "description": "Generated data pack",
                    "min_format": [%d, %d],
                    "max_format": [%d, %d]
                  }
                }
                """.formatted(
                MAJOR_FORMAT,
                MINOR_FORMAT,
                MAJOR_FORMAT,
                MINOR_FORMAT
        );
    }
}
