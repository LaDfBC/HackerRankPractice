public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int oneLength = num1.length();
        int twoLength = num2.length();

        int[] first_arr = new int[oneLength];
        int[] second_arr = new int[twoLength];
        int max = Math.max(oneLength * 2, twoLength * 2);
        int[] total = new int[max];
        int[] subtotal = new int[max];
        int[] emptySub = subtotal.clone();

        //Convert Strings to integer arrays
        for(int i = 0; i < oneLength; i++) {
            first_arr[oneLength - i - 1] = num1.charAt(i) - 48;
        }

        for(int j = 0; j < twoLength; j++) {
            second_arr[twoLength - j - 1] = num2.charAt(j) - 48;
        }

        //Main multiplication loop
        for(int i = 0; i < oneLength; i++) {
            for(int j = 0; j < twoLength; j++) {
                int value = (first_arr[i] * second_arr[j]) + subtotal[i + j];
                subtotal[i + j] = value % 10;
                int current = (value % 100) / 10;
                if(current != 0) {
                    subtotal[i + j + 1] = current;
                }

                current = (value % 1000) / 100;
                if(current != 0) {
                    subtotal[i + j + 2] = current;
                }
            }

            for(int k = 0; k < subtotal.length; k++) {
                int both = total[k] + subtotal[k];
                int carry = both - 10;
                if(carry >= 0) {
                    total[k] = carry;
                    total[k + 1] =  1 + total[k + 1];
                } else {
                    total[k] = both;
                }
            }
            subtotal = emptySub.clone();
        }

        StringBuilder output = new StringBuilder();
        for(int k = 0; k < total.length; k++) {
            output.insert(0, total[k]);
        }

        String current = output.toString();
        for(int i = 0; i < current.length(); i++) {
            if(current.charAt(i) != '0') {
                return current.substring(i);
            }
        }

        return "0";
    }

    public static void main(String[] args) {
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("99", "9"));
    }
}
