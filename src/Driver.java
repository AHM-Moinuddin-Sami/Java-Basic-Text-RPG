import java.util.InputMismatchException;
import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;
import Navigation.PlayerMovement;
import PlayerSubclasses.Knight;
import Systems.CharacterSelection;
import Systems.EnemyDirectory;
import Systems.EnemyStack;
import Systems.Launcher;
import Systems.Loader;
import Systems.SaveAndLoad;

// 
// 					Text Adventure RPG Game
// 
//  Project created by A.H.M. Moin Uddin Sami and S.M. Safiul Mahbub
// 

public class Driver {
	public static void main(String[] args) {

		EnemyDirectory dir = new EnemyDirectory(); //Enemy direction initialization
		Scanner inp = new Scanner(System.in);
		CharacterSelection select = new CharacterSelection(); //character selection initialization
		EnemyStack enemyStack = dir.getLoadedStack(); //loading enemy stack
		Player player = new Knight(); //Default placeholder player character
		PlayerMovement<Enemy> movement = new PlayerMovement<Enemy>(1,1, player, enemyStack); //Default starting position initialize
		SaveAndLoad save = new SaveAndLoad(player); //save and load initialization
		int choice;
		Launcher run = new Launcher(enemyStack); //main runner class initialization
		
		System.out.println("Welcome to Basic Text RPG");
		System.out.println("Select an option:");
		System.out.println("1. New Game");
		System.out.println("2. Load Game");
		System.out.println("3. Monster Directory");
		System.out.println("4. Exit Game");
		

		
		while(true) {

			try {
				choice = inp.nextInt();
				inp.nextLine(); //consuming trailing newline
				if(choice == 1)
				{
					player = select.characterSelector();
	            	movement.setPlayer(player);
					run.run(player, movement, save, enemyStack);
					break;
				}
				else if(choice == 2)
				{
	            	Loader loader = save.loadGame();
	            	movement.setCoord(loader.getX(), loader.getY()); //setting loaded player coordinates
	            	player = loader.getPlayer(); //getting loaded player status
	            	movement.setPlayer(player); //setting loaded player
	                System.out.println("Game loaded.");
					run.run(player, movement, save, enemyStack); //directly running main runner after loading
					break;
				}
				else if(choice == 3) {
					dir.run(inp);
					break;
				}
				else if(choice == 4) {
					System.out.println("Quiting ...");
					break;
				}
				else
				{
					System.out.println("Invalid choice! Please select again.");
				}
			
			}
			catch(InputMismatchException e) //In case of input data type error
			{
				System.out.println("Please enter an integer number as input.");
				inp.nextLine(); //Consuming trailing newline
			}
			
		}
	}
}