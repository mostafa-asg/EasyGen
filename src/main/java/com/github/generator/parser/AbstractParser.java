package com.github.generator.parser;

import com.github.generator.expersions.Expression;

/**
 * Created by Mostafa on 02/23/2017.
 */
public abstract class AbstractParser implements IParser {

    protected Lexer lexer;
    public AbstractParser(Lexer lexer) {
        this.lexer = lexer;
    }

    protected String readString(){

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.SINGLE_QUOTES ){
            return lexer.readSingleQuoteString();
        }

        return token.getValue();
    }

    protected Token getNextNToken(int n){
        Token token = null;

        for(int i=1;i<=n;i++)
            token = lexer.nextToken();

        return token;
    }

    protected Token ensureNextTokenIs(Token.Type type) throws ParseException {

        Token token = lexer.nextToken();

        if (token.getType() != type)
            throw new ParseException();

        return token;
    }
    protected Token ensureNextTokenIs(Token.Type type , String value) throws ParseException {

        Token token = lexer.nextToken();

        if (token.getType() != type || !token.getValue().equals(value))
            throw new ParseException();

        return token;
    }

    protected Token ensureCurrentTokenIs(Token.Type type) throws ParseException {

        Token token = lexer.getCurrentToken();

        if (token.getType() != type)
            throw new ParseException();

        return token;
    }

    protected Expression parseUntilNextComma() throws ParseException {

        int currentPos = lexer.getCurrentPosition();
        int endPos = lexer.findFirstCommaPosition();
        String newInput = lexer.substring(currentPos,endPos);

        return new Parser(new Lexer(newInput)).parse();
    }

    protected Expression parseUntilNextRightParenthese() throws ParseException {

        int currentPos = lexer.getCurrentPosition();
        int endPos = lexer.findFirstRightParenthese();
        String newInput = lexer.substring(currentPos,endPos);

        return new Parser(new Lexer(newInput)).parse();
    }

    protected Token parseSignedNumber() throws ParseException {

        String sign = "+";

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.MINUS ){
            sign = "-";
        }
        else if( token.getType() == Token.Type.PLUS ){
            sign = "+";
        }
        else if ( token.getType() == Token.Type.NUMBER ){
            return token;
        }
        else{
            throw new ParseException();
        }

        Token numb = ensureNextTokenIs(Token.Type.NUMBER);
        return new Token( sign+numb.getValue() , Token.Type.NUMBER );
    }

}
