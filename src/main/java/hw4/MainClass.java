package hw4;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import hw4.controller.ElevatorController;
import hw4.controller.Request;
import hw4.controller.generator.RequestGenerator;

public class MainClass {
    public static void main(String[] args) {
        int delay;
        int requestsCount;
        int floorsNum;
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of floors:");
        floorsNum = scanner.nextInt();  
        
        System.out.println("Enter delay (millisecs):");
        delay = scanner.nextInt();

        System.out.println("Enter count of requests:");
        requestsCount = scanner.nextInt();

        BlockingQueue<Request> requests = new LinkedBlockingQueue<>();
        ElevatorController elevatorController = new ElevatorController(requests);
        RequestGenerator requestGenerator = new RequestGenerator(requests)
            .setDelay(delay)
            .setRequestsCount(requestsCount)
            .setFloorsNum(floorsNum);

        Thread ecThread = new Thread(elevatorController);
        Thread rgThread = new Thread(requestGenerator);

        ecThread.start();
        rgThread.start();

        try {
            rgThread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        elevatorController.stop();
    }
}
