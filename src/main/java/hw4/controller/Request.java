package hw4.controller;

import hw4.elevator.State;

public class Request {
    private int requestFloor;
    private State direction;

    Request(int requestFloor, State direction) {
        this.requestFloor = requestFloor;
        this.direction = direction;
    }

    public int getRequestFloor() {
        return requestFloor;
    }

    public State getDirection() {
        return direction;
    }
}
