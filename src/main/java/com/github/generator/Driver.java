package com.github.generator;


import com.github.generator.parser.Lexer;
import com.github.generator.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class Driver {

    private static String readFile(String path) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(path));

        StringBuilder result = new StringBuilder();

        String line = null;
        while ( (line=reader.readLine()) != null ){
            result.append( line );
            result.append(System.lineSeparator());
        }

        return result.toString();
    }

    public static void main(String[] args) throws Exception {

        if( args.length == 0 ){
            System.err.println("please specify input file path");
            System.exit(1);
        }

        String filePath = args[0];
        String input = readFile(filePath);
        Parser parser = new Parser( new Lexer(input));
        parser.parse().generate();
    }

}
