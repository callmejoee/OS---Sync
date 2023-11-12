public class Main {
    public static void main(String[] args)
    {
        Router router = new Router(2); // Assuming the router can accept 2 connections

        // Create Device instances and pass the Router object to them
        Device device1 = new Device("C1", "mobile", router);
        Device device2 = new Device("C2", "tablet", router);

        // Start the devices (threads)
        device1.start();
        device2.start();
    }
}