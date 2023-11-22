package hw12.task1;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class PrintTime {

    static volatile long  startTime = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("5 seconds have passed");
            }
        });

        thread.start();

        while (true) {
            System.out.println("Time from start : " + (System.currentTimeMillis() - startTime) / 1000);
            Thread.sleep(1000);
        }
    }
}
