package com.github.generator.parser;

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

}
