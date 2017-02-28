package com.github.generator.expersions.sink;

import com.github.generator.expersions.Expersion;

import java.io.File;
import java.io.FileWriter;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class FileSink extends Expersion {

    private Expersion expersion;
    private boolean append;
    private String path;

    public FileSink(Expersion expersion, String path , boolean append) {
        this.expersion = expersion;
        this.path = path;
        this.append = append;
    }

    @Override
    public String generate() throws Exception {

        File file = new File(path);

        String content = expersion.generate();

        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(content);
        fileWriter.close();

        return content;
    }
}
