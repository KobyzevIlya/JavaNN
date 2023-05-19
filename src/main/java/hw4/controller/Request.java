package hw4.controller;

import hw4.elevator.State;

/**
 * The Request class represents a request for the elevator to a specific floor.
 */
public class Request {
    private int requestFloor;
    private State direction;

    /**
     * Creates a new Request instance with the specified request floor and direction.
     *
     * @param requestFloor the floor of the request
     * @param direction the direction of the request
     */
    public Request(int requestFloor, State direction) {
        this.requestFloor = requestFloor;
        this.direction = direction;
    }

    /**
     * Gets the floor of the request.
     *
     * @return the floor of the request
     */
    public int getRequestFloor() {
        return requestFloor;
    }

    /**
     * Gets the direction of the request.
     *
     * @return the direction of the request
     */
    public State getDirection() {
        return direction;
    }
}
