package com.github.generator.parser;

/**
 * @author Mostafa Asgari
 * @since 2/19/17
 */
public class Lexer {

    public static final char EOF = (char)-1; // represent end of file char
    private String input;
    private int pos = 0;
    private char c;

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

            if( c==EOF )
                throw new Error("reach end of file");
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

    public boolean isLookahead(String str) {

        if( pos + str.length() < input.length() ){
            return input.substring( pos+1 , pos+1+str.length() ).equals(str);
        }

        return false;
    }

    public boolean isLookahead(char ch){
        if( pos+1 < input.length() ){
            return input.charAt(pos+1) == ch;
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
                    if(isLookahead('.')){
                        consume();
                        consume();
                        return new Token("..", Token.Type.DOUBLE_DOT);
                    }
                    else{
                        return userData();
                    }
                default:
                    return userData();
            }

        }

        return new Token("EOF", Token.Type.EOF);
    }

    private void whitespace(){
        while ( c==' ' || c=='\t' || c=='\n' || c=='\r')
            consume();
    }

    private boolean isNumber(String str){

        if(str==null || str.length()==0)
            return false;

        for( int i=0;i<str.length();i++ ){
            if( !Character.isDigit(str.charAt(i)) )
                return false;
        }

        if( str.length() > 1 && str.charAt(0) == '0' ){
            return false;
        }

        return true;
    }

    private Token userData(){

        StringBuilder sb = new StringBuilder();

        while ( true ){

            if( c==EOF || c=='[' || c==']' || c==')' )
                break;

            if( c=='(' ){
                if( FunctionTables.isAFunction(sb.toString()) ){
                    return new Token(sb.toString(),Token.Type.FUNCTION);
                }
            }

            if( c=='.' && isLookahead('.') ){
                break;
            }

            sb.append(c);
            consume();
        }

        if( sb.length() == 1 && Character.isAlphabetic(sb.charAt(0)) )
            return new Token(sb.toString(),Token.Type.CHAR);
        else if ( isNumber(sb.toString()) )
            return new Token(sb.toString(),Token.Type.NUMBER);
        else
            return new Token(sb.toString(),Token.Type.STRING);

    }

}
