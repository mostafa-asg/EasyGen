package com.github.generator.parser;

import java.util.Stack;

/**
 * @author Mostafa Asgari
 * @since 2/19/17
 */
public class Lexer {

    public static final char EOF = (char)-1; // represent end of file char
    private String input;
    private int pos;
    private char c;

    public Lexer(String input) {

        if( input == null || input.length()==0 )
            throw new IllegalArgumentException("input");

        this.input = input;

        seek(0);
    }

    public void seek(int newPos){
        pos = newPos;
        if( pos >= input.length() )
            c = EOF;
        else
            c = input.charAt(pos);
    }

    public int getCurrentPosition(){
        return pos;
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

    public String readSingleQuoteString(){

        StringBuilder sb = new StringBuilder();

        while (true){

            if( c == '\'' ){
                if( sb.length() > 0 && sb.charAt(sb.length()-1) == '\\' ){
                    sb.deleteCharAt(sb.length()-1);
                    sb.append( c );
                }
                else{
                    consume();
                    break;
                }
            }

            sb.append(c);
            consume();

            if( c==EOF )
                throw new Error("reach end of file");
        }

        return sb.toString();
    }

    public int findFirstCommaPosition(){
        Stack<Character> stack = new Stack<Character>();

        while (true){

            if( c == '\'' ){
                readSingleQuoteString();
                continue;
            }
            else if ( c == '(' ){
                stack.push('(');
            }
            else if ( c == ')' ){
                if( stack.size() > 0 )
                    stack.pop();
            }
            else if ( c==',' ){
                if( stack.size() == 0 )
                    return pos;
            }

            consume();

            if( c==EOF )
                throw new Error("reach end of file");
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
    public boolean isLookaheadIgnoreWhitespace(char expectedChar) {

        int tempPos = pos+1;

        if(tempPos >= input.length())
            return false;

        char c = input.charAt(tempPos);
        while ( isWhitespace(c) ){
            ++tempPos;

            if( tempPos>=input.length() )
                return false;

            c = input.charAt(tempPos);
        }

        return c == expectedChar;
    }

    private Token currentToken;
    public Token getCurrentToken(){
        return currentToken;
    }

    public Token nextToken(){

        while (c!=EOF){

            switch (c){
                case ' ': case '\t': case '\n': case '\r':
                    whitespace();
                    continue;
                case '+':
                    consume();
                    currentToken = new Token("+",Token.Type.PLUS);
                    return currentToken;
                case '-':
                    consume();
                    currentToken = new Token("-",Token.Type.MINUS);
                    return currentToken;
                case ',':
                    consume();
                    currentToken = new Token(",", Token.Type.COMMA);
                    return currentToken;
                case '[':
                    consume();
                    currentToken = new Token("[", Token.Type.L_BRACKET);
                    return currentToken;
                case ']':
                    consume();
                    currentToken = new Token("]", Token.Type.R_BRACKET);
                    return currentToken;
                case '(':
                    consume();
                    currentToken = new Token("(", Token.Type.L_PARENTHESE);
                    return currentToken;
                case ')':
                    consume();
                    currentToken = new Token(")", Token.Type.R_PARENTHESE);
                    return currentToken;
                case '|':
                    consume();
                    currentToken = new Token("|", Token.Type.PIPE);
                    return currentToken;
                case '\'':
                    consume();
                    currentToken = new Token("'", Token.Type.SINGLE_QUOTES);
                    return currentToken;
                case '.':
                    if(isLookahead('.')){
                        consume();
                        consume();
                        currentToken = new Token("..", Token.Type.DOUBLE_DOT);
                        return currentToken;
                    }
                    else{
                        return userData();
                    }
                default:
                    return userData();
            }

        }

        currentToken = new Token("EOF", Token.Type.EOF);
        return currentToken;
    }

    public String substring(int beginIndex,int endIndex){
        return input.substring(beginIndex,endIndex);
    }

    private void whitespace(){
        while ( isWhitespace() )
            consume();
    }

    private boolean isWhitespace(){
        return isWhitespace(c);
    }
    private boolean isWhitespace(char c){
        if( c==' ' || c=='\t' || c=='\n' || c=='\r' )
            return true;

        return false;
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

            if( c==EOF || c=='[' || c==']' || c==')' || c=='|' || c==',' )
                break;

            if( (c == '(' && FunctionTables.isAFunction(sb.toString()))
                    ||
                isWhitespace() && isLookaheadIgnoreWhitespace('(')
              ){
                currentToken = new Token(sb.toString(),Token.Type.FUNCTION);
                return currentToken;
            }

            if( isWhitespace() )
                break;
            if( c=='.' && isLookahead('.') ){
                break;
            }

            sb.append(c);
            consume();
        }

        if( sb.length() == 1 && Character.isAlphabetic(sb.charAt(0)) ) {
            currentToken = new Token(sb.toString(), Token.Type.CHAR);
            return currentToken;
        }
        else if ( isNumber(sb.toString()) ) {
            currentToken = new Token(sb.toString(), Token.Type.NUMBER);
            return currentToken;
        }
        else {
            currentToken = new Token(sb.toString(), Token.Type.STRING);
            return currentToken;
        }

    }

}
