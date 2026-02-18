package edu.ntnu.idatt2003.textmanipulation;

import java.util.List;

public class Script {
    private final List<TextCommand> textCommands;

    public Script (List<TextCommand> textCommands) {
        this.textCommands = textCommands;
    }

    public String execute(String text) {
        String result = text;

        for (TextCommand command : textCommands) {
            result = command.execute(result);
        }

        return result;
    }
}
