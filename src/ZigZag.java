public class ZigZag {
    public String convert(String s, int numRows) {
        int length = s.length();
        if(length == 0) { return ""; }
        if(numRows == 1) { return s; }

        StringBuilder out = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            //Middle Cases
            if(i != 0 && i != numRows - 1) {
                //Loop boundary adds the number of characters between values in the straight columns
                for(int j = i; j < length; j += (numRows + (numRows - 2))) {
                    out.append(s.charAt(j));

                    //Current number + End of row values + zigzag values
                    int nextMiddle = j + (numRows - i) + ((numRows - 2) - i);
                    if(nextMiddle < length) {
                        out.append(s.charAt(nextMiddle));
                    }
                }
            }

            else { //Outer Edges
                for (int j = i; j < length; j += (numRows + (numRows - 2))) {
                    out.append(s.charAt(j));
                }
            }
        }

        return out.toString();
    }

    public static void main(String[] args) {
        ZigZag zigZag = new ZigZag();
        System.out.println(zigZag.convert("PAYPALISHIRING", 3));
        System.out.println(zigZag.convert("ABC", 2));
        System.out.println(zigZag.convert("ABCDEFGHIJKLMNOP", 4));
        System.out.println(zigZag.convert("ABCDEFGHIJKLMNOP", 5));
        System.out.println(zigZag.convert("A", 1));
    }
}
