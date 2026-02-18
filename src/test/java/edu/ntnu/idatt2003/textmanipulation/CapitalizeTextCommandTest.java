package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CapitalizeTextCommandTest {

    @Test
    void executeShouldCapitalizeFirstLetter() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("hello");

        assertEquals("Hello", result);
    }

    @Test
    void executeShouldNotModifyAlreadyCapitalizedText() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("Hello");

        assertEquals("Hello", result);
    }

    @Test
    void executeShouldOnlyCapitalizeFirstLetter() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("hello world");

        assertEquals("Hello world", result);
    }

    @Test
    void executeShouldCapitalizeFirstNorwegianLetter() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("ålesund");

        assertEquals("Ålesund", result);
    }

    @Test
    void executeShouldNotModifyRestOfText() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("hELLO wORLD");

        assertEquals("HELLO wORLD", result);
    }

    @Test
    void executeShouldHandleSingleCharacter() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("h");

        assertEquals("H", result);
    }

    @Test
    void executeShouldReturnEmptyTextWhenGivenBlank() {
        CapitalizeTextCommand cmd = new CapitalizeTextCommand();

        String result = cmd.execute("");

        assertEquals("", result);
    }
}