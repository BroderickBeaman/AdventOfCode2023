import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ConjunctionModule implements Module {

    private final String name;

    private final List<String> destinationModules;

    private final Map<String, Pulse> inputModuleMemory;

    public ConjunctionModule(String name, List<String> destinationModules) {
        this.name = name;
        this.destinationModules = destinationModules;
        inputModuleMemory = new HashMap<>();
    }

    @Override
    public void receivePulse(Pulse pulse, String source, Queue<PulseCommand> commandQueue) {
        inputModuleMemory.put(source, pulse);
        Pulse pulseToSend = inputModuleMemory.containsValue(Pulse.LOW) ? Pulse.HIGH : Pulse.LOW;
        for(String destination : destinationModules) {
            commandQueue.add(new PulseCommand(pulseToSend, destination, name));
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

    public void addInputModule(String inputModule) {
        inputModuleMemory.put(inputModule, Pulse.LOW);
    }

    public Map<String, Pulse> getInputModuleMemory() {
        return inputModuleMemory;
    }
}
