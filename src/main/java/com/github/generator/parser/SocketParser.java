package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.sink.Socket;

/**
 * @author Mostafa Asgari
 * @since 3/12/17
 */
public class SocketParser extends AbstractParser {

    public SocketParser(Lexer lexer) {
        super(lexer);
    }

    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Expression firstParam = parseUntilNextComma();

        ensureNextTokenIs(Token.Type.COMMA);

        String address = ensureNextTokenIs(Token.Type.STRING).getValue();

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new Socket( firstParam , address );
    }

}
