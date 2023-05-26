package PlayerSubclasses;

import java.util.Random;
import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;

public class Knight extends Player{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8690695144696386449L;
	int buffTurns = 0;
	Random random = new Random();

	public Knight() {
		super(100, 15, 10, 20, 35);
	}
	
	public double basicAttack() {
		double damage = random.nextDouble() * (super.getAttack() * 1.2 - 5) + 5;

		return damage;
	}
	
	public double shield() {
		double defBuff = Player.currentDefence*0.5;
		Player.currentDefence += defBuff;
		return defBuff;
	}
	
	public double heal() {
		double healAmount = random.nextDouble() * 5 + 5;

		Player.currentMana -= 5;
		if(Player.currentHealth<=super.getMaxHealth()-healAmount)		
			Player.currentHealth += healAmount;		
		else
			Player.currentHealth = super.getMaxHealth();
			
		return healAmount;
	}
	
	public boolean options(Scanner inp, Enemy enemy) {
		String input;
//		boolean validInput = true;
		
		if(buffTurns>0) //Handling Defence turn count
			buffTurns--;
		while(true) {
//		do {
			System.out.println();
			System.out.println("Slash  |  Guard  |  Minor Heal  |  Escape");
			System.out.println("Write the action you wish to do: ");
			input = inp.nextLine();
			if(input.equalsIgnoreCase("slash"))
			{
				System.out.println("You use Slash and deal "+ enemy.takeDamage(basicAttack()) + " damage.");
			}
			else if(input.equalsIgnoreCase("guard"))
			{
				if(buffTurns == 0)
				{
					System.out.println("You ready your shield to Guard and gain "+ shield()+" additional defence for 2 turns.");
					buffTurns = 2;
				}
				else
				{
					System.out.println("You ready your shield to Guard and gain "+ shield()+" additional defence for 2 more turns.");
					buffTurns += 2;
				}
			}
			else if(input.equalsIgnoreCase("minor heal") || input.equalsIgnoreCase("heal"))
			{
				System.out.println("You cast Minor Heal and heal for "+heal()+" health points.");
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
//				validInput = false;
				continue;
			}
			break;
		}
//		while(!validInput);
		
		return false;
	}
	
    public String toString() {
    	return "Class: Knight HP: "+ currentHealth + "/"+ super.getMaxHealth() +" Mana: "+ currentMana +"/"+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;
    }

	@Override
	public String defaultStats() {
		return "Knight\nMax HP: "+ super.getMaxHealth()+" Max Mana: "+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;		
	}

}
