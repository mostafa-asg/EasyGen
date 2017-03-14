package com.github.generator.parser;

import com.github.generator.expersions.Expression;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mostafa Asgari
 * @since 3/14/17
 */
public class DateParser extends AbstractParser {

    public static final String PARAM_DATE_PATTERN = "yyyy-MM-dd H:m:s";
    private SimpleDateFormat parameterDateFormat = new SimpleDateFormat(PARAM_DATE_PATTERN);

    public DateParser(Lexer lexer) {
        super(lexer);
    }

    public Expression parse() throws ParseException {

        Date startDate = null;
        Date endDate = new Date();

        ensureNextTokenIs( Token.Type.L_PARENTHESE );

        try {
            startDate = parameterDateFormat.parse(readString());
        } catch (java.text.ParseException e) {
            throw new ParseException(e.getMessage());
        }

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.R_PARENTHESE ){
            return new com.github.generator.expersions.functions.Date(startDate,endDate);
        }
        else if ( token.getType() == Token.Type.COMMA ){

            String secondParam = readString();
            token = lexer.nextToken();

            if( token.getType() == Token.Type.R_PARENTHESE ){
                return new com.github.generator.expersions.functions.Date(startDate,endDate,secondParam);
            }
            else if (token.getType() == Token.Type.COMMA){

                String thirdParam = readString();
                ensureNextTokenIs(Token.Type.R_PARENTHESE);

                try {
                    endDate = parameterDateFormat.parse(secondParam);
                } catch (java.text.ParseException e) {
                    throw new ParseException(e.getMessage());
                }

                return new com.github.generator.expersions.functions.Date(startDate,endDate,thirdParam);
            }
            else{
                throw new ParseException();
            }
        }
        else{
            throw new ParseException();
        }
    }
}
