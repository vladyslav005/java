package hw12.task2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class D {
    Queue<Integer> input;
    BlockingDeque<String> output;
    private AtomicBoolean processed = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        D d = new D(10000);
        d.processNumbers();
    }

    public D(int n) {
        this.output = new LinkedBlockingDeque<>(n);
        this.input = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++)
            input.add(i+1);
    }

    public void processNumbers () throws InterruptedException {
        while (!input.isEmpty()) {
            Integer curr = input.poll();
            Thread a = new Thread(() -> {if (curr % 3 == 0 && curr % 5 != 0 && !processed.get()) {output.add("fizz"); processed.set(true);}});
            Thread b = new Thread(() -> {if (curr % 5 == 0 && curr % 3 != 0 && !processed.get()) {output.add("buzz"); processed.set(true);}});
            Thread c = new Thread(() -> {if (curr % 5 == 0 && curr % 3 == 0 && !processed.get()) {output.add("fizzbuzz"); processed.set(true);}});
            a.start(); b.start(); c.start();
            a.join(); b.join(); c.join();
            if (!processed.get()) output.add(String.valueOf(curr));
            processed.set(false);
            number();
        }
    }

    public void number () {
        System.out.print(output.poll() + ", ");
    }

}
