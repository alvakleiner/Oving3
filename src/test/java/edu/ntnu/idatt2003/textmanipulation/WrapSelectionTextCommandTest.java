package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WrapSelectionTextCommandTest {

    @Test
    void constructorShouldSetOpeningClosingAndSelection() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        assertEquals("(", cmd.getOpening());
        assertEquals(")", cmd.getClosing());
        assertEquals("hello", cmd.getSelection());
    }

    @Test
    void executeShouldWrapSelectionInText() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "world");

        String result = cmd.execute("hello world");

        assertEquals("hello (world)", result);
    }

    @Test
    void executeShouldWrapAllOccurrencesOfSelection() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello and hello");

        assertEquals("(hello) and (hello)", result);
    }

    @Test
    void executeShouldWrapSelectionWithMultiCharacterOpeningAndClosing() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("<<", ">>", "hello");

        String result = cmd.execute("hello world");

        assertEquals("<<hello>> world", result);
    }

    @Test
    void executeShouldWrapSelectionWithHtmlTags() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("<b>", "</b>", "hello");

        String result = cmd.execute("hello world");

        assertEquals("<b>hello</b> world", result);
    }

    @Test
    void executeShouldHandleEmptyText() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("");

        assertEquals("", result);
    }

    @Test
    void executeShouldNotModifyTextWhenSelectionNotFound() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "xyz");

        String result = cmd.execute("hello world");

        assertEquals("hello world", result);
    }

    @Test
    void executeShouldWrapSelectionContainingNorwegianLetters() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "blåbær");

        String result = cmd.execute("Jeg liker blåbær");

        assertEquals("Jeg liker (blåbær)", result);
    }

    @Test
    void executeShouldWrapSelectionWithQuotes() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("\"", "\"", "hello");

        String result = cmd.execute("hello world");

        assertEquals("\"hello\" world", result);
    }

    @Test
    void executeShouldNotWrapNonSelectedWords() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello world");

        assertNotEquals("hello (world)", result);
    }

    @Test
    void executeShouldNotSwapOpeningAndClosing() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello world");

        assertNotEquals(")hello( world", result);
    }

    @Test
    void executeShouldNotWrapWithWrongOpening() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello world");

        assertNotEquals("[hello) world", result);
    }

    @Test
    void executeShouldNotWrapWithWrongClosing() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello world");

        assertNotEquals("(hello] world", result);
    }

    @Test
    void executeShouldNotAddExtraWrapping() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("hello world");

        assertNotEquals("((hello)) world", result);
    }

    @Test
    void executeShouldBeCaseSensitive() {
        WrapSelectionTextCommand cmd = new WrapSelectionTextCommand("(", ")", "hello");

        String result = cmd.execute("Hello hello HELLO");

        // Only exact lowercase "hello" should be wrapped
        assertNotEquals("(Hello) (hello) (HELLO)", result);
    }
}