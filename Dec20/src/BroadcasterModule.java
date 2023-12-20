import java.util.List;
import java.util.Queue;

public record BroadcasterModule(List<String> destinationModules) implements Module {

    private static final String NAME = "broadcaster";

    @Override
    public void receivePulse(Pulse pulse, String source, Queue<PulseCommand> commandQueue) {
        for (String destination : destinationModules) {
            commandQueue.add(new PulseCommand(pulse, destination, NAME));
        }
    }

    @Override
    public String name() {
        return NAME;
    }
}
