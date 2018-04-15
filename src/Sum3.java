import java.util.*;

//LeetCode #15
public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> retVal = new HashSet<>();
        int length = nums.length;
        if(length == 0) {
            return new ArrayList<>();
        }

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        int zeroCount = 0;

        for(int num : nums) {
            if (num < 0) {
                negatives.add(num);
            } else if (num > 0) {
                positives.add(num);
            } else {
                zeroCount++;
            }
        }

        if(zeroCount > 2) {
            retVal.add(Arrays.asList(0, 0, 0));
        }
        Collections.sort(positives);
        Collections.sort(negatives);

        for(int posIndex = 0; posIndex < positives.size(); posIndex++) {
            for (int negIndex = 0; negIndex < negatives.size(); negIndex++) {
                int query = -(positives.get(posIndex) + negatives.get(negIndex));
                if(query < 0) {
                    if (binarySearch(negatives, query, negIndex + 1, negatives.size() - 1)) {
                        retVal.add(Arrays.asList(negatives.get(negIndex), query, positives.get(posIndex)));
                    }
                } else if (query > 0) {
                    if (binarySearch(positives, query, posIndex + 1, positives.size() - 1)) {
                        retVal.add(Arrays.asList(positives.get(posIndex), query, negatives.get(negIndex)));
                    }
                } else {
                    if(zeroCount > 0) {
                        retVal.add(Arrays.asList(negatives.get(negIndex), 0, positives.get(posIndex)));
                    }
                }
            }
        }

        return new ArrayList<>(retVal);
    }

    public boolean binarySearch(List<Integer> nums, int value, int l, int r) {
        while (l <= r)
        {
            int m = l + (r-l)/2;

            if (nums.get(m) == value)
                return true;

            if (nums.get(m) < value)
                l = m + 1;

            else
                r = m - 1;
        }

        return false;
    }

    public static void main(String[] args) {
        Sum3 sum3 = new Sum3();
        int[] test1 = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(sum3.threeSum(test1));

        int[] test2 = new int[]{0, 0, 0, 0};
        System.out.println(sum3.threeSum(test2));

        int[] test3 = new int[]{3, -2, 1, 0};
        System.out.println(sum3.threeSum(test3));
    }
}
