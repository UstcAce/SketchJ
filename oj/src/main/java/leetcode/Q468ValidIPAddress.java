package leetcode;

import org.junit.Test;

/**
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
 * IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
 * 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。
 *
 * IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。
 * 比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。
 * 而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。
 * 所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
 * 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 * 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 */
public class Q468ValidIPAddress {

    /**
     * 注意："2001:0db8:85a3:0:0:8A2E:0370:7334:"
     * split函数会丢掉后面的空字符串
     */
    public String validIPAddress(String ip) {
        if (ip.contains(".")) {
            return validIPV4(ip);
        } else if (ip.contains(":")) {
            return validIPV6(ip);
        } else {
            return "Neither";
        }
    }

    private String validIPV4(String ip) {
        String[] arr = ip.split("\\.", -1);
        if (arr.length != 4) return "Neither";
        for (String str : arr) {
            if (!isIPV4Number(str)) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    private boolean isIPV4Number(String str) {
        try {
            int num = Integer.parseInt(str);
            String strNum = num + "";
            if (strNum.length() != str.length()) {
                return false;
            }
            return num >= 0 && num <= 255;
        } catch (Exception e) {
            return false;
        }
    }

    private String validIPV6(String ip) {
        String[] arr = ip.split(":", -1);
        if (arr.length != 8) return "Neither";
        for (String str : arr) {
            if (!isIPV6Number(str)) {
                return "Neither";
            }
        }

        return "IPv6";
    }

    private boolean isIPV6Number(String str) {
        if (str.length() == 0 || str.length() > 4) {
            return false;
        }
        for (char c : str.toCharArray()) {
            boolean b = Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
            if (!b) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCase01() {
        String input = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        System.out.println(validIPAddress(input));
    }
}
