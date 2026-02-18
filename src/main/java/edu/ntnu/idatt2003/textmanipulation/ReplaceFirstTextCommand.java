package edu.ntnu.idatt2003.textmanipulation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceFirstTextCommand extends ReplaceTextCommand {

    public ReplaceFirstTextCommand(String target, String replacement) {
        super(target, replacement);
    }

    @Override
    public String execute(String text) {
        String escapedTarget = Pattern.quote(target);
        String regex = "(?<![\\p{L}\\p{N}])" + escapedTarget + "(?![\\p{L}\\p{N}])";
        String safeReplacement = Matcher.quoteReplacement(replacement);
        return Pattern.compile(regex).matcher(text).replaceFirst(safeReplacement);
    }
}
