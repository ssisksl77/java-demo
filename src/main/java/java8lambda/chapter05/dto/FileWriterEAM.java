package java8lambda.chapter05.dto;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterEAM {
    private final FileWriter write;

    public FileWriterEAM(String FileName) throws IOException {
        this.write = new FileWriter(FileName);
    }

    private void close() throws IOException {
        System.out.println("close called automatically");
        write.close();
    }

    public void writeStuff(final String message) throws IOException {
        write.write(message);
    }


    public static void use(final String fileName,
                       final UseInstance<FileWriterEAM, IOException> block) throws IOException {
        final FileWriterEAM writerEAM = new FileWriterEAM(fileName);
        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }

}
