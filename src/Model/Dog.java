package Model;

import java.util.ArrayList;


/* Дочерний класс */
public class Dog extends Pet {

    public Dog(){

    }

    public Dog(int petID, String petName, String petType, ArrayList<String> commands) {
        setPetID(petID);
        setPetName(petName);
        setPetType(petType);
        setCommands(commands);
    }

}
