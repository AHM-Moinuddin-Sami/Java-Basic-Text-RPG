package Navigation;

import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;

public class Battle {
	protected void battle(Player player,Enemy enemy) {
		
        Scanner scanner = new Scanner(System.in);

        boolean playerTurn = true; //giving first turn to player always :)

        while (true) {
            if (playerTurn) {
            	System.out.println();
            	System.out.println("Player status: "+ player);
            	System.out.println("Enemy HP: "+ enemy.getHealth()); //showing enemy status
                System.out.println("Player's Turn");
                player.options(scanner, enemy);
                
                if (enemy.isDead()) {
                    System.out.println("You have defeated the "+ enemy.getName() +" !");
                    break;
                }
            } else {
            	System.out.println();
                System.out.println("Enemy's Turn");                
                System.out.println(enemy.getName()+" attacked you for "+ enemy.getAttack()+" damage ");
                System.out.println("You take "+ player.takeDamage(enemy.getAttack())+" damage");
                if (player.isDead()) {
                    System.out.println("You were defeated by the enemy!");
                    break;
                }
            }
            
            player.manaRegen(); //implementing player mana regen per turn
            
            playerTurn = !playerTurn; //changing turns
        }
    }
}
