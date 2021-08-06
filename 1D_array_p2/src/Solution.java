import java.util.*;
//https://www.hackerrank.com/challenges/java-1d-array/problem
public class Solution {

    public static boolean canWin(int leap, int[] game) {
        return Search(leap, game, 0);
    }

    private static boolean Search(int leap, int[] game, int ind)
    {

        if(ind < 0) return false;
        if(game[ind] != 0) return false;
        if(ind + leap >= game.length  || ind + 1 == game.length) return true;

        game[ind] = 1;

        return Search(leap, game, ind+1)||
        Search(leap, game, ind+leap)||
        Search(leap, game, ind-1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}