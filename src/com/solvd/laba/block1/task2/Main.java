package com.solvd.laba.block1.task2;

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

        Package package1 = new Package(letter, a, b);
        Package package2 = new Package(box, a, b);

        Delivery delivery1 = new Delivery(package1, car, normal);
        Delivery delivery2 = new Delivery(package2, airplane, fast);

        Client john = new Client("John");

        Order johnOrder = new Order(new Delivery[]{delivery1, delivery2}, john);

        System.out.println(DeliveryService.calculateSingleDeliveryCost(delivery1));
        System.out.println(DeliveryService.calculateSingleDeliveryCost(delivery2));

        System.out.println(DeliveryService.calculateTotalOrderCost(johnOrder));
    }
}
