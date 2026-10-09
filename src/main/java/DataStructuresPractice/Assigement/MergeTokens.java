package DataStructuresPractice.Assigement;

import java.util.Arrays;

public class MergeTokens {

    static int[] mergeTokens(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            res[k++] = a[i] <= b[j] ? a[i++] : b[j++];
        while (i < a.length) res[k++] = a[i++];
        while (j < b.length) res[k++] = b[j++];
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeTokens(new int[]{3, 8, 15, 20}, new int[]{5, 8, 12})));
        // [3, 5, 8, 8, 12, 15, 20]
        System.out.println(Arrays.toString(mergeTokens(new int[]{}, new int[]{4, 9}))); // [4, 9]
    }
}
// Two pointers: Time O(m + n), Space O(m + n) for the output
// Join + sort: Time O((m + n) log(m + n)), ignores that the lists are already sorted