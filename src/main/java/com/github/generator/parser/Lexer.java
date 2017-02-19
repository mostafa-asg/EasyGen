package com.github.generator.parser;

import java.util.Stack;

/**
 * @author Mostafa Asgari
 * @since 2/19/17
 */
public class Lexer {

    public static final char EOF = (char)-1; // represent end of file char
    private String input;
    private int pos = 0;
    private char c;
    private Stack<Integer> posStack = new Stack<Integer>();

    public Lexer(String input) {

        if( input == null || input.length()==0 )
            throw new IllegalArgumentException("input");

        this.input = input;
        c = input.charAt(pos);
    }

    /**
     * Move one character; detect "end of file"
     */
    public void consume() {
        ++pos;
        if (pos >= input.length()) {
            c = EOF;
        } else {
            c = input.charAt(pos);
        }
    }

    public String consumeUntil(char untilChar){

        StringBuilder sb = new StringBuilder();

        while (c != untilChar){
            sb.append(c);
            consume();
        }

        return sb.toString();
    }

    public void match(char expected){
        if( c == expected )
            consume();
        else
            throw new Error("expected " + expected + " but found " + c );
    }

    public boolean tryMatch(char expected){
        if( c == expected ) {
            consume();
            return true;
        }

        return false;
    }

    public Token nextToken(){

        while (c!=EOF){

            switch (c){
                case ' ': case '\t': case '\n': case '\r':
                    whitespace();
                    continue;
                case ',':
                    consume();
                    return new Token(",", Token.Type.COMMA);
                case '[':
                    consume();
                    return new Token("[", Token.Type.L_BRACKET);
                case ']':
                    consume();
                    return new Token("]", Token.Type.R_BRACKET);
                case '(':
                    consume();
                    return new Token("(", Token.Type.L_PARENTHESE);
                case ')':
                    consume();
                    return new Token(")", Token.Type.R_PARENTHESE);
                case '\'':
                    consume();
                    return new Token("'", Token.Type.SINGLE_QUOTES);
                case '.':
                    posStack.push( pos );
                    consume();
                    if (tryMatch( '.' )){
                        posStack.pop();
                        return new Token("..", Token.Type.DOUBLE_DOT);
                    }
                    else{
                        pos = posStack.pop();
                        return userData();
                    }
            }

        }

        return new Token("EOF", Token.Type.EOF);
    }

    private void whitespace(){
        while ( c==' ' || c=='\t' || c=='\n' || c=='\r')
            consume();
    }

    private Token userData(){

        StringBuilder sb = new StringBuilder();

        while (c != '[' || c!='(' ){
            sb.append( c );
            consume();
        }

        return new Token(sb.toString(),Token.Type.STRING_TERMINAL);
    }

}
