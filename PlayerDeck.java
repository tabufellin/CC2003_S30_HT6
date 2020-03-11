import java.util.ArrayList;

public class PlayerDeck {

    private ArrayList<String> playerCards = new ArrayList<>();


    public void addCard(String card) {
        playerCards.add(card);
    }

    public boolean containsCard(String card) {
        if (playerCards.contains(card)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getPlayerCards() {
        return playerCards;
    }
}
