package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.Newline;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class NewLineParser extends AbstractParser {

    public NewLineParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);
        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new Newline();

    }

}
