package com.github;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.functions.*;
import com.github.generator.expersions.functions.ranges.CharRange;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.functions.ranges.StringRange;
import com.github.generator.expersions.functions.uniqueness.*;
import com.github.generator.expersions.sink.ConsoleSink;
import com.github.generator.expersions.terminals.CharTerminal;
import com.github.generator.expersions.terminals.LongTerminal;
import com.github.generator.expersions.terminals.StringTerminal;
import com.github.generator.parser.Lexer;
import com.github.generator.parser.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */

public class TestCase {

    @Test
    public void test1() throws Exception {

        String input = "Hello[1..100]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(2, expList.size());

        Assert.assertTrue(expList.get(0) instanceof StringTerminal);
        Assert.assertEquals("Hello", ((StringTerminal) expList.get(0)).getValue());

        Assert.assertTrue(expList.get(1) instanceof LongRange);
        LongRange longRange = ((LongRange) expList.get(1));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(1);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }

        String generatedStr = seqExp.generate();
        Assert.assertTrue(generatedStr.startsWith("Hello"));
        long randomNum = Long.valueOf(generatedStr.substring("Hello".length()));
        Assert.assertTrue(randomNum >= 1 && randomNum <= 100);
    }

    @Test
    public void test2() throws Exception {

        String input = "[120..140]Hello";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(2, expList.size());

        Assert.assertTrue(expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange) expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(120);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }

        Assert.assertTrue(expList.get(1) instanceof StringTerminal);
        Assert.assertEquals("Hello", ((StringTerminal) expList.get(1)).getValue());

        String generatedStr = seqExp.generate();
        Assert.assertTrue(generatedStr.length() == 8);
        Assert.assertTrue(generatedStr.endsWith("Hello"));
        long randomNum = Long.valueOf(generatedStr.substring(0, 3));
        Assert.assertTrue(randomNum >= 120 && randomNum <= 140);
    }

    @Test
    public void test3() throws Exception {

        String input = "[100..119]Hello[5..9]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(3, expList.size());

        Assert.assertTrue(expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange) expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(100);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }

        Assert.assertTrue(expList.get(1) instanceof StringTerminal);
        Assert.assertEquals("Hello", ((StringTerminal) expList.get(1)).getValue());

        Assert.assertTrue(expList.get(2) instanceof LongRange);
        longRange = ((LongRange) expList.get(2));
        it = longRange.iterator();
        num = new Long(5);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }

        String generatedStr = seqExp.generate();
        Assert.assertTrue(generatedStr.length() == 9);
        Assert.assertTrue(generatedStr.contains("Hello"));
        long randomNum = Long.valueOf(generatedStr.substring(0, 3));
        Assert.assertTrue(randomNum >= 100 && randomNum <= 119);
        randomNum = Long.valueOf(generatedStr.substring(generatedStr.length() - 1, generatedStr.length()));
        Assert.assertTrue(randomNum >= 5 && randomNum <= 9);
    }

    @Test
    public void test4() throws Exception {

        String input = "[0..43]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());

        Assert.assertTrue(expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange) expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(0);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }
    }

    @Test
    public void test5() throws Exception {

        String input = "[a..z]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());

        Assert.assertTrue(expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange) expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();

        char ch = 'a';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'z');
    }

    @Test
    public void test6() throws Exception {

        String input = "[a..c][d..g]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(2, expList.size());

        Assert.assertTrue(expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange) expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'a';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'c');

        Assert.assertTrue(expList.get(1) instanceof CharRange);
        charRange = ((CharRange) expList.get(1));
        it = charRange.iterator();
        ch = 'd';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'g');
    }

    @Test
    public void test7() throws Exception {

        String input = "[a..c][1..5][d..g][666..777]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(4, expList.size());

        //-----------------------------------------------------
        Assert.assertTrue(expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange) expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'a';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'c');
        //-----------------------------------------------------
        Assert.assertTrue(expList.get(1) instanceof LongRange);
        LongRange longRange = ((LongRange) expList.get(1));
        Iterator<LongTerminal> itLong = longRange.iterator();

        Long num = new Long(1);
        while (itLong.hasNext()) {
            Assert.assertEquals(num, itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals(new Long(5), --num);
        //-----------------------------------------------------
        Assert.assertTrue(expList.get(2) instanceof CharRange);
        charRange = ((CharRange) expList.get(2));
        it = charRange.iterator();
        ch = 'd';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'g');
        //-----------------------------------------------------
        Assert.assertTrue(expList.get(3) instanceof LongRange);
        longRange = ((LongRange) expList.get(3));
        itLong = longRange.iterator();

        num = new Long(666);
        while (itLong.hasNext()) {
            Assert.assertEquals(num, itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals(new Long(777), --num);

    }

    @Test
    public void test8() throws Exception {
        String input = "This is a sentence[A..Z].And this is another[1..7] sentence";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(11, expList.size());
        Assert.assertTrue(seqExp.getExpersions().get(4) instanceof CharRange);
        Assert.assertTrue(seqExp.getExpersions().get(9) instanceof LongRange);

        //------------------------------------------------
        CharRange charRange = ((CharRange) expList.get(4));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'A';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'Z');
        //------------------------------------------------
        LongRange longRange = ((LongRange) expList.get(9));
        Iterator<LongTerminal> itLong = longRange.iterator();

        Long num = new Long(1);
        while (itLong.hasNext()) {
            Assert.assertEquals(num, itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals(new Long(7), --num);
    }

    @Test
    public void test9() throws Exception {
        String input = "[Hello|World|Bye]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringRange);
        Iterator<StringTerminal> it = ((StringRange) expList.get(0)).iterator();

        String[] words = new String[]{"Hello", "World", "Bye"};
        int i = 0;

        while (it.hasNext()) {
            Assert.assertEquals(words[i], it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test10() throws Exception {
        String input = "[Hello|World]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringRange);
        Iterator<StringTerminal> it = ((StringRange) expList.get(0)).iterator();

        String[] words = new String[]{"Hello", "World"};
        int i = 0;

        while (it.hasNext()) {
            Assert.assertEquals(words[i], it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test11() throws Exception {
        String input = "[Hello|'Hello World.This is a test'|World|'another world']";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringRange);
        Iterator<StringTerminal> it = ((StringRange) expList.get(0)).iterator();

        String[] words = new String[]{"Hello", "Hello World.This is a test", "World", "another world"};
        int i = 0;

        while (it.hasNext()) {
            Assert.assertEquals(words[i], it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test12() throws Exception {
        String input = "['Hello|World|This|is|a|test'|World|'another world']";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringRange);
        Iterator<StringTerminal> it = ((StringRange) expList.get(0)).iterator();

        String[] words = new String[]{"Hello|World|This|is|a|test", "World", "another world"};
        int i = 0;

        while (it.hasNext()) {
            Assert.assertEquals(words[i], it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test13() throws Exception {
        String input = "'Hello World REP([a..z],6)'";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringTerminal);
        Assert.assertEquals("Hello World REP([a..z],6)", ((StringTerminal) expList.get(0)).getValue());
    }

    @Test
    public void test14() throws Exception {
        String input = "'REP([a..z],6)'";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringTerminal);
        Assert.assertEquals("REP([a..z],6)", ((StringTerminal) expList.get(0)).getValue());
    }

    @Test
    public void test15() throws Exception {

        String input = "REP([a..z],34)";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Rep rep = (Rep) expList.get(0);
        Assert.assertTrue(rep.getExpersion() instanceof SequenceExpersion);

        Assert.assertEquals(34, rep.getMinimumLength());
        Assert.assertEquals(34, rep.getMaximumLength());

        Iterator<CharTerminal> it = ((CharRange) ((SequenceExpersion) rep.getExpersion()).getExpersions().get(0)).iterator();
        char ch = 'a';
        while (it.hasNext()) {
            Assert.assertTrue(it.next().getValue() == ch);
            ++ch;
        }
        Assert.assertTrue(--ch == 'z');
    }

    @Test
    public void test16() throws Exception {

        String input = "REP([10..99],3,7)";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Rep rep = (Rep) expList.get(0);
        Assert.assertTrue(rep.getExpersion() instanceof SequenceExpersion);
        Assert.assertEquals(3, rep.getMinimumLength());
        Assert.assertEquals(7, rep.getMaximumLength());

        Iterator<LongTerminal> it = ((LongRange) ((SequenceExpersion) rep.getExpersion()).getExpersions().get(0)).iterator();

        Long num = new Long(10);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }
        Assert.assertEquals(--num, new Long(99));
    }

    @Test
    public void test17() throws Exception {

        String input = "REP(REP([Hello|World|EasyGen],2),3)";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Rep outerRep = (Rep) expList.get(0);
        Assert.assertTrue(outerRep.getExpersion() instanceof SequenceExpersion);
        Assert.assertEquals(3, outerRep.getMinimumLength());
        Assert.assertEquals(3, outerRep.getMaximumLength());

        Rep innerRep = (Rep) ((SequenceExpersion) outerRep.getExpersion()).getExpersions().get(0);
        Assert.assertTrue(innerRep.getExpersion() instanceof SequenceExpersion);
        Assert.assertEquals(2, innerRep.getMinimumLength());
        Assert.assertEquals(2, innerRep.getMaximumLength());

        Iterator<StringTerminal> it = ((StringRange) ((SequenceExpersion) innerRep.getExpersion()).getExpersions().get(0)).iterator();
        String[] words = new String[]{"Hello", "World", "EasyGen"};
        int i = 0;

        while (it.hasNext()) {
            Assert.assertEquals(words[i], it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test18() throws Exception {

        String input = "DATE()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Date);
    }

    @Test
    public void test19() throws Exception {

        String input = "DATE(yyyy-MM-dd)";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Date);
    }

    @Test
    public void test20() throws Exception {

        String input = "Hello NEW_LINE() Bye";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(3, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringTerminal);
        Assert.assertTrue(expList.get(1) instanceof Newline);
        Assert.assertTrue(expList.get(2) instanceof StringTerminal);
        String output = seqExp.generate();
        String[] split = output.split(System.lineSeparator());
        Assert.assertTrue(split.length == 2);
        Assert.assertEquals("Hello", split[0]);
        Assert.assertEquals("Bye", split[1]);
    }

    @Test
    public void test21() throws Exception {

        String input = "[1..5]+[10..15]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof LongRange);

        Iterator<LongTerminal> it = ((LongRange) expList.get(0)).iterator();
        Long num = new Long(1);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
            if (num == 6L)
                num = 10L;
        }
        Assert.assertEquals(new Long(15), --num);

    }

    @Test
    public void test22() throws Exception {

        String input = "[1..20]-[12..15]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof LongRange);

        Iterator<LongTerminal> it = ((LongRange) expList.get(0)).iterator();
        Long num = new Long(1);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
            if (num == 12L)
                num = 16L;
        }
        Assert.assertEquals(new Long(20), --num);
    }

    @Test
    public void test23() throws Exception {

        String input = "[1..5]+[10..15]-[5..10]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof LongRange);

        Iterator<LongTerminal> it = ((LongRange) expList.get(0)).iterator();
        Long num = new Long(1);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
            if (num == 5L)
                num = 11L;
        }
        Assert.assertEquals(new Long(15), --num);

    }

    @Test
    public void test24() throws Exception {

        String input = "[a..z]+[A..Z]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof CharRange);

        Iterator<CharTerminal> it = ((CharRange) expList.get(0)).iterator();
        char ch = 'a';
        while (it.hasNext()) {
            Assert.assertEquals(new Character(ch), it.next().getValue());
            ++ch;
            if (ch == ('z' + 1))
                ch = 'A';
        }
        Assert.assertEquals(new Character('Z'), new Character(--ch));
    }

    @Test
    public void test25() throws Exception {

        String input = "[a..z]+[A..Z]-[b..d]-[X..Z]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof CharRange);

        Iterator<CharTerminal> it = ((CharRange) expList.get(0)).iterator();

        List<Character> lstChar = new ArrayList<Character>();
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            lstChar.add(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            lstChar.add(ch);
        }
        lstChar.remove(new Character('b'));
        lstChar.remove(new Character('c'));
        lstChar.remove(new Character('d'));
        lstChar.remove(new Character('X'));
        lstChar.remove(new Character('Y'));
        lstChar.remove(new Character('Z'));

        int i = 0;
        while (it.hasNext()) {
            Assert.assertEquals(lstChar.get(i), it.next().getValue());
            ++i;
        }
    }

    @Test
    public void test26() throws Exception {

        String input = "REP( DATE() NEW_LINE() , 3 )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Assert.assertTrue(((Rep) expList.get(0)).getExpersion() instanceof SequenceExpersion);
        SequenceExpersion repFirstExp = (SequenceExpersion) ((Rep) expList.get(0)).getExpersion();
        Assert.assertEquals(2, repFirstExp.getExpersions().size());
        Assert.assertTrue(repFirstExp.getExpersions().get(0) instanceof Date);
        Assert.assertTrue(repFirstExp.getExpersions().get(1) instanceof Newline);
        Assert.assertEquals(3, ((Rep) expList.get(0)).getMinimumLength());
        Assert.assertEquals(3, ((Rep) expList.get(0)).getMaximumLength());

    }

    @Test
    public void test27() throws Exception {

        String input = "REP( DATE() ',He,ll,o Wor,ld,' NEW_LINE() , 3 )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Assert.assertTrue(((Rep) expList.get(0)).getExpersion() instanceof SequenceExpersion);
        SequenceExpersion repFirstExp = (SequenceExpersion) ((Rep) expList.get(0)).getExpersion();
        Assert.assertEquals(3, repFirstExp.getExpersions().size());
        Assert.assertTrue(repFirstExp.getExpersions().get(0) instanceof Date);
        Assert.assertTrue(repFirstExp.getExpersions().get(1) instanceof StringTerminal);
        Assert.assertEquals(",He,ll,o Wor,ld,", ((StringTerminal) repFirstExp.getExpersions().get(1)).getValue());
        Assert.assertTrue(repFirstExp.getExpersions().get(2) instanceof Newline);
        Assert.assertEquals(3, ((Rep) expList.get(0)).getMinimumLength());
        Assert.assertEquals(3, ((Rep) expList.get(0)).getMaximumLength());

    }

    @Test
    public void test28() throws Exception {

        String input = "Hello TAB() World";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(3, expList.size());

        Assert.assertTrue(expList.get(0) instanceof StringTerminal);
        Assert.assertEquals("Hello", ((StringTerminal) expList.get(0)).getValue());

        Assert.assertTrue(expList.get(1) instanceof Tab);
        Assert.assertEquals("\t", ((Tab) expList.get(1)).generate());

        Assert.assertTrue(expList.get(2) instanceof StringTerminal);
        Assert.assertEquals("World", ((StringTerminal) expList.get(2)).getValue());

        Assert.assertEquals("Hello\tWorld", seqExp.generate());
    }

    @Test
    public void test29() throws Exception {

        String input = "CONSOLE ( REP('HELLO WORLD' NEW_LINE() , 3) )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof ConsoleSink);

        Assert.assertTrue(((ConsoleSink) expList.get(0)).getExpersion() instanceof SequenceExpersion);
        SequenceExpersion innerConoleExp = (SequenceExpersion) ((ConsoleSink) expList.get(0)).getExpersion();
        Assert.assertEquals(1, innerConoleExp.getExpersions().size());
        Assert.assertTrue(innerConoleExp.getExpersions().get(0) instanceof Rep);

        Rep repExp = (Rep) innerConoleExp.getExpersions().get(0);
        Assert.assertTrue(repExp.getExpersion() instanceof SequenceExpersion);
        SequenceExpersion repInnerExp = (SequenceExpersion) repExp.getExpersion();
        Assert.assertEquals(2, repInnerExp.getExpersions().size());
        Assert.assertTrue(repInnerExp.getExpersions().get(0) instanceof StringTerminal);
        Assert.assertTrue(repInnerExp.getExpersions().get(1) instanceof Newline);
        Assert.assertEquals("HELLO WORLD", ((StringTerminal) repInnerExp.getExpersions().get(0)).getValue());

        String output = seqExp.generate();
        Assert.assertEquals("HELLO WORLD" + System.lineSeparator() +
                "HELLO WORLD" + System.lineSeparator() +
                "HELLO WORLD" + System.lineSeparator(), output
        );
    }

    @Test
    public void test30() throws Exception {

        String input = "DEFINE( URL AS [http|https]:// REP([a..z]+[A..Z],3,10).[com|org|net] ) URL TAB() DATE()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(7, expList.size());
        Assert.assertTrue(expList.get(0) instanceof StringRange);
        Assert.assertTrue(expList.get(1) instanceof StringTerminal);
        Assert.assertTrue(expList.get(2) instanceof Rep);
        Assert.assertTrue(expList.get(3) instanceof StringTerminal);
        Assert.assertTrue(expList.get(4) instanceof StringRange);
        Assert.assertTrue(expList.get(5) instanceof Tab);
        Assert.assertTrue(expList.get(6) instanceof Date);
    }

    @Test
    public void test31() throws Exception {

        String input = "DATE('yyyy-MM-dd H:m:s')";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();
        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Date);
    }

    @Test
    public void test32() throws Exception {

        String input = "REP(   [    10     ..     99    ]      ,       3       ,       7        )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof Rep);

        Rep rep = (Rep) expList.get(0);
        Assert.assertTrue(rep.getExpersion() instanceof SequenceExpersion);
        Assert.assertEquals(3, rep.getMinimumLength());
        Assert.assertEquals(7, rep.getMaximumLength());

        Iterator<LongTerminal> it = ((LongRange) ((SequenceExpersion) rep.getExpersion()).getExpersions().get(0)).iterator();

        Long num = new Long(10);
        while (it.hasNext()) {
            Assert.assertEquals(num, it.next().getValue());
            ++num;
        }
        Assert.assertEquals(--num, new Long(99));
    }

    @Test
    public void test33() throws Exception {
        PadLeft padLeft = new PadLeft(new StringTerminal("ABC"), 7, '*');
        PadRight padRight = new PadRight(new StringTerminal("ABC"), 8, '@');
        Assert.assertEquals("****ABC", padLeft.generate());
        Assert.assertEquals("ABC@@@@@", padRight.generate());

        padLeft = new PadLeft(new StringTerminal("ABC"), 2, '?');
        Assert.assertEquals("ABC", padLeft.generate());

        padRight = new PadRight(new StringTerminal("ABC"), 3, '-');
        Assert.assertEquals("ABC", padRight.generate());
    }

    @Test
    public void test34() throws Exception {

        String input = "PAD_LEFT( [a..c]ABC[x..z] , 8 , * )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof PadLeft);

        PadLeft padLeft = (PadLeft)expList.get(0);
        Assert.assertTrue( padLeft.getExpersion() instanceof SequenceExpersion );
        SequenceExpersion padLeftExp = (SequenceExpersion)padLeft.getExpersion();
        Assert.assertEquals( 3 , padLeftExp.getExpersions().size() );
        Assert.assertTrue( padLeftExp.getExpersions().get(0) instanceof CharRange );
        Assert.assertTrue( padLeftExp.getExpersions().get(1) instanceof StringTerminal );
        Assert.assertTrue( padLeftExp.getExpersions().get(2) instanceof CharRange );

        String output = seqExp.generate();
        Assert.assertEquals( "***" , output.substring(0,3) );
        Assert.assertTrue( output.charAt(3) == 'a' || output.charAt(3) == 'b' || output.charAt(3) == 'c' );
        Assert.assertEquals( "ABC" , output.substring(4,7) );
        Assert.assertTrue( output.charAt(7) == 'x' || output.charAt(7) == 'y' || output.charAt(7) == 'z' );

    }

    @Test
    public void test35() throws Exception {

        String input = "PAD_RIGHT( [a..c]ABC[x..z] , 8 , * )";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1, expList.size());
        Assert.assertTrue(expList.get(0) instanceof PadRight);

        PadRight padRight = (PadRight) expList.get(0);
        Assert.assertTrue( padRight.getExpersion() instanceof SequenceExpersion );
        SequenceExpersion padRightExp = (SequenceExpersion)padRight.getExpersion();
        Assert.assertEquals( 3 , padRightExp.getExpersions().size() );
        Assert.assertTrue( padRightExp.getExpersions().get(0) instanceof CharRange );
        Assert.assertTrue( padRightExp.getExpersions().get(1) instanceof StringTerminal );
        Assert.assertTrue( padRightExp.getExpersions().get(2) instanceof CharRange );

        String output = seqExp.generate();
        Assert.assertTrue( output.charAt(0) == 'a' || output.charAt(0) == 'b' || output.charAt(0) == 'c' );
        Assert.assertEquals( "ABC" , output.substring(1,4) );
        Assert.assertTrue( output.charAt(4) == 'x' || output.charAt(4) == 'y' || output.charAt(4) == 'z' );
        Assert.assertEquals( "***" , output.substring(5) );
    }

    @Test
    public void test36() throws Exception {

        Identity.destroy();
        String input = "IDENTITY(1,1) IDENTITY() IDENTITY() IDENTITY() IDENTITY()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals("12345" , seqExp.generate());
    }

    @Test
    public void test37() throws Exception {

        Identity.destroy();
        String input = "IDENTITY(120,2)' 'IDENTITY()' 'IDENTITY()' 'IDENTITY()' 'IDENTITY()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals("120 122 124 126 128" , seqExp.generate());
    }

    @Test
    public void test38() throws Exception {

        String input = "MD5()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1 , expList.size());
        Assert.assertTrue( expList.get(0) instanceof MD5);
        Assert.assertEquals(32 , seqExp.generate().length() );
    }

    @Test
    public void test39() throws Exception {

        String input = "MD2()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1 , expList.size());
        Assert.assertTrue( expList.get(0) instanceof MD2);
        Assert.assertEquals(32 , seqExp.generate().length() );
    }

    @Test
    public void test40() throws Exception {

        String input = "SHA1()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1 , expList.size());
        Assert.assertTrue( expList.get(0) instanceof SHA1);
        Assert.assertEquals(40 , seqExp.generate().length() );
    }

    @Test
    public void test41() throws Exception {

        String input = "SHA256()";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals(1 , expList.size());
        Assert.assertTrue( expList.get(0) instanceof SHA256);
        Assert.assertEquals(64 , seqExp.generate().length() );
    }
}