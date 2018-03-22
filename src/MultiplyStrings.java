import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int oneLength = num1.length();
        int twoLength = num2.length();

        int[] first_arr = new int[oneLength];
        int[] second_arr = new int[twoLength];
        List<Integer> total = new ArrayList<Integer>();
        List<Integer> subtotal = new ArrayList<>();
        for(int i = 0; i < Math.max(oneLength + 3, twoLength + 3); i++) {
            total.add(0);
            subtotal.add(0);
        }

        //Convert Strings to integer arrays
        for(int i = 0; i < oneLength; i++) {
            first_arr[oneLength - i - 1] = num1.charAt(i) - 48;
        }

        for(int j = 0; j < twoLength; j++) {
            second_arr[twoLength - j - 1] = num2.charAt(j) - 48;
        }

        for(int i = 0; i < oneLength; i++) {
            for(int j = 0; j < twoLength; j++) {
                while(subtotal.size() < i + j + 2) {
                    subtotal.add(0);
                }
                int value = (first_arr[i] * second_arr[j]) + subtotal.get(i + j);
                subtotal.set(i + j, value % 10);
                int current = (value % 100) / 10;
                if(current != 0) {
                    if (subtotal.size() <= i + 1) {
                        subtotal.add(i + j + 1, current);
                    } else {
                        subtotal.set(i + j + 1, current);
                    }
                }

                current = (value % 1000) / 100;
                if(current != 0) {
                    if (subtotal.size() <= i + 2) {
                        subtotal.add(i + j + 2, current);
                    } else {
                        subtotal.set(i + j + 2, current);
                    }
                }
            }
            while(total.size() < subtotal.size() + 1) {
                total.add(0);
            }
            for(int k = 0; k < subtotal.size(); k++) {
                int both = total.get(k) + subtotal.get(k);
                int carry = both - 10;
                if(carry >= 0) {
                    total.set(k, both - 10);
                    total.set(k + 1, 1 + total.get(k + 1));
                } else {
                    total.set(k, both);
                }
            }
            setSubtotal(subtotal);
        }

        StringBuilder output = new StringBuilder();
        for(int k = 0; k < total.size(); k++) {
            output.insert(0, total.get(k));
        }

        String current = output.toString();
        for(int i = 0; i < current.length(); i++) {
            if(current.charAt(i) != '0') {
                return current.substring(i);
            }
        }

        return "0";
    }

    private void setSubtotal(List<Integer> subtotal) {
        for(int i = 0; i < subtotal.size(); i++) {
            subtotal.set(i, 0);
        }
    }

    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("123456789", "987654321"));
    }
}
