import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * <h1>Main</h1>
 * Main class that runs the card deck program. It uses a selected map from the MapFactory class.
 * <p>
 *
 * @author Sebastian Gonzales (tabufellin) Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-03-11
 **/
public class Main {
    public static void main(String[] args) {

        MapFactory<String, String> myMap = new MapFactory<>();
        ArrayList<String> cardCollection = new ArrayList<>();
        ArrayList<String> duplicatedCollection = new ArrayList<>();
        String userSelection = "9999";

        // User chooses the map to be implemented
        Scanner userInput =  new Scanner(System.in);
        System.out.println("Ingrese el tipo de mapa que desea utilizar (por defecto se utiliza Tree Map: ");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1 -> Hash Map");
        System.out.println("2 -> Linked Hash Map");
        System.out.println("3 -> Tree Map");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print(">> ");
        String chosenMap = userInput.nextLine();
        Map<String, String> implementedMap = myMap.getMap(chosenMap);
        System.out.println();

        // Reads from cards_desc.txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cards_desc.txt"));
            String line;

            // Generates the map
            while((line = reader.readLine()) != null) {
                String mapValues[] = line.split("\\|");
                String key = mapValues[0];
                String value = mapValues[1];
                implementedMap.put(key,value);
            }
        } catch (Exception E) {
            System.err.println("There was an error while converting the file to a map");
        }


        while (!userSelection.equals("0")) {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Seleccione la opcion que desea realizar");
            System.out.println("0 -> terminar el programa");
            System.out.println("1 -> agregar una carta");
            System.out.println("2 -> mostrar el tipo de una carta especifica");
            System.out.println("3 -> Mostrar nombre, tipo y cantidad de cada carta en la coleccion");
            System.out.println("4 -> Mostrar nombre, tipo y cantidad de cada carta en la coleccion, ordenado por tipo");
            System.out.println("5 -> Mostrar nombre y tipo de todas las cartas existentes");
            System.out.println("6 -> Mostrar el nombre y tipo de todas las cartas existentes, ordenadas por tipo");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.print(">> ");
            userSelection = userInput.nextLine();
            switch (userSelection) {

                case "1":
                    // Adds a card to the collection
                    System.out.println("Ingrese la carta para agregar a la coleccion");
                    String selectedCard = userInput.nextLine();

                    // Only if it exists
                    if(implementedMap.containsKey(selectedCard)) {
                        cardCollection.add(selectedCard);
                        System.out.println(selectedCard + " se agrego a la coleccion");
                        System.out.println("Presione enter para continuar");
                        userInput.nextLine();
                    } else {
                        System.err.println("La carta introducida no existe. Por favor revise la escritura de la misma");
                    }
                    break;

                case "2":

                    // Shows card type
                    System.out.println("Ingrese la carta a la que desea ver su tipo ");
                    selectedCard = userInput.nextLine();

                    // Only if it exists
                    if (implementedMap.containsKey(selectedCard)) {
                        System.out.println(selectedCard + " es de tipo " + implementedMap.get(selectedCard));
                        System.out.println("Presione enter para continuar");
                        userInput.nextLine();

                    } else {
                        System.err.println("La carta introducida no existe. Por favor revise la escritura de la misma");
                    }

                    break;

                case "3":
                    System.out.println("Las cartas en la coleccion son: ");
                    duplicatedCollection = (ArrayList<String>)cardCollection.clone();

                    for(int i = 0; i < cardCollection.size(); i ++) {
                        // Counts the number of cards in the collection
                        String current = duplicatedCollection.get(0);
                        if(!current.equals("0")) {
                            int cardNumber = Collections.frequency(duplicatedCollection,current);
                            duplicatedCollection.removeAll(Arrays.asList(current));
                            for(int j = 0; j < cardNumber; j++){
                                duplicatedCollection.add("0");
                            }

                            System.out.println(current + " de tipo: " + implementedMap.get(current) + " cantidad: " + cardNumber);
                        }
                    }
                    break;

                case "4": {

                    duplicatedCollection = (ArrayList<String>)cardCollection.clone();

                    for(int i = 0; i < cardCollection.size(); i ++) {
                        // Counts the number of cards in the collection
                        String current = duplicatedCollection.get(0);
                        if(!current.equals("0")) {
                            int cardNumber = Collections.frequency(duplicatedCollection,current);
                            duplicatedCollection.removeAll(Arrays.asList(current));
                            for(int j = 0; j < cardNumber; j++){
                                duplicatedCollection.add("0");
                            }

                            System.out.println("Cantidad de cartas: ");
                            System.out.println(current +  " cantidad: " + cardNumber);
                        }
                    }

                    // Prints the cards by type
                    System.out.println();
                    System.out.println("Ordenado por tipo: ");
                    System.out.println();
                    PlayerDeck p = new PlayerDeck();
                    String[] tiposCartas = {"Monstruo","Hechizo", "Trampa" };
                    p.getForCard(cardCollection,implementedMap, tiposCartas);
                }
                break;

                case "5": {
                    // This was implemented this way to allow for a better profiling, and easier visualization
                    printMap(implementedMap);
                }
                break;

                case "6":{

                    // Prints the map according to card types
                    PlayerDeck d = new PlayerDeck();
                    String[] tiposCartas = {"Monstruo","Hechizo", "Trampa" };
                    d.getCardByTypePrinter("Monstruo", implementedMap);
                    for (String tiposCarta : tiposCartas) {
                        int cuantas = d.getCardByTypePrinter(tiposCarta, implementedMap);   // Gets number by card type
                        System.out.println("-----------------------------------------------------------------------------------------");
                        System.out.println();
                        System.out.println("La cantidad de cartas de tipo " + tiposCarta + ": " + cuantas);
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------------------------------");
                    }
                }

                break;
            }
        }
    }

    /**
     * This method prints the map. Used for profiling purposes.
     */
    public static void printMap(Map<String, String> implementedMap) {
        // Prints the map using an iterator
        System.out.println("Nombre y tipo de todas las cartas existentes: ");
        System.out.println();
        Iterator<String> valueIterator = implementedMap.values().iterator();
        Iterator<String> keyIterator = implementedMap.keySet().iterator();
        //int count = 0;
        //while (valueIterator.hasNext() && count < 6000) used to get map complexity
        while (valueIterator.hasNext()) {
            System.out.println(keyIterator.next() + " Type: " + valueIterator.next());
            //count++;
        }
    }
}
