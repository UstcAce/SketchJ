package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Q9PalindromeNum {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        int iter = x;
        while (iter>0) {
            list.add(iter%10);
            iter = iter / 10;
        }
        int len = list.size();
        int mid = len/2;
        for (int i=0; i<mid; i++) {
            if (list.get(i).equals(list.get(len-i-1))) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        }
        String ori = "" + x;
        int len = ori.length();
        int help = (int)Math.pow(10, len);
        while (x>0) {
            int high = x/help;
            int low = x%10;
            if (high != low) {
                return false;
            } else {
                x = (x%help)/10;
                help = help / 100;
            }
        }
        return true;
    }

    @Test
    public void testCase01() {
        System.out.println(new StringBuilder("-123").reverse().toString());
    }
}
