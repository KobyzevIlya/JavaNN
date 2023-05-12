package hw4.elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import hw4.controller.Request;

public class Elevator implements Runnable {
    private int id;
    private State state;
    private int currentFloor;

    private int requestFloor;
    private State requestDirection;
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

    public Elevator setCurrentFloor(int floor) {
        this.currentFloor = floor;
        return this;
    }

    public Elevator setRequestDirection(State direction) {
        this.requestDirection = direction;
        return this;
    }

    @Override
    public void run() {
        System.out.println("Elevator №" + id + " started ride");
        
        int step = state == State.UP ? 1 : -1;
        while (currentFloor != requestFloor) {
            System.out.println("Elevator №" + id + " now on floor " + currentFloor + ", state " + state);

            currentFloor += step;
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.println("Elevator №" + id + " catch passanger on floor " + currentFloor);
        
        // state = state == State.UP ? State.DOWN : State.UP;
        state = requestDirection;
        requestFloor = requestDirection == State.UP ? floorsNum : 1;

        List<Boolean> avaibleFloors;
        step = state == State.UP ? 1 : -1;
        while (currentFloor != requestFloor) {
            System.out.println("Elevator №" + id + " now on floor " + currentFloor + ", state " + state);
            
            avaibleFloors = getAvaibleFloors();
            if (avaibleFloors.get(currentFloor)) {
                System.out.println("Elevator №" + id + " catch passanger on floor " + currentFloor);

                removeRequests(currentFloor, state);
            }
            currentFloor += step;
            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.println("Elevator №" + id + " finished ride");
        state = State.IDLE;
    }

    public State getState() {
        return state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getId() {
        return id;
    } 

    private List<Boolean> getAvaibleFloors() {
        List<Boolean> avaibleFloors = new ArrayList<>(Collections.nCopies(floorsNum + 1, false));
        for (Request request : requests) {
            if (state == State.UP) {
                if ((request.getRequestFloor() >= currentFloor && request.getDirection() == state)) {
                    avaibleFloors.set(request.getRequestFloor(), true);
                }
            }
            if (state == State.DOWN) {
                if ((request.getRequestFloor() <= currentFloor && request.getDirection() == state)) {
                    avaibleFloors.set(request.getRequestFloor(), true);
                }
            }
        }
        return avaibleFloors;
    }

    private void removeRequests(int requestFloor, State direction) {
        for (Request request : requests) {
            if (direction == request.getDirection() && requestFloor == request.getRequestFloor()) {
                requests.remove(request);
            }
        }
    }
}
