package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.ranges.CharRange;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.functions.ranges.Range;
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
           return parseRange();
        }
    }

    private Range parseRange() throws ParseException {

        int tempPos = lexer.getCurrentPosition();
        Token firstParam = lexer.nextToken();

        if (firstParam.getType() == Token.Type.NUMBER ||
                (firstParam.getType() == Token.Type.PLUS || firstParam.getType() == Token.Type.MINUS
                        && lexer.nextToken().getType() == Token.Type.NUMBER)
        ){
            lexer.seek( tempPos );
            return parseLongRange(null,null);
        }
        else  if (firstParam.getType() == Token.Type.CHAR) {
            lexer.seek( tempPos );
            return parseCharRange(null,null);
        }

        throw new ParseException();

    }

    private LongRange parseLongRange(LongRange prevLongRange , Token.Type operatorType) throws ParseException {
        Token firstParam = parseSignedNumber();
        ensureNextTokenIs( Token.Type.DOUBLE_DOT );
        Token secondParam = parseSignedNumber();
        ensureNextTokenIs( Token.Type.R_BRACKET);

        LongRange currentRange = new LongRange(firstParam.getValueAsLong(), secondParam.getValueAsLong());
        if( currentRange != null && operatorType != null ){
            if( operatorType == Token.Type.PLUS ){
                prevLongRange.add( currentRange );
            }
            else if( operatorType == Token.Type.MINUS ){
                prevLongRange.addExclude(currentRange);
            }
            else{
                throw new ParseException("unsupported operator :" + operatorType.name());
            }
        }
        else{
            prevLongRange = currentRange;
        }

        int tempPos = lexer.getCurrentPosition();
        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.PLUS ){
            ensureNextTokenIs(Token.Type.L_BRACKET);
            parseLongRange( prevLongRange, Token.Type.PLUS );
        }
        else if( token.getType() == Token.Type.MINUS ){
            ensureNextTokenIs(Token.Type.L_BRACKET);
            parseLongRange( prevLongRange, Token.Type.MINUS );
        }
        else{
            lexer.seek(tempPos);
        }

        return currentRange;
    }

    private CharRange parseCharRange(CharRange prevCharRange , Token.Type operatorType) throws ParseException {
        Token firstParam = ensureNextTokenIs(Token.Type.CHAR);
        ensureNextTokenIs( Token.Type.DOUBLE_DOT );
        Token secondParam = ensureNextTokenIs(Token.Type.CHAR);
        ensureNextTokenIs( Token.Type.R_BRACKET);

        CharRange currentRange = new CharRange(firstParam.getValueAsChar(), secondParam.getValueAsChar());
        if( currentRange != null && operatorType != null ){
            if( operatorType == Token.Type.PLUS ){
                prevCharRange.add( currentRange );
            }
            else if( operatorType == Token.Type.MINUS ){
                prevCharRange.addExclude(currentRange);
            }
            else{
                throw new ParseException("unsupported operator :" + operatorType.name());
            }
        }
        else{
            prevCharRange = currentRange;
        }


        int tempPos = lexer.getCurrentPosition();
        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.PLUS ){
            ensureNextTokenIs(Token.Type.L_BRACKET);
            parseCharRange(prevCharRange, Token.Type.PLUS);
        }
        else if( token.getType() == Token.Type.MINUS ){
            ensureNextTokenIs(Token.Type.L_BRACKET);
            parseCharRange(prevCharRange, Token.Type.MINUS);
        }
        else{
            lexer.seek(tempPos);
        }

        return currentRange;
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
