import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Logic {


    /* Создание питомцев по готовому шаблону */
    public ArrayList<Pet> generatePets() {
        Dog firstPet = new Dog(0, "Woofer", "Dog", new ArrayList<String>());
        Cat secondPet = new Cat(1, "Vasya", "Cat", new ArrayList<String>());
        Hamster thirdPet = new Hamster(2, "Stepa", "Hamster", new ArrayList<String>());
        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(firstPet);
        pets.add(secondPet);
        pets.add(thirdPet);
        return pets;
    }

    /* Проверка того, знает ли питомец выбранную команду или нет */
    public boolean checkCommand(ArrayList<String> commands, String command) {
        if (commands.size() == 0) {
            return true;
        } else {
            for (int i = 0; i < commands.size(); i++) {
                if (command.equals(commands.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Получение количества питомцев в списке */
    public int getSize(ArrayList<Pet> pets) {
        int id = pets.size();
        return id;
    }

    /* Выбор питомца по индексу */
    public Pet petSelect(ArrayList<Pet> pets) {
        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone) {
            System.out.print("Enter pet id: ");
            try {
                int entered = Integer.parseInt(scanner.nextLine());
                if (entered < pets.size()) {
                    return pets.get(entered);
                } else {
                    System.out.println("Error, incorrect id");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input, please enter a number");
            }
        }
        return null;
    }

    /* Выбор типа питомца */
    public String enterPetType() {
        Scanner scanner = new Scanner(System.in);
        String petType = null;
        boolean typeEntered = false;
        while (!typeEntered) {
            System.out.print("Enter pet type (1 - Dog, 2 - Cat, 3 - Hamster): ");
            Character entered = scanner.nextLine().charAt(0);
            switch (entered) {
                case '1':
                    petType = "Dog";
                    typeEntered = true;
                    break;
                case '2':
                    petType = "Cat";
                    typeEntered = true;
                    break;
                case '3':
                    petType = "Hamster";
                    typeEntered = true;
                    break;
                default:
                    System.out.println("Error, incorrect input!");
            }
        }
        return petType;
    }

    /* Добавление нового питомца */
    public Pet createNewPet(int petID) {
        Scanner scanner = new Scanner(System.in);
        int id = petID;
        String petName = null;
        String petType = null;
        boolean isDone = false;

        while (!isDone) {
            petType = enterPetType();

            System.out.print("Enter pet name: ");
            petName = scanner.nextLine();

            if (petType.equals("Dog")) {
                return new Dog(id, petName, petType, new ArrayList<String>());
            } else if (petType.equals("Cat")) {
                return new Cat(id, petName, petType, new ArrayList<String>());
            } else if (petType.equals("Hamster")) {
                return new Hamster(id, petName, petType, new ArrayList<String>());
            } else {
                System.out.println("Unknown error");
            }

        }
        return null;
    }

    /* Вывод информации о всех питомцах */
    public void printAllPets(ArrayList<Pet> pets) {
        if (pets.size() != 0) {
            for (int i = 0; i < pets.size(); i++) {
                pets.get(i).printPetInfo();
            }
        } else {
            System.out.println("Pets database is empty!");
        }
    }

    /* Вывод всех команд конкретного питомца */
    public void getPetCommands(Pet pet) {
        ArrayList<String> commands = pet.getCommands();
        if (commands.size() != 0) {
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(commands.get(i));
            }
        } else {
            System.out.println("This pet knows no commands!");
        }
    }

    /* Обучение питомца новым командам */
    public void trainPet(Pet pet) {
        ArrayList<String> commands = pet.getCommands();
        Scanner scanner = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone) {
            System.out.println("Available commands: 1 - Sit, 2 - Shake, 3 - Voice, 4 - Lay, 0 - exit");
            System.out.printf("Enter command you want to learn: ");
            Character entered = scanner.nextLine().charAt(0);
            switch (entered) {
                case '1':
                    boolean isValid = checkCommand(commands, "Sit");
                    if (isValid) {
                        commands.add("Sit");
                        System.out.println("Successfully learned new command!");
                    } else {
                        System.out.println("This command is already learned!");
                    }
                    break;
                case '2':
                    isValid = checkCommand(commands, "Shake");
                    if (isValid) {
                        commands.add("Shake");
                        System.out.println("Successfully learned new command!");
                    } else {
                        System.out.println("This command is already learned!");
                    }
                    break;
                case '3':
                    isValid = checkCommand(commands, "Voice");
                    if (isValid) {
                        commands.add("Voice");
                        System.out.println("Successfully learned new command!");
                    } else {
                        System.out.println("This command is already learned!");
                    }
                    break;
                case '4':
                    isValid = checkCommand(commands, "Lay");
                    if (isValid) {
                        commands.add("Lay");
                        System.out.println("Successfully learned new command!");
                    } else {
                        System.out.println("This command is already learned!");
                    }
                    break;
                case '0':
                    System.out.println("Exiting");
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        pet.setCommands(commands);
    }
}
