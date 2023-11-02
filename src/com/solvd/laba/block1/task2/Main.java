package com.solvd.laba.block1.task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Item letter = new Item("Letter", 5, 5, 5, 0.1F);
        Item box = new Item("Box", 10, 10, 10, 6);

        Vehicle car = new Vehicle("Car", 1.25F);
        Vehicle airplane = new Vehicle("Airplane", 2.25F);

        Priority normal = new Priority("Normal", 1);
        Priority fast = new Priority("Fast", 1.5F);

        Location a = new Location("Test", "test", 1, new Cords(0, 0));
        Location b = new Location("Test2", "test2", 2, new Cords(2, 2));


        System.out.println();

        Package package1 = new Package(letter, a, b);
        Package package2 = new Package(box, a, b);

        Delivery delivery1 = new Delivery(package1, car, normal);
        Delivery delivery2 = new Delivery(package2, airplane, fast);


        Client john = new Client("John", 123);

        john.printAccountBalance();

        Order johnOrder = new Order(new Delivery[]{delivery1, delivery2}, john);

        System.out.println();

        System.out.println("Delivery 1 = " + DeliveryService.calculateSingleDeliveryCost(delivery1));
        System.out.println("Delivery 2 = " + DeliveryService.calculateSingleDeliveryCost(delivery2));

        System.out.println("Total = " + DeliveryService.calculateTotalOrderCost(johnOrder));

        System.out.println();

        john.setAccountBalance((float) (-1.0 * DeliveryService.calculateTotalOrderCost(johnOrder)));

        john.printAccountBalance();
        System.out.println();


        Driver mike = new Driver("Mike", 125, 2000);

        List<Delivery> mikeDeliveries = new ArrayList<>();

        for (Delivery d : johnOrder.getDeliveries())
            if (d.getVehicle().getName().equals("Car"))
                mikeDeliveries.add(d);

        mike.setDeliveries(mikeDeliveries.toArray(new Delivery[0]));

        System.out.println(mike);

        mike.printAccountBalance();
    }
}
