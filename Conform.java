
import java.util.ArrayList;
import java.util.Arrays;

public class Conform {

    public static void main(String[] args) {

        // ArrayList<Character> caps = new ArrayList<>(Arrays.asList('F', 'B', 'F', 'F', 'F', 'F', 'B', 'B'));
        ArrayList<Character> caps = new ArrayList<>(Arrays.asList('F', 'B', 'B', 'B'));
        print(pleaseConform(caps));
        // pleaseConform_onePass(caps);
    }

    static ArrayList<int[]> pleaseConform(ArrayList<Character> caps) {

        int start = 0;
        int forward = 0, backward = 0;
        ArrayList<int[]> intervals = new ArrayList<>();

        caps.add('x');
        for (int i = 1; i < caps.size(); i++) {
            if (!caps.get(start).equals(caps.get(i))) {
                intervals.add(new int[]{start, i - 1, caps.get(start)});
                start = i;
            }

            if (caps.get(i) == 'F') {
                forward++;
            } else {
                backward++;
            }
        }

        char flip;
        if (forward < backward) {
            flip = 'F';
        } else {
            flip = 'B';
        }

        for (int[] in : intervals) {
            if (in[2] == flip) {
                System.out.println("People in positions " + in[0] + " through " + in[1] + " flip your caps");
            }
        }

        return intervals;

    }

    static void pleaseConform_onePass(ArrayList<Character> caps) {
        caps.add(caps.get(0));
        for (int i = 1; i < caps.size(); i++) {
            if (caps.get(i) != caps.get(i - 1)) {
                if (caps.get(i) != caps.get(0)) {
                    System.out.print("People in positions " + i);
                } else {
                    System.out.println(" through " + (i - 1) + " flip your caps");
                }
            }
        }
    }

    static void print(ArrayList<int[]> intervals) {
        for (int[] in : intervals) {
            System.out.println(in[0] + ", " + in[1] + ", " + (char) in[2]);
        }
    }
}
