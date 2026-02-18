package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WrapLinesTextCommandTest {

    @Test
    void constructorShouldSetOpeningAndClosing() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        assertEquals("(", cmd.getOpening());
        assertEquals(")", cmd.getClosing());
    }

    @Test
    void executeShouldWrapSingleLine() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello");

        assertEquals("(hello)", result);
    }

    @Test
    void executeShouldWrapEachLineIndividually() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertEquals("(hello)\n(world)", result);
    }

    @Test
    void executeShouldWrapMultipleLines() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("[", "]");

        String result = cmd.execute("line1\nline2\nline3");

        assertEquals("[line1]\n[line2]\n[line3]", result);
    }

    @Test
    void executeShouldWrapWithHtmlTags() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("<p>", "</p>");

        String result = cmd.execute("hello\nworld");

        assertEquals("<p>hello</p>\n<p>world</p>", result);
    }

    @Test
    void executeShouldWrapWithMultiCharacterOpeningAndClosing() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("<<", ">>");

        String result = cmd.execute("hello\nworld");

        assertEquals("<<hello>>\n<<world>>", result);
    }

    @Test
    void executeShouldWrapEmptyText() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("");

        assertEquals("()", result);
    }

    @Test
    void executeShouldWrapLinesContainingNorwegianLetters() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("blåbær\nøl");

        assertEquals("(blåbær)\n(øl)", result);
    }

    @Test
    void executeShouldPreserveNewlinesBetweenLines() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertEquals("(hello)\n(world)", result);
    }

    @Test
    void executeShouldWrapWithEmptyOpeningAndClosing() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("", "");

        String result = cmd.execute("hello\nworld");

        assertEquals("hello\nworld", result);
    }

    @Test
    void executeShouldNotWrapAllLinesAsOne() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals("(hello\nworld)", result);
    }

    @Test
    void executeShouldNotSwapOpeningAndClosing() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals(")hello(\n)world(", result);
    }

    @Test
    void executeShouldNotWrapWithWrongOpening() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals("[hello)\n[world)", result);
    }

    @Test
    void executeShouldNotWrapWithWrongClosing() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals("(hello]\n(world]", result);
    }

    @Test
    void executeShouldNotAddExtraWrapping() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals("((hello))\n((world))", result);
    }

    @Test
    void executeShouldNotRemoveNewlinesBetweenLines() {
        WrapLinesTextCommand cmd = new WrapLinesTextCommand("(", ")");

        String result = cmd.execute("hello\nworld");

        assertNotEquals("(hello)(world)", result);
    }
}
