package commons;

public class Tuple3<A, B, C>{

    public final A first;

    public final B second;

    public final C third;

    public Tuple3(A a, B b, C c) {
        first = a;
        second = b;
        third = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;

        if (!first.equals(tuple3.first)) return false;
        if (!second.equals(tuple3.second)) return false;
        return third.equals(tuple3.third);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }

    public String toString(){
        return "(" + first + "," + second + "," + third + ")";
    }

}
