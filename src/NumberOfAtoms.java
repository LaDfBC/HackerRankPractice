import java.util.*;

class NumberOfAtoms {
    private final List<Character> NUMBERS = Arrays.asList('0','1', '2', '3','4','5','6','7','8','9');
    private TreeMap<String, Integer> eleCounts = new TreeMap<>();

    public String countOfAtoms(String formula) {
        countHelper(formula, 1);

        System.out.println(eleCounts);
        //WE'RE DONE! ORGANIZE OUTPUT!
        Set set = eleCounts.entrySet();
        Iterator i = set.iterator();

        StringBuilder output = new StringBuilder();
        while(i.hasNext()) {
            Map.Entry current = (Map.Entry)i.next();
            output.append(current.getKey());
            if((Integer) current.getValue() != 1) {
                output.append(current.getValue());
            }
        }

        return output.toString();
    }

    private Integer countHelper(String formula, Integer multiplier) {
        int index = formula.length() - 1;
        StringBuilder numberString = new StringBuilder();
        StringBuilder chemical = new StringBuilder();
        while(index > -1) {
            char currentChar = formula.charAt(index);
            if(NUMBERS.contains(formula.charAt(index))) {
                numberString.insert(0, currentChar);
            } else if(currentChar == ')') {
                index = countHelper(formula.substring(0, index),
                        calculateValue(multiplier, numberString));
                numberString = new StringBuilder();
            } else if(currentChar == '(') {
                return index;
            } else { //It's a chemical that's next here
                if(currentChar >= 97 && currentChar <= 122) { //LOWERCASE
                    chemical.append(currentChar);
                } else { //UPPERCASE
                    chemical.insert(0, currentChar);
                    String chemString = chemical.toString();
                    Integer value = calculateValue(multiplier, numberString);

                    if(eleCounts.get(chemString) == null) {
                        eleCounts.put(chemString, value);
                    } else {
                        eleCounts.put(chemString, eleCounts.get(chemString) + value);
                    }
                    numberString = new StringBuilder();
                    chemical = new StringBuilder();
                }
            }
            index--;
        }
        return index;
    }

    private Integer calculateValue(Integer multiplier, StringBuilder numberString) {
        String number = numberString.toString();
        if(number.isEmpty()) {
            return multiplier;
        } else {
            return multiplier * Integer.valueOf(number);
        }
    }

    public static void main(String[] args) {
        NumberOfAtoms numberOfAtoms = new NumberOfAtoms();
        System.out.println(numberOfAtoms.countOfAtoms("H50"));
    }
}