package edu.ntnu.idatt2003.textmanipulation;

public class WrapTextCommand implements TextCommand {
    protected String opening;
    protected String closing;

    public WrapTextCommand(String opening, String closing) {
        this.opening = opening;
        this.closing = closing;
    }

    @Override
    public String execute(String text) {
        return this.opening + text + this.closing;
    }

    public String getOpening() {
        return opening;
    }

    public String getClosing() {
        return closing;
    }
}
