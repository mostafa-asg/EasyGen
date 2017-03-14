package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.Sleep;

/**
 * @author Mostafa Asgari
 * @since 3/14/17
 */
public class SleepParser extends AbstractParser {

    public SleepParser(Lexer lexer) {
        super(lexer);
    }

    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        long millis = ensureNextTokenIs(Token.Type.NUMBER).getValueAsLong();

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new Sleep( millis );
    }
}
