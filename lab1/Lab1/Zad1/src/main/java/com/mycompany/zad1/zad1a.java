package com.mycompany.zad1;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mayurii
 */
public class zad1a {
    
 public static void main(String[] args) throws IOException, InterruptedException {
        int i;
        float t0 = System.nanoTime();
        for ( i = 1; i <= 10; i++) {
            ImageIO.write(ImageIO.read(new URL("https://picsum.photos/200/300")), "jpg", new File("C:\\tak\\zdj" + i + ".jpg"));
            System.out.println("sekwencyjnie" );
        }
        float t1 = System.nanoTime();
        float t2 = t1-t0;
        System.out.println("Sekwencyjnie "+t2+"ms");


        float s0 = System.nanoTime();
        List<Thread> threads = new ArrayList<Thread>();
        for ( i = 11; i <= 20; i++) {
            final int finalI = i;
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        ImageIO.write(ImageIO.read(new URL("https://picsum.photos/g/200/300")), "jpg", new File("C:\\tak\\zdj" + finalI + ".jpg"));
                        System.out.println("wspóbieżnie");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads.add(t);
            t.start();
        }
        
        for (Thread thread: threads) {
            thread.join();
        }
        float s1 = System.nanoTime();
        float s2 = s1-s0;
        System.out.println( "wspóbieżnie = "+ s2+"ms");
        float roznica = t2-s2;
        System.out.println("Różnica miedzy współbieżnym, a sekwencyjnym wynosi : " + roznica+"ms");

    }

}