package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplaceTextCommandTest {

    @Test
    void constructorShouldSetTargetAndReplacement() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "replacement");

        assertEquals("target", cmd.getTarget());
        assertEquals("replacement", cmd.getReplacement());
    }

    @Test
    void executeShouldReplaceWholeWordAllOccurrences() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "replacement");

        String result = cmd.execute("text with target and target");

        assertEquals("text with replacement and replacement", result);
    }

    @Test
    void executeShouldNotReplaceWhenTargetIsPartOfAnotherWord() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("tar", "X");

        String result = cmd.execute("target tartar tar");

        // Only the standalone "tar" should be replaced (not "target" or "tartar")
        assertEquals("target tartar X", result);
    }

    @Test
    void executeShouldReplaceWordBeforeAndAfterPunctuation() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "replacement");

        String result = cmd.execute("target, and (target).");

        // "target" should be replaced even when followed/preceded by punctuation
        assertEquals("replacement, and (replacement).", result);
    }

    @Test
    void executeShouldBeCaseSensitiveByDefault() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "replacement");

        String result = cmd.execute("Target target TARGET");

        // Only exact lowercase "target" replaced
        assertEquals("Target replacement TARGET", result);
    }

    @Test
    void executeShouldHandleEmptyText() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "replacement");

        assertEquals("", cmd.execute(""));
    }
}