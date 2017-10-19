package jdk.concurrent;

import java.util.concurrent.Exchanger;

public class TestExchanger {
    public static void main(String[] args) {
        Exchanger<Object> e = new Exchanger<Object>();
        ExchangerRunnable r1 = new ExchangerRunnable(e, "A");
        ExchangerRunnable r2 = new ExchangerRunnable(e, "B");
        new Thread(r1).start();
        new Thread(r2).start();
    }
}

class ExchangerRunnable implements Runnable {
    Exchanger<Object> exchanger = null;
    Object object = null;

    ExchangerRunnable(Exchanger<Object> exchanger, Object object) {
        this.exchanger = exchanger;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(previous + "~" + this.object);
        } catch (Exception e) {

        }
    }


}
