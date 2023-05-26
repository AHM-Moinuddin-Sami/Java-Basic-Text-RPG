package Systems;

import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;
import Navigation.PlayerMovement;
import World.GenerateWorld;

public class Launcher extends GenerateWorld<Enemy>{
	
	public Launcher(EnemyStack enemyStack) {
		super(enemyStack);
	}

	public void run(Player player, PlayerMovement<?> movement, SaveAndLoad save, EnemyStack enemyStack) {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
        	
            try {
            	System.out.println("Current position: (" + movement.getPlayerX() + ", " + movement.getPlayerY() + ")"); //Movement indicator per turn
                System.out.println("Enter a direction to move (north, south, east, west), 'status' to check your current status, 'save' to save the game, 'load' to load a saved game, or 'quit' to exit:");

                input = scanner.nextLine().toLowerCase();

                if (input.equals("quit")) { //Handling exit
                    break;
                } else if (input.equals("save")) { // Handling save during gameplay
                	
            		save = new SaveAndLoad(movement.getPlayerX(), movement.getPlayerY(), player, getWorld());
            		
                    save.saveGame();
                    
                    System.out.println("Game saved.");
                    continue;
                } else if (input.equals("load")) { //Handling load during gameplay
                	
                	Loader loader = save.loadGame();
                	
                	movement.setCoord(loader.getX(), loader.getY()); //setting loaded coords
                	
                	player = loader.getPlayer(); //getting loaded player status
                	
                	movement.setPlayer(player); //setting loaded player status
                	
                    System.out.println("Game loaded.");
                    
                    continue;
                }
                else if(input.equals("status")) { //Current status option
                	
                	System.out.println(player); //calling toString
                	
                	continue;
                }

                switch (input) { // 4 way movement
                    case "north":
                        movement.movePlayer(0, 1);
                        break;
                        
                    case "south":
                        movement.movePlayer(0, -1);
                        break;
                        
                    case "east":
                        movement.movePlayer(1, 0);
                        break;
                        
                    case "west":
                        movement.movePlayer(-1, 0);
                        break;
                        
                    default:
                        System.out.println("Invalid direction. Please try again.");
                        break;
                        
                }

                movement.checkEncounter(); //checks for events (only enemies for now)
            }
            catch(NullPointerException e)
            {
            	System.out.println("Fatal Error!"+e);
            	break;
            }
        }

        System.out.println("Exiting the game...");
        
        scanner.close();
    }
}
