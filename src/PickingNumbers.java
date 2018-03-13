public class PickingNumbers {
    static int pickingNumbers(int[] a) {
        int[] vals = new int[100];

        for(int i = 0; i < a.length; i++) {
            vals[a[i]]++;
        }

        int max = 0;

        for(int i = 0; i < vals.length - 1; i++) {
            max = Math.max(max, vals[i] + vals[i + 1]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 2, 3, 1, 2};
        System.out.println(pickingNumbers(n));
    }
}
