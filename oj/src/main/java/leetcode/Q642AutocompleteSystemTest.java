package leetcode;

import org.junit.Test;

public class Q642AutocompleteSystemTest {

    @Test
    public void testCase01() {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        Q642AutocompleteSystem system = new Q642AutocompleteSystem(sentences, times);

        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
    }

    @Test
    public void testCase02() {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        Q642AutocompleteSystem system = new Q642AutocompleteSystem(sentences, times);

        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
    }
}
