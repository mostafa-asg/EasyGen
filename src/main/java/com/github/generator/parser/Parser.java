package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.functions.ranges.CharRange;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.functions.ranges.StringRange;
import com.github.generator.expersions.terminals.StringTerminal;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public SequenceExpersion parse() throws ParseException {

        SequenceExpersion result = new SequenceExpersion();

        while (true){

            Token token = lexer.nextToken();
            if( token.getType()==Token.Type.EOF )
                break;

            if( token.getType() == Token.Type.SINGLE_QUOTES ){
                result.addExpersion( new StringTerminal(lexer.readSingleQuoteString()) );
            }
            else if( token.getType() == Token.Type.STRING || token.getType() == Token.Type.CHAR )
                result.addExpersion( new StringTerminal(token.getValue()));
            else if( token.getType() == Token.Type.SINGLE_QUOTES ){
                result.addExpersion(new StringTerminal(lexer.consumeUntil('\'')));
                lexer.match('\'');
            }
            else if(token.getType()==Token.Type.L_BRACKET){
                result.addExpersion( parseRange( ) );
            }
        }

        return result;
    }

    private String readString(){

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.SINGLE_QUOTES ){
            return lexer.readSingleQuoteString();
        }

        return token.getValue();
    }

    private StringRange parseRangePipeDelimiter() throws ParseException{

        StringRange result = new StringRange();
        result.addItem( new StringTerminal(readString()) );

        if(lexer.nextToken().getType() != Token.Type.PIPE){
            throw new ParseException();
        }
        result.addItem( new StringTerminal(readString()) );

        while (lexer.nextToken().getType() == Token.Type.PIPE){
            result.addItem( new StringTerminal(readString()) );
        }

        if( lexer.getCurrentToken().getType() != Token.Type.R_BRACKET )
            throw new ParseException();

        return result;
    }

    private Token getNextNToken(int n){
        Token token = null;

        for(int i=1;i<=n;i++)
            token = lexer.nextToken();

        return token;
    }

    private Expersion parseRange() throws ParseException {

        boolean isPipeDelimiter = false;

        int tempPos = lexer.getCurrentPosition();

        Token firstParam = lexer.nextToken();
        if( firstParam.getType() == Token.Type.SINGLE_QUOTES ){
            isPipeDelimiter = true;
        }
        else {
            lexer.seek(tempPos);
            if (getNextNToken(2).getType() == Token.Type.PIPE)
                isPipeDelimiter = true;
        }
        lexer.seek(tempPos);

        if( isPipeDelimiter ){
            return parseRangePipeDelimiter();
        }
        else {
            firstParam = lexer.nextToken();

            if (firstParam.getType() == Token.Type.NUMBER) {
                if (lexer.nextToken().getType() != Token.Type.DOUBLE_DOT)
                    throw new ParseException();

                Token secondParam = lexer.nextToken();

                if (secondParam.getType() != Token.Type.NUMBER)
                    throw new ParseException();

                if (lexer.nextToken().getType() != Token.Type.R_BRACKET)
                    throw new ParseException();

                return new LongRange(firstParam.getValueAsLong(), secondParam.getValueAsLong());
            }
            else  if (firstParam.getType() == Token.Type.CHAR) {
                if (lexer.nextToken().getType() != Token.Type.DOUBLE_DOT)
                    throw new ParseException();

                Token secondParam = lexer.nextToken();

                if (secondParam.getType() != Token.Type.CHAR)
                    throw new ParseException();

                if (lexer.nextToken().getType() != Token.Type.R_BRACKET)
                    throw new ParseException();

                return new CharRange(firstParam.getValueAsChar(), secondParam.getValueAsChar());
            }

            throw new ParseException();
        }
    }

}
