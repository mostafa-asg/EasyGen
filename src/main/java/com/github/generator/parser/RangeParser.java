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

                ensureNextTokenIs( Token.Type.DOUBLE_DOT );
                Token secondParam = ensureNextTokenIs(Token.Type.NUMBER);
                ensureNextTokenIs( Token.Type.R_BRACKET);

                return new LongRange(firstParam.getValueAsLong(), secondParam.getValueAsLong());
            }
            else  if (firstParam.getType() == Token.Type.CHAR) {

                ensureNextTokenIs( Token.Type.DOUBLE_DOT );
                Token secondParam = ensureNextTokenIs(Token.Type.CHAR);
                ensureNextTokenIs( Token.Type.R_BRACKET);

                return new CharRange(firstParam.getValueAsChar(), secondParam.getValueAsChar());
            }

            throw new ParseException();
        }
    }

    private StringRange parseRangePipeDelimiter() throws ParseException{

        StringRange result = new StringRange();
        result.addItem( new StringTerminal(readString()) );

        ensureNextTokenIs(Token.Type.PIPE);
        result.addItem( new StringTerminal(readString()) );

        while (lexer.nextToken().getType() == Token.Type.PIPE){
            result.addItem( new StringTerminal(readString()) );
        }

        ensureCurrentTokenIs(Token.Type.R_BRACKET);

        return result;
    }


}
