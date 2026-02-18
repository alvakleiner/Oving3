package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CapitalizeSelectionTextCommandTest {

    @Test
    void constructorShouldSetSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        assertEquals("hello", cmd.getSelection());
    }

    @Test
    void constructorShouldTrimSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("  hello  ");

        assertEquals("hello", cmd.getSelection());
    }

    @Test
    void executeShouldCapitalizeSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        String result = cmd.execute("hello world");

        assertEquals("Hello world", result);
    }

    @Test
    void executeShouldCapitalizeAllOccurrencesOfSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        String result = cmd.execute("hello and hello");

        assertEquals("Hello and Hello", result);
    }

    @Test
    void executeShouldNotModifyTextWhenSelectionNotFound() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("xyz");

        String result = cmd.execute("hello world");

        assertEquals("hello world", result);
    }

    @Test
    void executeShouldNotModifyAlreadyCapitalizedSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("Hello");

        String result = cmd.execute("Hello world");

        assertEquals("Hello world", result);
    }

    @Test
    void executeShouldCapitalizeNorwegianSelection() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("ålesund");

        String result = cmd.execute("jeg bor i ålesund");

        assertEquals("jeg bor i Ålesund", result);
    }

    @Test
    void executeShouldHandleEmptyText() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        String result = cmd.execute("");

        assertEquals("", result);
    }

    @Test
    void executeShouldNotModifyRestOfText() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        String result = cmd.execute("hello WORLD");

        assertEquals("Hello WORLD", result);
    }

    @Test
    void executeShouldBeCaseSensitive() {
        CapitalizeSelectionTextCommand cmd = new CapitalizeSelectionTextCommand("hello");

        String result = cmd.execute("Hello HELLO hello");

        assertNotEquals("Hello Hello Hello", result);
    }
}
