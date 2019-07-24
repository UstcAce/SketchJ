package strategy;

public class IsNumeric implements ValidationStrategy {
    public boolean excute(String s) {
//        return s.matches("\\d+");
        return s.matches("[0-9]+");
    }
}
