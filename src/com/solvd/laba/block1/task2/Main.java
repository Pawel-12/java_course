package com.solvd.laba.block1.task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        DeliveryService delService = new DeliveryService();

        Vehicle car = new Vehicle("Car", 1.25F);
        Vehicle airplane = new Vehicle("Airplane", 2.25F);

        Priority normal = new Priority("Normal", 1);
        Priority fast = new Priority("Fast", 1.5F);

        delService.addVehicle(car, airplane);
        delService.addPriority(normal, fast);


        Client john = new Client("John");
        delService.addClient(john);

        Driver mike = new Driver("Mike", 2000, new ArrayList<>(List.of("Car")));
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

        System.out.println(mike);
        mike.printAccountBalance();

        /*
        Order johnOrder = new Order(new Delivery[]{delivery1, delivery2}, john);
        List<Delivery> mikeDeliveries = new ArrayList<>();

        for (Delivery d : johnOrder.getDeliveries())
            if (d.getVehicle().getName().equals("Car"))
                mikeDeliveries.add(d);

        mike.setDeliveries(mikeDeliveries.toArray(new Delivery[0]));*/

        System.out.println("\nNot assigned:\n " + delService.getDeliveries());
    }
}
