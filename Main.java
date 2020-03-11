import CC2003_S30_HT6.MapFactory;
import CC2003_S30_HT6.PlayerDeck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        MapFactory<String, String> myMap = new MapFactory<>();
        ArrayList<String> cardCollection = new ArrayList<>();
        ArrayList<String> duplicatedCollection = new ArrayList<>();
        String userSelection = "9999";
        char selectedOption = 'Q';

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

        long inicio = System.nanoTime();

        // Reads from cards_desc.txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cards_desc.txt"));
            String line;

            while((line = reader.readLine()) != null) {
                System.out.println(line);
                String mapValues[] = line.split("\\|");
                String key = mapValues[0];
                String value = mapValues[1];
                implementedMap.put(key,value);
            }
        } catch (Exception E) {
            System.err.println("There was an error while converting the file to a map");
        }

        long fin = System.nanoTime();

        double dif =(double) (fin - inicio) * 1.0e-9;
        System.out.println(dif);

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

                    System.out.println("Ingrese la carta para agregar a la coleccion");
                    String selectedCard = userInput.nextLine();

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
                    System.out.println("Ingrese la carta a la que desea ver su tipo ");
                    selectedCard = userInput.nextLine();
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
                    PlayerDeck p = new PlayerDeck();
                    String[] tiposCartas = {"Monstruo","Hechizo", "Trampa" };
                    p.getForCard(cardCollection,implementedMap, tiposCartas);

                }
                break;

                case "5": {
                    System.out.println("Nombre y tipo de todas las cartas existentes: ");
                    System.out.println();
                    Iterator<String> valueIterator = implementedMap.values().iterator();
                    Iterator<String> keyIterator = implementedMap.keySet().iterator();

                    while (valueIterator.hasNext()) {
                        System.out.println(keyIterator.next() + " Type: " + valueIterator.next());
                    }
                }
                break;

                case "6":{
                    PlayerDeck d = new PlayerDeck();
                    String[] tiposCartas = {"Monstruo","Hechizo", "Trampa" };
                    d.getCardByTypePrinter("Monstruo", implementedMap);
                    for (String tiposCarta : tiposCartas) {
                        int cuantas = d.getCardByTypePrinter(tiposCarta, implementedMap);
                        System.out.println("La cantidad de cartas de tipo " + tiposCarta + ": " + cuantas);
                    }
                }

                break;

            }


        }
        // implementedMap.size();
        //System.out.println(Arrays.toString(implementedMap.entrySet().toArray()));
    }
}
