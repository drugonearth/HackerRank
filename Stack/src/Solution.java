import java.util.*;
class Solution{

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input=sc.next();
            System.out.println(isBalanced(input));
        }

    }
    public static boolean isBalanced(String chars)
    {
        if(chars.length()%2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        char next;
        char last;
        for(int i = 0; i < chars.length(); i++) {
            next = chars.charAt(i);
            if(next == ')' || next =='}' || next == ']' )
            {
                if(stack.isEmpty()) return false;
                last = stack.pop();
                if(last == '(' && next != ')' ||
                        last == '{' && next != '}'||
                        last == '[' && next != ']') return false;
            }
            else {
                stack.push(next);
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }
}
