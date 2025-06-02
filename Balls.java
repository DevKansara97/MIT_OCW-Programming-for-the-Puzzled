// Please Do Break the Crystal
// This is an interactive procedure that given n floors and d balls determines
// what floors to drop the balls from to minimize the worst-case number of
// drops required to determine the hardness coefficient of the crystal.
// The hardness coefficient will range from 0 (breaks at Floor 1) or n (does not
// break at n.

import java.util.*;

public class Balls {
    public static void main(String[] args) {
	howHardIsTheCrystal(128, 4);
    }

    static void howHardIsTheCrystal(int n, int d) {
	int r = 1;
	while (myPow(r, d) <= n) {
		r++;
	}
	System.out.println("Radix chosen is: " + r);
	
	// See if you can reduce d
    	int newd = d;
    	while (myPow(r, newd-1) > n)
        	newd--;
    	if (newd < d)
        	System.out.println("Using only" + newd + "balls");
    	d = newd;

	int numDrops = 0;
    	int[] floorNoBreak = new int[d];
	Arrays.fill(floorNoBreak, 0);
	
	for (int i=0; i<d; i++) {
		for (int j=0; j<r-1; j++) {
			floorNoBreak[i]++; //increment ith digit of representation
			int Floor = convertToDecimal(r, d, floorNoBreak);

			// Make sure not to get higher than highest floor
			if (Floor > n) {
				floorNoBreak[i]--;
				break;
			}
			System.out.println("Drop ball " + (i+1) + " from Floor " + Floor);
			System.out.print("Did the ball break? (yes/no): ");
			Scanner sc = new Scanner(System.in);
			String yes = sc.next();
			
			numDrops++;
			
			if (yes.equals("yes")) {
				floorNoBreak[i]--;
				break;
			}
		}
	}
	
	int hardness = convertToDecimal(r, d, floorNoBreak);
    	System.out.println("Hardness coefficient is " + hardness);
    	System.out.println("Total number of drops is " + numDrops);
    }


    public static int myPow(int x, int n) {
        int ans = 1;
        int nn = n;

        // if (nn < 0)
        //     nn = (-1) * nn;

        while (nn > 0) {
            if (nn % 2 == 1) {
                ans = ans * x;
                nn--;
            } else {
                x = x * x;
                nn /= 2;
            }
        }

        // if (n < 0)
        //     ans = (double) (1.0) / (double) (ans);
        
        return ans;
    }

    static int convertToDecimal(int r, int d, int[] rep) {
	int number = 0;
	for (int i=0; i<d-1; i++) {
		number = (number + rep[i]) * r;
	}
	number += rep[d-1];
	return number;
    }
}

    
    