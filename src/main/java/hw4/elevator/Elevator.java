package hw4.elevator;

import java.util.concurrent.BlockingQueue;

import hw4.controller.Request;

public class Elevator implements Runnable {
    private int id;
    private State state;
    private int currentFloor;
    private int requestFloor;
    private int floorsNum;
    private BlockingQueue<Request> requests;

    public Elevator(int id) {
        this.id = id;
    }
    
    public Elevator setState(State state) {
        this.state = state;
        return this;
    }

    public Elevator setFloorsNum(int floorsNum) {
        this.floorsNum = floorsNum;
        return this;
    }

    public Elevator setRequests(BlockingQueue<Request> requests) {
        this.requests = requests;
        return this;
    }

    public Elevator setRequestFloor(int requestFloor) {
        this.requestFloor = requestFloor;
        return this;
    }

    @Override
    public void run() {
        
    }
}
