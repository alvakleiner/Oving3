package edu.ntnu.idatt2003.textmanipulation;

import java.util.List;
import java.util.stream.Collectors;

public class CapitalizeWordsTextCommand extends CapitalizeTextCommand {

    @Override
    public String execute(String text) {
        List<String> words = List.of(text.trim().split("\\s+"));
        return words.stream().map(super::execute)
                .collect(Collectors.joining(" "));
    }
}
