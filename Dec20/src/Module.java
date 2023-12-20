import java.util.List;
import java.util.Queue;

public interface Module {

    void receivePulse(Pulse pulse, String source, Queue<PulseCommand> commandQueue);

    List<String> destinationModules();

    String name();

}
