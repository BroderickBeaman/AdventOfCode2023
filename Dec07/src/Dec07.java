import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dec07 {

    public static void main(String[] args) {
        List<Hand> sortableHands = new ArrayList<>( HandLoader.loadHands());
        Collections.sort(sortableHands);

        int sum = 0;

        for (int i = 0; i < sortableHands.size(); i++) {
            sum += sortableHands.get(i).getBet() * (i + 1);
        }

        System.out.println("Sum of bets: " + sum);
    }
}
