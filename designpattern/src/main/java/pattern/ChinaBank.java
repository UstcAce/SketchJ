package pattern;

public class ChinaBank extends BankingPattern {
    @Override
    void makeCustomerHappy(String customer) {

    }

    public static void main(String[] args) {
        new ChinaBank().processCustomer(11, (String str) -> System.out.println("Hello " + str));
    }
}
