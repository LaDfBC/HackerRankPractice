public class BetweenSums {
    static int getTotalX(int[] a, int[] b) {
        int smallestB = 101;
        for(int i = 0; i < b.length; i++) {
            if(b[i] < smallestB) {
                smallestB = b[i];
            }
        }

        int lcdVal = lcd(a);

        int finale = 0;

        for(int i = lcdVal; i <= smallestB; i += lcdVal) {
            boolean divisible = true;
            for(int j = 0; j < b.length; j++) {
                if(b[j] % i != 0) {
                    divisible = false;
                    break;
                }
            }
            if(divisible) {
                finale++;
            }
        }

        return finale;
    }

    static int lcd(int[] divisors) {
        int retLcd = 1;
        int current = 2;

        while(true) {
            boolean divisible = false;
            int counter = 0;
            for(int i = 0; i < divisors.length; i++) {
                if(divisors[i] == 1) {
                    counter++;
                }

                if(divisors[i] % current == 0) {
                    divisible = true;
                    divisors[i] = divisors[i] / current;
                }
            }

            if(divisible) {
                retLcd *= current;
            } else {
                current++;
            }

            if(counter == divisors.length) {
                return retLcd;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 4};
        int[] b = new int[]{16, 32, 96};
        System.out.println(BetweenSums.getTotalX(a, b));
    }
}
