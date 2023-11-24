/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Seif
 */
// public class Network {
// /**
// * @param args the command line arguments
// */
// public static void main(String[] args) throws InterruptedException {
// Scanner scanner = new Scanner(System.in);
// int maxConnections, numDevices;
// Router router;

// System.out.println("Enter Maximum number of Connections a router can accept:
// ");
// maxConnections = scanner.nextInt();

// System.out.println("Enter number of devices that wish to connect: ");
// numDevices = scanner.nextInt();

// router = new Router(maxConnections);

// for (int i = 0; i < numDevices; i++) {
// System.out.println("Enter details for Device " + (i + 1) + ":");
// System.out.println("Name: ");
// String name = scanner.next();

// System.out.println("Type: ");
// String type = scanner.next();

// Device device = new Device(name, type, router);
// // telling the Thread object which Runnable instance to execute.
// Thread thread = new Thread(device);
// thread.start();
// }
// scanner.close();
// }
// }

// !test Network Class
public class Network {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int maxConnections, numDevices;
        Router router;

        // System.out.println("Enter Maximum number of Connections a router can accept:
        // ");
        maxConnections = 5;

        // System.out.println("Enter number of devices that wish to connect: ");
        numDevices = 10;

        router = new Router(maxConnections);

        for (int i = 0; i < numDevices; i++) {
            // System.out.println("Enter details for Device " + (i + 1) + ":");
            // System.out.println("Name: ");
            String name = "Device-" + i;

            // System.out.println("Type: ");
            String type = "Mobile";

            Device device = new Device(name, type, router);

            // telling the Thread object which Runnable instance to execute.
            Thread thread = new Thread(device);
            thread.start();
        }
        scanner.close();
    }
}

// _____________________________________________________________________________________________________________
class Device implements Runnable {
    private String name;
    private String type;
    private Router router;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // ++++++++++++++++++
    Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    // +++++++++++++++++++
    @Override // the Threads run method
    public void run() {
        connect();
        performActivity();
        logout();
    }

    // ++++++++++++++++++++
    // define each of the run functions
    private void connect() {
        router.occupyConnection(this);
        System.out.println(name + " " + type + " Connected");
    }

    // ++++++++++++++++++++++
    private void performActivity() {
        System.out.println("Connection " + index + ": " + name + " performs online activity");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // +++++++++++++++++++++++
    private void logout() {
        router.releaseConnection(this);
        System.out.println("Connection " + index + ": " + name + " Disconnected");
    }
    // +++++++++++++++++++++++
}

// _____________________________________________________________________________________________________________
class Router {
    private int maxConnections;
    private List<Device> ConnectedDevices;
    private Semaphore semaphore;

    Router(int maxConnections) {
        this.maxConnections = maxConnections;
        this.ConnectedDevices = new ArrayList<>();
        this.semaphore = new Semaphore(maxConnections);
    }

    public void occupyConnection(Device device) {
        semaphore.acquire();
        ConnectedDevices.add(device);
    }

    public void releaseConnection(Device device) {
        ConnectedDevices.remove(device);
        semaphore.release();
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public List<Device> getConnectedDevices() {
        return ConnectedDevices;
    }

}

// _____________________________________________________________________________________________________________
class Semaphore {
    private int value;

    Semaphore(int value) {
        this.value = value;
    }

    public synchronized void acquire() {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        value--;
    }

    public synchronized void release() {
        value++;
        notify();
    }

    public int getValue() {
        return value;
    }
}
