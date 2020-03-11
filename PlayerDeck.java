package CC2003_S30_HT6;

import java.util.ArrayList;
import java.util.Map;

public class PlayerDeck {

    private ArrayList<String> playerCards = new ArrayList<>();


    public void addCard(String card) {
        playerCards.add(card);
    }

    public boolean containsCard(String card) {
        return playerCards.contains(card);
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }


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

    public void getForCard (ArrayList<String> collection,Map<String, String> implementedMap, String[] s) {
        int i = 0;
        for (String value : s) {
            for (String item : collection  ) {
                String x = implementedMap.get(item);
                if (x.equals(value)) {
                    System.out.println(item + "de tipo: " + value);
                }
            }
        }
    }
}
