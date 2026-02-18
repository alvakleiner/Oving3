package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CapitalizeWordsTextCommandTest {

    @Test
    void executeShouldCapitalizeFirstLetterOfEachWord() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("hello world");

        assertEquals("Hello World", result);
    }

    @Test
    void executeShouldHandleSingleWord() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("hello");

        assertEquals("Hello", result);
    }

    @Test
    void executeShouldNotModifyAlreadyCapitalizedWords() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("Hello World");

        assertEquals("Hello World", result);
    }

    @Test
    void executeShouldHandleMultipleWords() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("the quick brown fox");

        assertEquals("The Quick Brown Fox", result);
    }

    @Test
    void executeShouldCapitalizeNorwegianWords() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("ålesund er en by");

        assertEquals("Ålesund Er En By", result);
    }

    @Test
    void executeShouldHandleExtraWhitespaceBetweenWords() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("hello   world");

        assertEquals("Hello World", result);
    }

    @Test
    void executeShouldHandleLeadingAndTrailingWhitespace() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("  hello world  ");

        assertEquals("Hello World", result);
    }

    @Test
    void executeShouldNotCapitalizeAllLetters() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("hello world");

        assertNotEquals("HELLO WORLD", result);
    }

    @Test
    void executeShouldReturnEmptyTextWhenGivenBlank() {
        CapitalizeWordsTextCommand cmd = new CapitalizeWordsTextCommand();

        String result = cmd.execute("");

        assertEquals("", result);
    }
}