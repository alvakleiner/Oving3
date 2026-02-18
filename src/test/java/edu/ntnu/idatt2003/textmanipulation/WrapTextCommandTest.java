package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WrapTextCommandTest {

    @Test
    void constructorShouldSetOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        assertEquals("(", cmd.getOpening());
        assertEquals(")", cmd.getClosing());
    }

    @Test
    void executeShouldWrapTextWithOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("hello");

        assertEquals("(hello)", result);
    }

    @Test
    void executeShouldWrapTextWithMultiCharacterOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("<<", ">>");

        String result = cmd.execute("hello");

        assertEquals("<<hello>>", result);
    }

    @Test
    void executeShouldWrapEmptyText() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("");

        assertEquals("()", result);
    }

    @Test
    void executeShouldWrapWithEmptyOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("", "");

        String result = cmd.execute("hello");

        assertEquals("hello", result);
    }

    @Test
    void executeShouldWrapWithQuotes() {
        WrapTextCommand cmd = new WrapTextCommand("\"", "\"");

        String result = cmd.execute("hello");

        assertEquals("\"hello\"", result);
    }

    @Test
    void executeShouldWrapWithHtmlTags() {
        WrapTextCommand cmd = new WrapTextCommand("<b>", "</b>");

        String result = cmd.execute("hello");

        assertEquals("<b>hello</b>", result);
    }

    @Test
    void executeShouldWrapTextContainingNorwegianLetters() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("blåbær");

        assertEquals("(blåbær)", result);
    }

    @Test
    void executeShouldWrapTextContainingSpaces() {
        WrapTextCommand cmd = new WrapTextCommand("[", "]");

        String result = cmd.execute("hello world");

        assertEquals("[hello world]", result);
    }

    @Test
    void executeShouldWrapWithAsymmetricOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("START ", " END");

        String result = cmd.execute("hello");

        assertEquals("START hello END", result);
    }

    @Test
    void executeShouldNotWrapWithWrongOpening() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("hello");

        assertNotEquals("[hello)", result);
    }

    @Test
    void executeShouldNotWrapWithWrongClosing() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("hello");

        assertNotEquals("(hello]", result);
    }

    @Test
    void executeShouldNotAddExtraCharacters() {
        WrapTextCommand cmd = new WrapTextCommand("(", ")");

        String result = cmd.execute("hello");

        assertNotEquals("((hello))", result);
    }

    @Test
    void executeShouldNotSwapOpeningAndClosing() {
        WrapTextCommand cmd = new WrapTextCommand("START", "END");

        String result = cmd.execute("hello");

        assertNotEquals("ENDhelloSTART", result);
    }
}