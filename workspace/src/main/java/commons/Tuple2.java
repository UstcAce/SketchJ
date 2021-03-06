package commons;

import java.util.Objects;

public class Tuple2<A, B> {

    public final A first;

    public final B second;

    public Tuple2(A a, B b){
        first = a;
        second = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(first, tuple2.first) &&
                Objects.equals(second, tuple2.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public String toString(){
        return "(" + first + ", " + second + ")";
    }

}
