package com.mycompany.zad1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mayurii
 */
public class zad1b {

    public static int NWD(int a, int b) {
        while (a != b) // dopóki dwie liczby nie są sobie równe
        {
            if (a > b) // sprawdzamy, która z nich jest większa
            {
                a = a - b; // odejmujemy mniejszą liczbę
            } // od większej
            else {
                b = b - a;
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        final int liczby[] = {18, 22, 30, 555, 3}; // liczby dla przykładu

        System.out.println("SEKWENCYJNIE");
        long t0 = System.nanoTime();
        for (int i = 0; i < 5; i++) {
            NWD(liczby[i], liczby[i + 1]);

        }
        long t1 = System.nanoTime();
        long t2 = t1 - t0;
        System.out.println("Czas NWD sekwencyjnie :" + t2 + "ms");

        long s0 = System.nanoTime(); //Thread start wspolbieznie
        List<Thread> threads = new ArrayList<Thread>();
        for (int j = 0; j < 5; j++) {

            final int finalJ = j;
            Thread t = new Thread() {
                @Override
                public void run() {
                    NWD(liczby[finalJ], liczby[finalJ + 1]);
                }
            };

            threads.add(t);
            t.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long s1 = System.nanoTime();
        long s2 = s1 - s0;
        System.out.println("Czas NWD współbieznie :" + s2 + "ms");
        float roznica = t2 - s2;
        System.out.println("Różnica miedzy współbieżnym, a sekwencyjnym wynosi: " + roznica + "ms");

    }
}
