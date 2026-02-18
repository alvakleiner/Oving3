package edu.ntnu.idatt2003.textmanipulation;

public class CapitalizeSelectionTextCommand extends CapitalizeTextCommand {
    private final String selection;

    public CapitalizeSelectionTextCommand(String selection) {
        this.selection = selection.trim();
    }

    @Override
    public String execute(String text) {
        return text.replace(selection, super.execute(selection));
    }

    public String getSelection() {
        return selection;
    }
}
