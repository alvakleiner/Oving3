package edu.ntnu.idatt2003.textmanipulation;

public class WrapSelectionTextCommand extends WrapTextCommand {
    private final String selection;

    public WrapSelectionTextCommand(String opening, String closing, String selection) {
        super(opening, closing);
        this.selection = selection;
    }

    @Override
    public String execute(String text) {
        return text.replace(this.selection,this.opening + this.selection + this.closing);
    }

    public String getSelection() {
        return selection;
    }
}