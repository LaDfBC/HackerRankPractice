import java.util.ArrayList;
import java.util.List;

//LeetCode.com, #68
public class Justification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int length = words.length;
        if(length == 0) {
            return new ArrayList<>();
        }

        List<String> output = new ArrayList<>();

        List<String> wordsInLine = new ArrayList<>();
        int currentLineLength = 0;
        for(int i = 0; i < length; i++) {
            int currentWordLength = words[i].length();
            if(currentWordLength + currentLineLength > maxWidth) {
                 output.add(createLine(wordsInLine, maxWidth, currentLineLength, false));
                 wordsInLine = new ArrayList<>();
                 currentLineLength = currentWordLength + 1;
            } else {
                currentLineLength += currentWordLength + 1;
            }
            wordsInLine.add(words[i]);
        }

        output.add(createLine(wordsInLine, maxWidth, currentLineLength, true));

        return output;
    }

    private String createLine(List<String> wordsInLine,
                              int maxWidth,
                              int currentLineLength,
                              boolean lastLine) {
        StringBuilder builder = new StringBuilder();
        int size = wordsInLine.size();
        if(lastLine) {
            for(int i = 0; i < wordsInLine.size() - 1; i++) {
                builder.append(wordsInLine.get(i));
                builder.append(" ");
            }
            builder.append(wordsInLine.get(size - 1));
            while(builder.length() < maxWidth) {
                builder.append(" ");
            }
        } else {
            if (size < 3) {
                builder.append(wordsInLine.get(0));
                for (int i = currentLineLength - (size); i < maxWidth; i++) {
                    builder.append(" ");
                }
                if (size == 2) {
                    builder.append(wordsInLine.get(1));
                }
            } else {
                int wordIndex = 0;
                for (int i = currentLineLength - (size); i < maxWidth; i++) {
                    wordsInLine.set(wordIndex, wordsInLine.get(wordIndex).concat(" "));
                    if (wordIndex == size - 2) {
                        wordIndex = 0;
                    } else {
                        wordIndex++;
                    }
                }

                for (String word : wordsInLine) {
                    builder.append(word);
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Justification justification = new Justification();
        String[] words = {"examle", "massive", "justify"};
        System.out.println(justification.fullJustify(words, 8));

        String[] wordsFinal = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(justification.fullJustify(wordsFinal, 16));

        String[] wordsLastLine = {"What","must","be","shall","be."};
        System.out.println(justification.fullJustify(wordsLastLine, 12));
    }
}
