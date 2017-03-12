package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.Tab;

/**
 * Created by Mostafa on 02/26/2017.
 */
public class TabParser extends AbstractParser {

    public TabParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);
        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new Tab();
    }
}
