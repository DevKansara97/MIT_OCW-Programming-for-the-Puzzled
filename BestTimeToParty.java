
import java.util.*;

public class BestTimeToParty {

    public static void main(String[] args) {

        // Time Complexity: O(n)
        int[][] intervals = {{6, 7}, {7, 9}, {10, 11}, {10, 12}, {8, 10}, {9, 11}, {6, 8}};
        int n = intervals.length;

        int start = 1, end = 24;
        for (int[] in : intervals) {
            start = Math.min(start, in[0]);
            end = Math.max(end, in[1]);
        }

        int max = 0, maxCnt = 0;
        for (int i = start; i <= end; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i >= intervals[j][0] && i < intervals[j][1]) {
                    cnt++;
                }
            }
            if (maxCnt < cnt) {
                maxCnt = cnt;
                max = i;
            }
        }

        System.out.println(max);

        System.out.println(optimalSolution(intervals));
    }

    // Can also work on fractional times: 
    static int optimalSolution(int[][] intervals) {

        // start --> 1, end --> 0;
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] in : intervals) {
            list.add(new int[]{in[0], 1});
            list.add(new int[]{in[1], 0});
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int rcnt = 0, time = 0, maxCnt = 0;
        for (int[] ls : list) {
            if (ls[1] == 1) {
                rcnt++;
            } else if (ls[1] == 0) {
                rcnt--;
            }

            if (rcnt > maxCnt) {
                maxCnt = rcnt;
                time = ls[0];
            }
        }

        return time;
    }
}
