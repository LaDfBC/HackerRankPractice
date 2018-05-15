import java.util.Scanner;

public class CaesarCipher {

    static String caesarCipher(String s, int k) {
        int rotation = k % 26;
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c >= 65 && c <= 90) {
                c = (char) ((int) c + rotation);
                if(c > 90) {
                    c = (char) (c - 26);
                }
                builder.append((char)(c + rotation));
            } else if (c >= 97 && c <= 122) {
                c = (char) (c + rotation);
                if (c > 122) {
                    c = (char) (c - 26);
                }
                builder.append((char)(c + rotation));
            } else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int k = in.nextInt();
        String result = caesarCipher(s, k);
        System.out.println(result);
        in.close();
    }
}