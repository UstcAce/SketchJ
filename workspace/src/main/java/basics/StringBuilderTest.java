package basics;

import org.junit.Test;

public class StringBuilderTest {
    /**
     * delete(int start, int end)
     * 包含start ~ end -1
     * 下标end所在的字符不会被删除
     */
    @Test
    public void testCase01() {
        StringBuilder sb = new StringBuilder("1234");

        System.out.println(sb.delete(0, 10).toString());
    }
}
