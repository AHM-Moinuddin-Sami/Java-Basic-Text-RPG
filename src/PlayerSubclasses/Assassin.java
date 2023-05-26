package PlayerSubclasses;

import java.util.Random;
import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;

public class Assassin extends Player{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2941046874053509584L;
	int buffTurns = 0;
	Random random = new Random();

	public Assassin() {
		super(80, 20, 5, 50, 65);
	}
	
	public double basicAttack() {
		double damage = random.nextDouble() * (super.getAttack() * 1.5 - 5) + 5;
		return damage;
	}
	
	public double shield() {
		double defBuff = Player.currentDefence*0.2;
		Player.currentDefence += defBuff;
		return defBuff;
	}
	
	public double heal() {
		double healAmount = random.nextDouble() * 3 + 5;

		Player.currentMana -= 5;
		if(Player.currentHealth<=super.getMaxHealth()-healAmount)		
			Player.currentHealth += healAmount;		
		else
			Player.currentHealth = super.getMaxHealth();
			
		return healAmount;
	}
	
	public boolean options(Scanner inp, Enemy enemy) {
		String input;
		if(buffTurns>0) //Handling Defence turn count
			buffTurns--;
		
		while(true) {
			System.out.println();
			System.out.println("Stab  |  Evade  |  Heal  |  Escape");
			System.out.println("Write the action you wish to do: ");
			
			input = inp.nextLine();
		        
			if(input.equalsIgnoreCase("stab"))
			{
				System.out.println("You use Stab and deal "+ enemy.takeDamage(basicAttack())+ " damage.");
			}
			else if(input.equalsIgnoreCase("evade"))
			{
				if(buffTurns == 0)
				{
					System.out.println("You ready your shield to defend and gain "+ shield()+" additional defence for 2 turns.");
					buffTurns = 2;
				}
				else
				{
					System.out.println("You ready your shield to defend and gain "+ shield()+" additional defence for 2 more turns.");
					buffTurns += 2;
				}
			}
			else if(input.equalsIgnoreCase("heal"))
			{
				System.out.println("You heal for "+heal()+" health points.");
			}
			else if(input.equalsIgnoreCase("escape"))
			{
				int randomNum = random.nextInt(100);
				
				if(randomNum<super.escapeChance)
				{
					System.out.println("You have successfully managed to escape.");
					inp.close();
					return true;
				}
				else
				{
					System.out.println("You have failed to escape.");
					inp.close();
					return false;
				}
			}
			else
			{
				System.out.println("Invalid Input. Please write your command again.");
				continue;
			}
			
			break;
		}
		
		return false;
	}
	
    public String toString() {
    	return "Class: Assassin HP: "+ currentHealth + "/"+ super.getMaxHealth() +" Mana: "+ currentMana +"/"+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;
    }

	@Override
	public String defaultStats() {
		return "Assassin\nMax HP: "+ super.getMaxHealth()+" Max Mana: "+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;		
	}

}
