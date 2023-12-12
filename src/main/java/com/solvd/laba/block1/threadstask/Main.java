package com.solvd.laba.block1.threadstask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(com.solvd.laba.block1.threadstask.Main.class);

    public static void main(String[] args) {
        System.out.println();

        try (ExecutorService es = Executors.newFixedThreadPool(7)) {
            ConnectionPool conPool = ConnectionPool.createInstance(5);
            Future<?>[] futures = new FutureTask[7];

            for (int i = 0; i < 7; i++) {
                int finalI = i;
                futures[i] = es.submit(() -> {
                    var temp = conPool.getConnection();
                    while (temp == null) {
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        temp = conPool.getConnection();
                    }

                    try {
                        LOGGER.info("i = " + finalI + "\n");
                        sleep(finalI * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    conPool.releaseConnection(temp);
                });
            }
            for (var f : futures)
                f.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        ConnectionPool.reset();
        LOGGER.info("CompletableFuture --------------------------------------------------------------------------\n");

        try (ExecutorService es = Executors.newFixedThreadPool(7)) {
            ConnectionPool conPool = ConnectionPool.createInstance(5);
            for (int i = 0; i < 7; i++) {
                CompletableFuture<ConnectionMock> f = CompletableFuture.supplyAsync(() -> {
                    var temp = conPool.getConnection();
                    while (temp == null) {
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        temp = conPool.getConnection();
                    }
                    return temp;
                }, es);

                int finalI = i;
                f.thenAccept(con -> {
                    try {
                        LOGGER.info("i = " + finalI + "\n");
                        sleep(finalI * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    conPool.releaseConnection(con);
                });
            }
        }
    }
}
