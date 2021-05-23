package example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class ResourcesReader {

    public String readFile(String fileName) throws FileNotFoundException {
        try {
            URL file = this.getClass().getClassLoader().getResource(fileName);
            assert file != null;
            File file1 = new File(file.getFile());
            try (Stream<String> fileReader = Files
                    .lines(Paths.get(file1.getCanonicalPath()))) {
                StringJoiner joiner = new StringJoiner(" ");
                fileReader.forEach(joiner::add);
                return joiner.toString();
            }

        } catch (IOException | NullPointerException e) {
            throw new FileNotFoundException("file: " + fileName + " is not found");
        }
    }
}
