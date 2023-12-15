public class Command {
    private final Lens lens;
    private final Operation operation;

    public Command(String input) {
        if (input.contains("=")) {
            String[] sides = input.split("=");
            operation = Operation.ADD;
            lens = new Lens(sides[0], Integer.parseInt(sides[1]));
        } else {
            String[] sides = input.split("-");
            operation = Operation.REMOVE;
            lens = new Lens(sides[0], 0);
        }
    }

    public Lens getLens() {
        return lens;
    }

    public Operation getOperation() {
        return operation;
    }
}
