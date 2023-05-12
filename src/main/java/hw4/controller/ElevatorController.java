package hw4.controller;

import java.util.concurrent.BlockingQueue;

import hw4.elevator.Elevator;
import hw4.elevator.State;

public class ElevatorController implements Runnable {
    private Elevator firstElevator;
    private Elevator secondElevator;
    private int floorsNum = 20;
    private boolean working = true;
    
    private BlockingQueue<Request> requests;

    public ElevatorController(BlockingQueue<Request> requests) {
        this.requests = requests;
        
        firstElevator = new Elevator(1)
            .setCurrentFloor(1)
            .setState(State.IDLE);
        secondElevator = new Elevator(2)
            .setCurrentFloor(1)
            .setState(State.IDLE);
    }

    ElevatorController setFloorsNum(int floorsNum) {
        this.floorsNum = floorsNum;
        return this;
    }

    @Override
    public void run() {
        while (working || !requests.isEmpty()) {
            try {
                if (firstElevator.getState() == State.IDLE || secondElevator.getState() == State.IDLE) {
                    Request request = requests.poll();
                    if (request == null) {
                        continue;
                    }
    
                    Elevator bestElevator;
                    if (firstElevator.getState() == State.IDLE && secondElevator.getState() == State.IDLE) {
                        if (Math.abs(firstElevator.getCurrentFloor() - request.getRequestFloor()) < Math.abs(secondElevator.getCurrentFloor() - request.getRequestFloor())) {
                            bestElevator = firstElevator;
                        } else {
                            bestElevator = secondElevator;
                        }
                    } else if (firstElevator.getState() == State.IDLE) {
                        bestElevator = firstElevator;
                    } else if (secondElevator.getState() == State.IDLE) {
                        bestElevator = secondElevator;
                    } else {
                        continue;
                    }

                    System.out.println("Request from floor " + request.getRequestFloor() + ", direction " + request.getDirection()
                        + " is assigned to elevator №" + bestElevator.getId());

                    bestElevator
                        .setRequests(requests)
                        .setFloorsNum(floorsNum)
                        .setRequestFloor(request.getRequestFloor())
                        .setState(request.getRequestFloor() >= bestElevator.getCurrentFloor() ? State.UP : State.DOWN)
                        .setRequestDirection(request.getDirection());

                    Thread elevatorThread = new Thread(bestElevator);
                    elevatorThread.start();
                    // elevatorThread.join();                    
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void stop() {
        this.working = false;
    }
}
