package edu.ntnu.idatt2003.textmanipulation;

import java.util.List;
import java.util.stream.Collectors;

public class WrapLinesTextCommand extends WrapTextCommand {

    public WrapLinesTextCommand(String opening, String closing) {
        super(opening, closing);
    }

    @Override
    public String execute(String text) {
        List<String> lines = List.of(text.split("\n"));
        return lines.stream().map(line -> this.opening + line + this.closing)
                .collect(Collectors.joining("\n"));
    }
}
