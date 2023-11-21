package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.persons.Client;
import com.solvd.laba.block1.task2.persons.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println();

        DeliveryService delService = new DeliveryService();

        Vehicle car = null;
        Vehicle airplane = null;

        try {
            car = new Vehicle("Car", 1.25F);
            airplane = new Vehicle("Airplane", 2.25F);
        } catch (Exception e) {
            LOGGER.error("Caught exception ", e);
        }

        Priority normal = null;
        Priority fast = null;

        try {
            normal = new Priority("Normal", 1);
            fast = new Priority("Fast", 1.5F);
        } catch (Exception e) {
            LOGGER.error("Caught exception ", e);
        }


        Client john = new Client("John");
        delService.addClient(john);

        Driver mike = null;

        try {
            mike = new Driver("Mike", 2000, new ArrayList<>(List.of("Car")));
        } catch (Exception e) {
            LOGGER.error("Caught exception ", e);
        }
        
        delService.addEmployee(mike);


        Item letter = new Item("Letter", 5, 5, 5, 0.1F);
        Item box = new Item("Box", 10, 10, 10, 6);

        Location a = new Location("Test", "test", 1, new Cords(0, 0));
        Location b = new Location("Test2", "test2", 2, new Cords(2, 2));

        Package package1 = new Package(letter, a, b);
        Package package2 = new Package(box, a, b);

        Delivery delivery1 = new Delivery(package1, car, normal);
        Delivery delivery2 = new Delivery(package2, airplane, fast);


        john.printAccountBalance();

        delService.newOrder(john, delivery1, delivery2);

        john.printAccountBalance();


        delService.processDeliveries();


        LOGGER.info('\n' + mike.toString());
        mike.printAccountBalance();


        LOGGER.info("\nNot assigned:\n " + delService.getDeliveries());

        delService.saveClients();
    }
}
