package com.github.generator.expersions.functions.uniqueness;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Rep;
import com.github.generator.expersions.functions.ranges.CharRange;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Mostafa Asgari
 * @since 3/7/17
 */
public class MD2 extends Expersion {

    @Override
    public String generate() throws Exception {

        CharRange charRange = new CharRange('a','z');
        charRange.add(new CharRange('A','Z').add(new CharRange('0','9')) );

        String randomStr = new Rep( charRange , 1 , 256 ).generate();

        return DigestUtils.md2Hex( randomStr );
    }
}
