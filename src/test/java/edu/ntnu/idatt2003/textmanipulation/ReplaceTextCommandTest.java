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

    @Test
    void executeShouldReplaceNorwegianLettersAsWholeWord() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("blåbær", "jordbær");

        String result = cmd.execute("Jeg liker blåbær og blåbær.");

        assertEquals("Jeg liker jordbær og jordbær.", result);
    }

    @Test
    void executeShouldNotReplaceWhenNorwegianTargetIsPartOfAnotherWord() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("øl", "saft");

        String result = cmd.execute("ølsmaking øl ølglass");

        // Only standalone "øl" replaced
        assertEquals("ølsmaking saft ølglass", result);
    }

    @Test
    void executeShouldReplaceBeforeAndAfterPunctuationWithNorwegianLetters() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("Ålesund", "Trondheim");

        String result = cmd.execute("(Ålesund), Ålesund. \"Ålesund\"!");

        assertEquals("(Trondheim), Trondheim. \"Trondheim\"!", result);
    }

    @Test
    void executeShouldHandleTargetContainingRegexSpecialCharacters_plus() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("C++", "CPP");

        String result = cmd.execute("Jeg liker C++ og C++.");

        assertEquals("Jeg liker CPP og CPP.", result);
    }

    @Test
    void executeShouldHandleTargetContainingRegexSpecialCharacters_dot() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("a.b", "X");

        String result = cmd.execute("a.b aXb a.b.");

        // Only literal "a.b" replaced, not "aXb"
        assertEquals("X aXb X.", result);
    }

    @Test
    void executeShouldHandleTargetContainingParentheses() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("(test)", "OK");

        String result = cmd.execute("Dette er (test) og (test).");

        assertEquals("Dette er OK og OK.", result);
    }

    @Test
    void executeShouldAllowReplacementContainingDollarSign() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "$1");

        String result = cmd.execute("target target");

        // Without quoteReplacement, $1 would be treated as a regex group reference and can crash/behave oddly
        assertEquals("$1 $1", result);
    }

    @Test
    void executeShouldAllowReplacementContainingBackslash() {
        ReplaceTextCommand cmd = new ReplaceTextCommand("target", "\\path\\to\\file");

        String result = cmd.execute("target!");

        assertEquals("\\path\\to\\file!", result);
    }
}