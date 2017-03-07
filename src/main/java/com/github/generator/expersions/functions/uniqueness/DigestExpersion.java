package com.github.generator.expersions.functions.uniqueness;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Rep;
import com.github.generator.expersions.functions.ranges.CharRange;

/**
 * @author Mostafa Asgari
 * @since 3/7/17
 */
public abstract class DigestExpersion extends Expersion {

    @Override
    public String generate() throws Exception {

        CharRange charRange = new CharRange('a','z');
        charRange.add(new CharRange('A','Z').add(new CharRange('0','9')) );

        String randomStr = new Rep( charRange , 1 , 256 ).generate();

        return getDigest( randomStr );
    }

    protected abstract String getDigest(String data);
}
