package java8_impatiant.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Util {
    public static List<String> readAlice() throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("src/main/alice.txt")), StandardCharsets.UTF_8);
        return Arrays.asList(contents.split("\\PL+"));
    }
}
