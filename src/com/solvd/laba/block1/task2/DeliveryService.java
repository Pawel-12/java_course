package com.solvd.laba.block1.task2;

public class DeliveryService {

    public static double calculateSingleDeliveryCost(Delivery delivery) {
        double result = 0;

        if (inSizeRange(delivery.getPackage().getItem(), 5))
            result += 10;
        else if (inSizeRange(delivery.getPackage().getItem(), 10))
            result += 50;
        else if (inSizeRange(delivery.getPackage().getItem(), 40))
            result += 60;

        result += (delivery.getPackage().getDistance() * delivery.getVehicle().getRate());
        result *= delivery.getPriority().getRate();

        return result;
    }

    public static double calculateTotalOrderCost(Order order) {
        double result = 0;

        for (Delivery d : order.getDeliveries())
            result += calculateSingleDeliveryCost(d);

        return result;
    }

    private static boolean inSizeRange(Item item, float size) {
        boolean widthOrHeight = (item.getWidth() <= size) || (item.getHeight() <= size);
        boolean depthOrWeight = (item.getDepth() <= size) || (item.getWeight() <= size);

        return widthOrHeight || depthOrWeight;
    }
}