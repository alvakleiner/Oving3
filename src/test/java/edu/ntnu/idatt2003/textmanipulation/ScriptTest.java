package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScriptTest {

    @Test
    void executeShouldReturnOriginalTextWhenNoCommands() {
        Script script = new Script(List.of());

        String result = script.execute("hello");

        assertEquals("hello", result);
    }

    @Test
    void executeShouldApplyCommandsInOrder() {
        Script script = new Script(List.of(
                new CapitalizeTextCommand(),
                new ReplaceTextCommand("Hei", "Hallo"),
                new WrapTextCommand("<p>", "</p>")
        ));

        String result = script.execute("hei");

        assertEquals("<p>Hallo</p>", result);
    }

    @Test
    void executeShouldUseResultFromPreviousCommandAsInputToNext() {
        Script script = new Script(List.of(
                new ReplaceTextCommand("hello", "hi"),
                new ReplaceTextCommand("hi", "yo")
        ));

        String result = script.execute("hello");

        assertEquals("yo", result);
    }

    @Test
    void executeShouldWorkWithSingleCommand() {
        Script script = new Script(List.of(
                new WrapTextCommand("[", "]")
        ));

        String result = script.execute("text");

        assertEquals("[text]", result);
    }
}