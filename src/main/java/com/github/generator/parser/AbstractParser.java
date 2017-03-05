package com.github.generator.parser;

import com.github.generator.expersions.Expersion;

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

    protected Expersion parseUntilNextComma() throws ParseException {

        int currentPos = lexer.getCurrentPosition();
        int endPos = lexer.findFirstCommaPosition();
        String newInput = lexer.substring(currentPos,endPos);

        return new Parser(new Lexer(newInput)).parse();
    }

    protected Expersion parseUntilNextRightParenthese() throws ParseException {

        int currentPos = lexer.getCurrentPosition();
        int endPos = lexer.findFirstRightParenthese();
        String newInput = lexer.substring(currentPos,endPos);

        return new Parser(new Lexer(newInput)).parse();

    }


}
