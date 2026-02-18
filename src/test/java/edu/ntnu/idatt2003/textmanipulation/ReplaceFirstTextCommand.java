package edu.ntnu.idatt2003.textmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplaceFirstTextCommandTest {

    @Test
    void constructorShouldSetTargetAndReplacement() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "replacement");

        assertEquals("target", cmd.getTarget());
        assertEquals("replacement", cmd.getReplacement());
    }

    @Test
    void executeShouldReplaceOnlyFirstOccurrence() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "replacement");

        String result = cmd.execute("text with target and target");

        assertEquals("text with replacement and target", result);
    }

    @Test
    void executeShouldNotReplaceWhenTargetIsPartOfAnotherWord() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("tar", "X");

        String result = cmd.execute("target tartar tar");

        // Only the first standalone "tar" should be replaced
        assertEquals("target tartar X", result);
    }

    @Test
    void executeShouldReplaceWordBeforeAndAfterPunctuation() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "replacement");

        String result = cmd.execute("target, and (target).");

        // Only the first "target" replaced, even when surrounded by punctuation
        assertEquals("replacement, and (target).", result);
    }

    @Test
    void executeShouldBeCaseSensitiveByDefault() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "replacement");

        String result = cmd.execute("Target target TARGET");

        // Only the first exact lowercase "target" replaced
        assertEquals("Target replacement TARGET", result);
    }

    @Test
    void executeShouldHandleEmptyText() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "replacement");

        assertEquals("", cmd.execute(""));
    }

    @Test
    void executeShouldReplaceFirstNorwegianWordOnly() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("blåbær", "jordbær");

        String result = cmd.execute("Jeg liker blåbær og blåbær.");

        assertEquals("Jeg liker jordbær og blåbær.", result);
    }

    @Test
    void executeShouldNotReplaceWhenNorwegianTargetIsPartOfAnotherWord() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("øl", "saft");

        String result = cmd.execute("ølsmaking øl ølglass");

        // Only the first standalone "øl" replaced
        assertEquals("ølsmaking saft ølglass", result);
    }

    @Test
    void executeShouldReplaceFirstOccurrenceBeforeAndAfterPunctuationWithNorwegianLetters() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("Ålesund", "Trondheim");

        String result = cmd.execute("(Ålesund), Ålesund. \"Ålesund\"!");

        assertEquals("(Trondheim), Ålesund. \"Ålesund\"!", result);
    }

    @Test
    void executeShouldHandleTargetContainingRegexSpecialCharacters_plus() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("C++", "CPP");

        String result = cmd.execute("Jeg liker C++ og C++.");

        assertEquals("Jeg liker CPP og C++.", result);
    }

    @Test
    void executeShouldHandleTargetContainingRegexSpecialCharacters_dot() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("a.b", "X");

        String result = cmd.execute("a.b aXb a.b.");

        // Only first literal "a.b" replaced, not "aXb"
        assertEquals("X aXb a.b.", result);
    }

    @Test
    void executeShouldHandleTargetContainingParentheses() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("(test)", "OK");

        String result = cmd.execute("Dette er (test) og (test).");

        assertEquals("Dette er OK og (test).", result);
    }

    @Test
    void executeShouldAllowReplacementContainingDollarSign() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "$1");

        String result = cmd.execute("target target");

        assertEquals("$1 target", result);
    }

    @Test
    void executeShouldAllowReplacementContainingBackslash() {
        ReplaceFirstTextCommand cmd = new ReplaceFirstTextCommand("target", "\\path\\to\\file");

        String result = cmd.execute("target target!");

        assertEquals("\\path\\to\\file target!", result);
    }
}