package Model;

import java.util.ArrayList;


/* Дочерний класс */
public class Hamster extends Pet {

    public Hamster() {

    }

    public Hamster(int petID, String petName, String petType, ArrayList<String> commands) {
        setPetID(petID);
        setPetName(petName);
        setPetType(petType);
        setCommands(commands);
    }

}
