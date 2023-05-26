package Systems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import CharacterSkeletons.Enemy;

public class EnemyDirectory {
    private EnemyStack enemyStack;

    public void run(Scanner input) {
        loadDirectory();

        while (true) {
            System.out.println("Welcome to the Monster Directory");
            System.out.println("Please select an option from below:");
            System.out.println("1. Add a monster");
            System.out.println("2. Edit a monster's data");
            System.out.println("3. Remove a monster");
            System.out.println("4. See the monster catalogue");
            System.out.println("5. Exit");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                case "add":
                    addMonster(input);
                    saveDirectory();
                    break;
                case "2":
                case "edit":
                    editMonster(input);
                    saveDirectory();
                    break;
                case "3":
                case "remove":
                    removeMonster(input);
                    saveDirectory();
                    break;
                case "4":
                case "catalogue":
                    displayCatalogue();
                    break;
                case "5":
                case "exit":
                    System.out.println("Quitting...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    public void addMonster(Scanner input) {
        System.out.print("Enter the name of the monster you want to add: ");
        
        String name = input.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Invalid name. Monster not added.");
            return;
        }

//        The extra input.newLine() is for consuming trailing new lines from scanner
//        
//        Getting new information for the monster
        
        System.out.print("Enter the level of the monster: ");
        int level = input.nextInt();
        input.nextLine();

        System.out.print("Enter the default health of the monster: ");
        double health = input.nextDouble();
        input.nextLine();

        System.out.print("Enter the default defense of the monster: ");
        double defense = input.nextDouble();
        input.nextLine();

        System.out.print("Enter the default attack power of the monster: ");
        double attackPower = input.nextDouble();
        input.nextLine();

        Enemy monster = new Enemy(name, health, attackPower, defense, level);
        enemyStack.push(monster);

        System.out.println("Monster added successfully.");
    }

    public void editMonster(Scanner input) {
        System.out.print("Enter the name of the monster you want to edit: ");
        String name = input.nextLine().trim();
        boolean isEdited = false;

        for (int i = 0; i < enemyStack.getLength(); i++) {
            Enemy monster = enemyStack.getStckElement(i);
            if (monster.getName().equalsIgnoreCase(name)) {
                System.out.println("Previous details of the monster:");
                System.out.println(monster);

                System.out.print("Enter the new name: ");
                String newName = input.nextLine().trim();

                System.out.print("Enter the new level: ");
                int newLevel = input.nextInt();
                input.nextLine(); 

                System.out.print("Enter the new health: ");
                double newHealth = input.nextDouble();
                input.nextLine(); 

                System.out.print("Enter the new defense: ");
                double newDefense = input.nextDouble();
                input.nextLine(); 

                System.out.print("Enter the new attack power: ");
                double newAttackPower = input.nextDouble();
                input.nextLine(); 

                monster.setName(newName);
                monster.setLevel(newLevel);
                monster.setMaxHealth(newHealth);
                monster.setMaxDef(newDefense);
                monster.setMaxAttack(newAttackPower);

                System.out.println("Monster details successfully edited.");
                isEdited = true;
                break;
            }
        }

        if (!isEdited) {
            System.out.println("Monster with this name does not exist in the directory.");
        }
    }

    public void removeMonster(Scanner input) {
        System.out.print("Enter the name of the monster you want to remove: ");
        String name = input.nextLine().trim();
        boolean isRemoved = false;

        for (int i = 0; i < enemyStack.getLength(); i++) {
            Enemy monster = enemyStack.getStckElement(i);
            if (monster.getName().equalsIgnoreCase(name)) {
                enemyStack.remove(i);
                System.out.println("Monster removed successfully.");
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            System.out.println("Monster with this name does not exist in the directory.");
        }
    }

    public void displayCatalogue() {
        System.out.println("Monster Catalogue:");

        if (enemyStack.isEmpty()) {
            System.out.println("No monsters found.");
            return;
        }

        for (int i = 0; i < enemyStack.getLength(); i++) {
            Enemy monster = enemyStack.getStckElement(i);
            if(monster != null)
            	System.out.println((i + 1) + ". " + monster);
        }
    }

    public void loadDirectory() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("enemyDir.dat"))) {
        	
            Enemy[] stack = (Enemy[]) inputStream.readObject();
            
            enemyStack = new EnemyStack(stack.length);
            
            for (Enemy monster : stack) {
                enemyStack.push(monster);
            }
            
//            System.out.println("Monster directory loaded successfully.");
            
        } catch (IOException | ClassNotFoundException| NullPointerException e) {
        	
        	enemyStack = new EnemyStack(1);
        	System.out.println("Monster Directory does not exist, thus cannot be loaded.");
            System.out.println("Monster directory created.");
        }
    }
    
    public EnemyStack getLoadedStack() {
    	loadDirectory();
    	return enemyStack;
    }

    public void saveDirectory() {
        Enemy[] stack = enemyStack.getStack();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("enemyDir.dat"))) {
            outputStream.writeObject(stack);
            System.out.println("Monster directory saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save the monster directory.");
        }
    }
}
