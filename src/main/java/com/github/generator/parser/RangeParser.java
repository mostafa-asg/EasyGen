package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.ranges.CharRange;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.functions.ranges.StringRange;
import com.github.generator.expersions.terminals.StringTerminal;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class RangeParser extends AbstractParser {

    public RangeParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expersion parse() throws ParseException {
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


}
