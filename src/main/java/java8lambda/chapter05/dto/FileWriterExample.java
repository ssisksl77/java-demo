package java8lambda.chapter05.dto;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    private final FileWriter fileWriter;

    public FileWriterExample(final String fileName) throws IOException {
        this.fileWriter = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException {
        fileWriter.write(message);
    }

    public void finalize() throws IOException {
        fileWriter.close();
    }

    public void close() throws IOException {
        fileWriter.close();
    }
    // ...

}
