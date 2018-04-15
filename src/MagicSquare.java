import java.util.Scanner;

public class MagicSquare {
    static int formingMagicSquare(int[][] s) {
        int x = 15;
        int subtotal = 0;
        int total = 0;
        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[0][i];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[1][i];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[2][i];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        int halfTotal = total;
        total = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[i][0];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[i][1];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[i][2];
        }

        total += Math.abs(x - subtotal);

        halfTotal = Math.max(total, halfTotal);
        total = 0;
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[i][i];
        }

        total += Math.abs(x - subtotal);
        subtotal = 0;

        for(int i = 0 ; i <= 2; i ++) {
            subtotal += s[i][2 - i];
        }
        total += Math.abs(x - subtotal);

        return Math.max(total, halfTotal);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] s = new int[3][3];
        for(int s_i = 0; s_i < 3; s_i++){
            for(int s_j = 0; s_j < 3; s_j++){
                s[s_i][s_j] = in.nextInt();
            }
        }
        int result = formingMagicSquare(s);
        System.out.println(result);
        in.close();
    }
}
