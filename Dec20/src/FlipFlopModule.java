import java.util.List;
import java.util.Queue;

public class FlipFlopModule implements Module {


    private final String name;
    private boolean on;
    private final List<String> destinationModules;

    public FlipFlopModule(String name, List<String> destinationModules) {
        this.name = name;
        on = false;
        this.destinationModules = destinationModules;
    }

    @Override
    public void receivePulse(Pulse pulse, String source, Queue<PulseCommand> commandQueue) {
        if (pulse.equals(Pulse.LOW)) {
            on = !on;
            for (String destination : destinationModules) {
                commandQueue.add(new PulseCommand(on ? Pulse.HIGH : Pulse.LOW, destination, name));
            }
        }
    }

    @Override
    public List<String> destinationModules() {
        return destinationModules;
    }

    @Override
    public String name() {
        return name;
    }
}
