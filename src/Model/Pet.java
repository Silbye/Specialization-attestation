package Model;

import java.util.ArrayList;


/* Родительский класс */
public class Pet {

    private int petID;
    private String petName;

    private String petType;
    private ArrayList<String> commands;

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetType() {
        return petType;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public Pet() {
    }

    public Pet(int petID, String petName, String petType, ArrayList<String> commands) {
        this.petID = petID;
        this.petName = petName;
        this.petType = petType;
        this.commands = commands;
    }

    public void printPetInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("id: %d, name: %s, type: %s, commands: ", this.getPetID(), this.getPetName(), this.getPetType()));
        sb.append(String.join(", ", this.getCommands()));
        System.out.println(sb);
    }

}
