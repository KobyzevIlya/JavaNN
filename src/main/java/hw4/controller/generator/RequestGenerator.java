package hw4.controller.generator;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

import hw4.controller.Request;
import hw4.elevator.State;

/**
 * The RequestGenerator class generates random requests and adds them to the request queue.
 */
public class RequestGenerator implements Runnable {
    private int floorsNum = 20;
    private int delay = 500;
    private int requestsCount = 1;
    private BlockingQueue<Request> requests;

    /**
     * Creates a new RequestGenerator instance with the specified queue of requests.
     *
     * @param requests the queue of requests
     */
    public RequestGenerator(BlockingQueue<Request> requests) {
        this.requests = requests;
    }

    /**
     * Sets the number of floors in the building.
     *
     * @param floorsNum the number of floors
     * @return the RequestGenerator instance
     */
    public RequestGenerator setFloorsNum(int floorsNum) {
        this.floorsNum = floorsNum;
        return this;
    }

    /**
     * Sets the delay between generating requests.
     *
     * @param delay the delay in milliseconds
     * @return the RequestGenerator instance
     */
    public RequestGenerator setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    /**
     * Sets the number of requests to generate.
     *
     * @param requestsCount the number of requests
     * @return the RequestGenerator instance
     */
    public RequestGenerator setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
        return this;
    }

    @Override
    public void run() {
        Random random = new Random(42);

        while (requestsCount-- > 0) {
            int floor = Math.abs(random.nextInt()) % floorsNum + 1;
            State direction = random.nextInt() % 2 == 0 ? State.UP : State.DOWN;

            if (floor == floorsNum && direction == State.UP) {
                --floor;
            }
            if (floor == 1 && direction == State.DOWN) {
                ++floor;
            }

            System.out.println("Added request: floor " + floor + " direction " + direction);
            requests.add(new Request(floor, direction));

            try {
                Thread.sleep(delay);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
