import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Solution {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(s.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b:bytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb);
    }
}