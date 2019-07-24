package strategy;

public class Validator {
    private ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.excute(s);
    }

    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        System.out.println(b1);
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b2);

        // using lambda
        ValidationStrategy numericValidator2 = (String str) -> str.matches("[0-9]+");
        boolean b3 = numericValidator2.excute("aaaa");
        System.out.println(b3);

        ValidationStrategy lowerCaseValidator2 = (String str) -> str.matches("[a-z]+");
        boolean b4 = lowerCaseValidator2.excute("bbbb");
        System.out.println(b4);

        Validator numericValidator3 = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b5 = numericValidator3.validate("aaaa");
        System.out.println(b5);
        Validator lowerCaseValidator3 = new Validator((String s) -> s.matches("\\d+"));
        boolean b6 = lowerCaseValidator3.validate("bbbb");
        System.out.println(b6);
    }
}
