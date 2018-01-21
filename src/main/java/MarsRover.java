import java.util.Arrays;
import java.util.List;

public class MarsRover {
    private static final List<String> VALID_COMMANDS = Arrays.asList("L", "R", "M", "B");
    private static final List<String> DIRECTIONS = Arrays.asList("N", "E", "S", "W");

    private static final int Y = 1;
    private static final int X = 0;

    private String direction;
    // position which contains X and Y
    private int[] position;

    public MarsRover(int startingX, int startingY, String direction) {
        this.position = new int[]{startingX, startingY};
        this.direction = direction;
    }

    public String run(String input) {
        String[] commandArray = input.split("");

        validateCommands(input, commandArray);


        for (String command : commandArray) {
            switch (command) {
                case "M":
                    move();
                    break;
                case "R":
                    turnRight();
                    break;
                case "L":
                    turnLeft();
                    break;
                default:
                    turnRight();
                    turnRight();
                    move();
                    turnRight();
                    turnRight();
                    break;
            }
        }

        return asString();
    }


    private void move() {
        switch (direction) {
            case "N":
                position[Y] += +1;
                break;
            case "S":
                position[Y] += -1;
                break;
            case "E":
                position[X] += +1;
                break;
            default:
                position[X] += -1;
                break;
        }
    }

    private void turnLeft() {
        int left = (DIRECTIONS.indexOf(direction) + 3) % DIRECTIONS.size();
        direction = DIRECTIONS.get(left);
    }

    private void turnRight() {
        int right = (DIRECTIONS.indexOf(direction) + 1) % DIRECTIONS.size();
        direction = DIRECTIONS.get(right);
    }

    private static void validateCommands(String input, String[] commandArray) {
        for (String command : commandArray) {
            if (!VALID_COMMANDS.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + input);
            }
        }
    }

    private String asString() {
        return position[X] + " " + position[Y] + " " + direction;
    }
}