package leetcode;

public class Q65ValidNumber {
    /**
     * 规则梳理
     * 1. 空格只能出现在头尾，不能出现在中间
     * 2. 不能有除e以外的其他字符
     * 3. e后面不能出现小数点
     * 4. e,小数点最多只能出现1次
     * 5. 在有e的情况下，正负最多出现2次，其他情况下最多出现一次
     */
    public boolean isNumber(String s) {
        String input = s.trim();

        int digitNum = 0;
        int digitNumAfterE = 0;
        int signNum = 0;
        int pointNum = 0;
        int eNum = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 1. 数字
            if (Character.isDigit(c)) {
                digitNum++;
                if (eNum != 0) {
                    digitNumAfterE++;
                }
            } else if (c == '+' || c == '-') {
                // 2. 正负号
                signNum++;
                if (digitNum > 0 && eNum == 0) return false;
                if (pointNum > 0) return false;
            } else if (c == '.') {
                // 3. 小数点
                pointNum++;
                if (eNum > 0) return false;
            } else if (c == 'e') {
                // 4. 指数e
                eNum++;
            } else {
                return false;
            }
            if (!isValid(signNum, pointNum, eNum)) return false;
        }
        if (digitNum == 0) return false;
        if (eNum > 0 && (digitNumAfterE == 0 || digitNum == digitNumAfterE)) return false;
        return true;
    }

    private boolean isValid(int signNum, int pointNum, int eNum) {
        if (pointNum >= 2) return false;
        if (eNum >= 2) return false;
        if (signNum > 2 || (signNum == 2 && eNum != 1)) {
            return false;
        }
        return true;
    }

    public boolean isNumber2(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }

}
