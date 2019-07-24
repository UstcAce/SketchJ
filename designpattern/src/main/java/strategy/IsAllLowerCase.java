package strategy;

public class IsAllLowerCase implements ValidationStrategy {
    public boolean excute(String s) {
        return s.matches("[a-z]+");
    }
}
