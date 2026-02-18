package edu.ntnu.idatt2003.textmanipulation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTextCommand implements TextCommand {
    protected String target;
    protected String replacement;

    public ReplaceTextCommand(String target, String replacement) {
        this.target = target;
        this.replacement = replacement;
    }

    @Override
    public String execute(String text) {
        // Pattern.quote() wraps the target in \Q...\E,
        // which tells the regex engine to treat everything inside as literal characters
        String escapedTarget = Pattern.quote(target);

        // (?<![\p{L}\p{N}]) means the character to the left must not be a letter or digit
        // (?![\p{L}\p{N}])  means the character to the right must not be a letter or digit
        // So: only match (for later in "return") escapedTarget when it is not surrounded by letters or digits on either side
        String regex = "(?<![\\p{L}\\p{N}])" + escapedTarget + "(?![\\p{L}\\p{N}])";

        // Escape the replacement so $1 and \ are treated literally
        String safeReplacement = Matcher.quoteReplacement(replacement);

        // compile the regex  →  scan the text for matches  →  replace all matches
        return Pattern.compile(regex).matcher(text).replaceAll(safeReplacement);
    }

    public String getTarget() {
        return target;
    }

    public String getReplacement() {
        return replacement;
    }
}
