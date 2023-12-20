import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputLoader extends InputLoaderParent {
    public static Map<String, Module> loadModules() {
        List<String> allLines = loadLines();
        List<Module> moduleList = allLines.stream().map(line -> {
            String[] parts = line.split(" -> ");
            String name = parts[0];
            String destinationModuleString = parts[1];
            List<String> destinationModules = new ArrayList<>();
            if (destinationModuleString.contains(",")) {
                destinationModules.addAll(Arrays.asList(destinationModuleString.split(", ")));
            } else {
                destinationModules.add(destinationModuleString);
            }
            if (name.contains("%")) {
                return new FlipFlopModule(name.substring(1), destinationModules);
            } else if (name.contains("&")) {
                return new ConjunctionModule(name.substring(1), destinationModules);
            } else {
                return new BroadcasterModule(destinationModules);
            }
        }).toList();

        moduleList.stream()
            .filter(module -> module instanceof ConjunctionModule)
            .forEach(module -> {
                ConjunctionModule conjunctionModule = (ConjunctionModule) module;
                moduleList.stream()
                        .filter(mod -> mod.destinationModules().contains(conjunctionModule.name()))
                        .map(Module::name)
                        .forEach(conjunctionModule::addInputModule);
            });

        return moduleList.stream().collect(Collectors.toMap(Module::name, Function.identity()));
    }
}
