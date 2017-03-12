package com.github.generator.expersions.sink;

import com.github.generator.expersions.Expression;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class FileSink extends Expression {

    private Expression expression;
    private boolean append;
    private String path;

    public FileSink(Expression expression, String path , boolean append) {
        this.expression = expression;
        this.path = path;
        this.append = append;
    }

    @Override
    public String generate() throws Exception {

        File file = new File(path);
        if( file.exists() && append==false ){
            throw new FileAlreadyExistsException(path);
        }

        String content = expression.generate();

        FileWriter fileWriter = new FileWriter(file,append);
        fileWriter.write(content);
        fileWriter.close();

        return content;
    }
}
