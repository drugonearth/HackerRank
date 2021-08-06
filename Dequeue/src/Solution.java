import java.util.*;

//https://www.hackerrank.com/challenges/java-dequeue/problem

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int num = in.nextInt();

            deque.addLast(num);
            addToMap(num, hm);
        }

        int max = hm.size();
        int deleted;

        for (int i = m; i < n; i++)
        {
            int num = in.nextInt();
            deleted = deque.pollFirst();

            deque.addLast(num);
            removeFromMap(deleted, hm);
            addToMap(num, hm);

            if(hm.size() > max) max = hm.size();
        }

        System.out.println(max);
    }

    public static void addToMap(int num, Map<Integer,Integer> hm)
    {
        if(hm.get(num)==null) hm.put(num, 1);
        else hm.put(num,hm.get(num)+1);
    }

    public static void removeFromMap(int num, Map<Integer,Integer> hm)
    {
        if(hm.get(num) == 1) hm.remove(num);
        else hm.put(num, hm.get(num) - 1);
    }
}
