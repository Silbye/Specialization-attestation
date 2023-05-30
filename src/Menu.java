import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        Logic logic = new Logic();
        Counter count = new Counter();
        boolean flag = true;
        /* ArrayList<Pet> pets = logic.generatePets(); */
        ArrayList<Pet> pets = new ArrayList<Pet>();
        while (flag) {
            System.out.print("Options: 1 - Create new pet\n" +
                    "2 - Print all pets info\n" +
                    "3 - Print pet commands\n" +
                    "4 - Train pet\n" +
                    "0 - Exit\n");
            char entered = scanner.next().charAt(0);
            switch (entered) {
                case '1':
                    pets.add(logic.createNewPet(logic.getSize(pets)));
                    break;
                case '2':
                    logic.printAllPets(pets);
                    break;
                case '3':
                    logic.getPetCommands(logic.petSelect(pets));
                    count.add();
                    break;
                case '4':
                    logic.trainPet(logic.petSelect(pets));
                    break;
                case '0':
                    System.out.println("Goodbye!");
                    flag = false;
                    break;
                default:
                    System.out.println("Error, incorrect input!");
            }
        }
    }
}
