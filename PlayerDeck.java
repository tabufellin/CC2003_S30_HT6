import java.util.ArrayList;
import java.util.Map;
/**
 * <h1>PlayerDeck</h1>
 * Contains needed methods to operate cards on the player deck.
 * <p>
 *
 * @author Sebastian Gonzales (tabufellin) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-11
 **/
public class PlayerDeck {

    private ArrayList<String> playerCards = new ArrayList<>();

    /**
     * This method gets cards according to their type.
     *
     * @return int with the number of cards by type.
     */
    public int getCardByTypePrinter (String type, Map<String, String> implementedMap ) {
        int contadorCartaTipo = 0;

        for (Map.Entry<String, String> entry : implementedMap.entrySet()) {
            if (entry.getValue().equals(type)) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                contadorCartaTipo++;
            }
        }
        return contadorCartaTipo;
    }

    /**
     * This method gets the cards in the deck, and prints them in the format: card, type.
     *
     * @return
     */
    public void getForCard (ArrayList<String> collection,Map<String, String> implementedMap, String[] s) {
        int i = 0;
        for (String value : s) {
            for (String item : collection  ) {
                String x = implementedMap.get(item);
                if (x.equals(value)) {
                    System.out.println(item + " de tipo: " + value);
                }
            }
        }
    }
}
