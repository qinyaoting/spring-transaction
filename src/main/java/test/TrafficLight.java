package test;

/**
 * Created by chin on 5/17/16.
 */

enum Signal {
    GREEN, YELLOW, RED
}

public class TrafficLight {

    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                System.out.println("Green");
                break;
            case YELLOW:
                color = Signal.RED;
                System.out.println("Red");
                break;
            case GREEN:
                color = Signal.YELLOW;
                System.out.println("Yellow");
                break;

        }
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 10; i++) {
            t.change();
        }
    }


}
