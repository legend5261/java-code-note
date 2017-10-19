package jdk.lambda;

import java.util.function.Function;

public class FunctionDemo {
    public static void execute(Action action) {
        action.run("jdk.lambda expresstion");
    }

    static void modifyValue(int valueToBeOperated, Function<Integer, Integer> function) {
        int newValue = function.apply(valueToBeOperated);
        System.out.println(newValue);
    }

    public static void main(String[] args) {
        int incr = 20;
        modifyValue(10, val -> val + incr);

        Runnable r = () -> System.out.println("hello");
        r.run();

        execute((param) -> System.out.println(param));
    }

    private interface Action {
        void run(String value);
    }


}


