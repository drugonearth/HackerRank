import java.util.*;

public class Solution {

    public static boolean canWin(int leap, int[] game) {
        return Search(leap, game, 0);
    }

    private static boolean Search(int leap, int[] game, int ind)
    {
        if(ind + leap >= game.length || ind + 1 >= game.length) return true;
        game[ind] = 1;

        if(ind < game.length - 1 && game[ind+1] == 0) return Search(leap, game, ind+1);
        if(game[ind+leap] == 0) return Search(leap, game, ind+leap);
        if(ind > 0 && game[ind-1] == 0) return Search(leap, game, ind-1);

        return false;
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