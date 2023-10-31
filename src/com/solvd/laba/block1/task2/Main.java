package com.solvd.laba.block1.task2;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Item{
    private String _name;
    private float _price;

    public Item(String name, float price)
    {
        _name = name;
        _price = price;
    }

    public String getName(){return _name;}
    public float getPrice() {return _price;}

    public void setName(String name){_name = name;}
    public void setPrice(float price) {_price = price;}
}

class Vehicle{
    private String _name;
    private float _rate;

    public Vehicle(String name, float rate){
        _name = name;
        _rate = rate;
    }

    public String getName(){return _name;}
    public float getRate(){return _rate;}

    public void setName(String name){_name = name;}
    public void setRate(float rate){_rate = rate;}
}

class Cords{
    public float X;
    public float Y;
    public Cords(float x, float y){
        X = x;
        Y = y;
    }
    public double calcDistance(Cords cords2){
        return sqrt(pow(cords2.X - X,2)) + sqrt(pow(cords2.Y - Y,2));
    }
}

class Priority{
    private String _name;
    private float _rate;

    public Priority(String name, float rate){
        _name = name;
        _rate = rate;
    }

    public String getName(){return _name;}
    public float getRate(){return _rate;}

    public void setName(String name){_name = name;}
    public void setRate(float rate){_rate = rate;}
}

class Location{
    private String _town;
    private String _road;
    private int _houseNumber;
    private Cords _cords;

    public Location(String town, String road, int houseNumber, Cords cords){
        _town = town;
        _road = road;
        _houseNumber = houseNumber;
        _cords = cords;
    }

    public String getTown(){return _town;}
    public String getRoad(){return _road;}
    public int getHouseNumber(){return _houseNumber;}
    public Cords getCords(){return _cords;}


    public void setTown(String town){_town = town;}
    public void setRoad(String road){_road = road;}
    public void setHouseNumber(int houseNumber){_houseNumber = houseNumber;}
    public void setCords(Cords cords){_cords = cords;}

    public double calcDistance(Location loc2){
        return _cords.calcDistance(loc2.getCords());
    }
}

class Package{
    private Item _item;
    private Location _source;
    private Location _destination;
    private float _weight;

    private float _distance;

    public Package(Item item, Location from, Location to, float weight){
        _item = item;
        _source = from;
        _destination = to;
        _weight = weight;

        _distance = (float) _source.calcDistance(_destination);
    }

    public Item getItem() {return _item;}
    public Location getSource() {return _source;}
    public Location getDestination() {return _destination;}
    public float getWeight() {return _weight;}
    public float getDistance() {return _distance;}

    public void setItem(Item item) {_item = item;}
    public void setSource(Location from) {_source = from; _distance = (float) _source.calcDistance(_destination);}
    public void setDestination(Location to) {_destination = to; _distance = (float) _source.calcDistance(_destination);}
    public void setWeight(float weight) {_weight = weight;}
    public void setDistance(float distance) {_distance = distance;}
}

class Delivery{
    private Package _package;
    private Vehicle _vehicle;
    private float _price;
    private Priority _priority;

    private void updatePrice()
    {
        _price = (float) (_package.getItem().getPrice() + (_package.getDistance() * _vehicle.getRate())) * _priority.getRate();
    }

    public Delivery(Package package1, Vehicle vehicle, Priority priority){
        _package = package1;
        _vehicle = vehicle;
        _priority = priority;

        updatePrice();
    }


    public Package getPackage() {return _package;}
    public Vehicle getVehicle() {return _vehicle;}
    public float getPrice() {return _price;}
    public Priority getPriority() {return _priority;}



    public void setPackage(Package package1) {_package = package1; updatePrice();}
    public void setVehicle(Vehicle vehicle) {_vehicle = vehicle; updatePrice();}
    public void setPrice(float price) {_price = price;}
    public void setPriority(Priority priority) {_priority = priority; updatePrice();}
}

class Client{
    private String _name;

    public Client(String name)
    {
        _name = name;
    }

    public String getName(){return _name;}

    public void setName(String name){_name = name;}
}

class Order{
    private Delivery[] _deliveries;
    private Client _client;
    private float _totalPrice;

    public Order(Delivery[] deliveries, Client client){
        _deliveries = deliveries;
        _client = client;

        for(Delivery delivery : _deliveries)
            _totalPrice += delivery.getPrice();
    }

    public Delivery[] getDeliveries(){return _deliveries;}
    public Client getClient(){return _client;}
    public float getTotalPrice(){return _totalPrice;}

    public void setDeliveries(Delivery[] deliveries){_deliveries = deliveries;}
    public void setClient(Client client){_client = client;}
    public void setTotalPrice(float totalPrice){_totalPrice = totalPrice;}
}

public class Main {
    public static void main(String[] args)
    {
        Item letter = new Item("Letter", 10);
        Item box = new Item("Box", 50);

        Vehicle car = new Vehicle("Car", 1.25F);
        Vehicle airplane = new Vehicle("Airplane", 2.25F);

        Priority normal = new Priority("Normal",1);
        Priority fast = new Priority("Normal",1.5F);

        Location a = new Location("Test","test",1, new Cords(0, 0));
        Location b = new Location("Test2","test2",2, new Cords(2, 2));

        Package package1 = new Package(letter, a, b, 12);
        Package package2 = new Package(box, a, b, 24);

        Delivery delivery1 = new Delivery(package1, car, normal);
        Delivery delivery2 = new Delivery(package2, airplane, fast);

        Client john = new Client("John");

        Order johnOrder = new Order(new Delivery[]{delivery1, delivery2}, john);

        System.out.println(delivery1.getPrice());
        System.out.println(delivery2.getPrice());

        System.out.println(johnOrder.getTotalPrice());
    }
}
