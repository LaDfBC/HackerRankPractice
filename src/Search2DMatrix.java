public class Search2DMatrix {
    int[][] matrix;
    int rows;
    int cols;
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) { return false; }
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;

        int l = 0;
        int r = (cols * rows)  - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;

            int value = getValue(m);
            if(value == target) {
                return true;
            }

            if(value < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return false;
    }

    public int getValue(int position) {
        return matrix[position / cols][position % cols];
    }
}
