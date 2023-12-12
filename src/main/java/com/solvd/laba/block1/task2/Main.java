package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.enums.DeliveryStatus;
import com.solvd.laba.block1.task2.enums.Month;
import com.solvd.laba.block1.task2.enums.Year;
import com.solvd.laba.block1.task2.exceptions.NegativeRateException;
import com.solvd.laba.block1.task2.exceptions.NegativeSalaryException;
import com.solvd.laba.block1.task2.persons.Client;
import com.solvd.laba.block1.task2.persons.Driver;
import com.solvd.laba.block1.task2.utils.FileStats;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Thread fileStats = new Thread(() -> FileStats.countUniqueWords("src/main/resources/data/log.txt"));
        fileStats.start();
        
        System.out.println();

        DeliveryService delService = new DeliveryService();

        Thread fileSaverThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                delService.saveClients();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.info("fileThread interrupted " + e);
                    Thread.currentThread().interrupt();
                }
                delService.saveClients();
            }
        });

        fileSaverThread.start();

        delService.setDate(new MutablePair<>(Year.Y2023, Month.DECEMBER));
        LOGGER.info("Current date = " + delService.getDate() + '\n');

        Vehicle car = null;
        Vehicle airplane = null;

        try {
            car = new Vehicle("Car", 1.25F);
            airplane = new Vehicle("Airplane", 2.25F);
        } catch (NegativeRateException e) {
            LOGGER.error("Caught exception ", e);
        }

        Priority normal = null;
        Priority fast = null;

        try {
            normal = new Priority("Normal", 1);
            fast = new Priority("Fast", 1.5F);
        } catch (NegativeRateException e) {
            LOGGER.error("Caught exception ", e);
        }


        Client john = new Client("John");
        delService.addClient(john);
        LOGGER.info(delService.getClient(c -> c.getName().equals("John")));

        Driver mike = null;

        try {
            mike = new Driver("Mike", 2000, new ArrayList<>(List.of("Car")));
        } catch (NegativeSalaryException e) {
            LOGGER.error("Caught exception ", e);
        }

        delService.addEmployee(mike);
        LOGGER.info(delService.getEmployee(e -> e.getName().equals("Mike")));


        Item letter = new Item("Letter", 5, 5, 5, 0.1F);
        Item box = new Item("Box", 10, 10, 10, 6);

        Location a = new Location("Test", "test", 1, new Cords(0, 0));
        Location b = new Location("Test2", "test2", 2, new Cords(2, 2));

        Package package1 = new Package(letter, a, b);
        Package package2 = new Package(box, a, b);

        Delivery delivery1 = new Delivery(package1, car, normal);
        Delivery delivery2 = new Delivery(package2, airplane, fast);


        john.printAccountBalance();

        LOGGER.info("New Order by " + john.getName() + " to deliver \n - " + delivery1 + "\n - " + delivery2 + '\n');

        delService.newOrder(john, delivery1, delivery2);

        john.printAccountBalance();


        delService.processDeliveries();
        delService.nextMonth();
        LOGGER.info("Current date = " + delService.getDate() + '\n');


        LOGGER.info(mike.toString());
        mike.printAccountBalance();
        LOGGER.info(mike.getDelivery(d -> d.getStatus() == DeliveryStatus.TRANSIT) + "\n");


        LOGGER.info("Not assigned:\n " + delService.getDeliveries());

        fileSaverThread.interrupt();
    }
}
