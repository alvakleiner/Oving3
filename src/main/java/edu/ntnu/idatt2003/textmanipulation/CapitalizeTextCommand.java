package edu.ntnu.idatt2003.textmanipulation;

public class CapitalizeTextCommand implements TextCommand {

    @Override
    public String execute(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
