package Model;

import java.util.ArrayList;


/* Дочерний класс */
public class Cat extends Pet {

    public Cat() {

    }

    public Cat(int petID, String petName, String petType, ArrayList<String> commands) {
        setPetID(petID);
        setPetName(petName);
        setPetType(petType);
        setCommands(commands);
    }

}
