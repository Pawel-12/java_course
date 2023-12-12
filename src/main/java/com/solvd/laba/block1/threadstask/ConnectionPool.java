package com.solvd.laba.block1.threadstask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.solvd.laba.block1.threadstask.Main.LOGGER;

public class ConnectionPool {
    private volatile static ConnectionPool thisPool = null;
    private volatile BlockingQueue<ConnectionMock> activeConnections = new LinkedBlockingQueue<>();
    private volatile BlockingQueue<ConnectionMock> idleConnections = new LinkedBlockingQueue<>();
    private static int size;

    private ConnectionPool(int size) {
        ConnectionPool.size = size;
        LOGGER.info("ConnectionPool created\n");
    }

    public static synchronized ConnectionPool createInstance(int size) {
        if (thisPool == null)
            thisPool = new ConnectionPool(size);

        return thisPool;
    }

    public static ConnectionPool getInstance() {
        return thisPool;
    }

    public synchronized ConnectionMock getConnection() {
        if (activeConnections.size() < size) {
            if (!idleConnections.isEmpty()) {
                try {
                    ConnectionMock temp = idleConnections.take();
                    activeConnections.add(temp);
                    LOGGER.info("Idle connection " + temp + " reused\n");
                    return temp;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ConnectionMock temp = new ConnectionMock();
                activeConnections.add(temp);
                LOGGER.info("New connection " + temp + " created\n");
                return temp;
            }
        }
        LOGGER.info("No available connections!\n");
        return null;
    }

    public synchronized void releaseConnection(ConnectionMock con) {
        idleConnections.add(con);
        activeConnections.remove(con);
        LOGGER.info("Connection " + con + " released\n");
    }

    public static synchronized void reset() {
        thisPool = null;
    }
}
