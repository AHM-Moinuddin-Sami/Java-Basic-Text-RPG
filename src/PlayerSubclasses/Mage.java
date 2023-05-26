package PlayerSubclasses;

import java.util.Random;
import java.util.Scanner;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;

public class Mage extends Player{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8659912062999454825L;
	int buffTurns = 0;
	Random random = new Random();

	public Mage() {
		super(60, 30, 2, 100, 40);
	}
	
	public double basicAttack() {
		double damage = random.nextDouble() * (super.getAttack() * 1.1 - 5) + 5;

		return damage;
	}
	
	public double shield() {
		double defBuff = Player.currentDefence*0.5;
		Player.currentDefence += defBuff;
		return defBuff;
	}
	
	public double heal() {
		double healAmount = random.nextDouble() * 10 + 5;

		Player.currentMana -= 5;
		if(Player.currentHealth<=super.getMaxHealth()-healAmount)		
			Player.currentHealth += healAmount;		
		else
			Player.currentHealth = super.getMaxHealth();
			
		return healAmount;
	}
	
    public void manaRegen() {
    	if(currentMana < mana)
    		currentMana +=2;
    }
	
	public boolean options(Scanner inp, Enemy enemy) {
//		Scanner inp = new Scanner(System.in);
		String input;
//		boolean validInput = true;
		
		if(buffTurns>0) //Handling Defence turn count
			buffTurns--;
		while(true) {
//		do {
			System.out.println();
			System.out.println("Fireball  |  Mana Shield  |  Greater Heal  |  Escape");
			System.out.println("Write the action you wish to do: ");
			input = inp.nextLine();
			if(input.equalsIgnoreCase("fireball"))
			{
				System.out.println("You cast Fireball and deal "+ enemy.takeDamage(basicAttack())+ " damage.");
			}
			else if(input.equalsIgnoreCase("shield") || input.equalsIgnoreCase("minor shield"))
			{
				if(buffTurns == 0)
				{
					System.out.println("You cast Mana Shield and gain "+ shield()+" additional defence for 3 turns.");
					buffTurns = 3;
				}
				else
				{
					System.out.println("You cast Mana Shield and gain "+ shield()+" additional defence for 3 more turns.");
					buffTurns += 3;
				}
			}
			else if(input.equalsIgnoreCase("greater heal") || input.equalsIgnoreCase("heal"))
			{
				System.out.println("You cast Greater Heal and heal for "+heal()+" health points.");
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
    	return "Class: Mage HP: "+ currentHealth + "/"+ super.getMaxHealth() +" Mana: "+ currentMana +"/"+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;
    }

	@Override
	public String defaultStats() {
		return "Mage\nMax HP: "+ super.getMaxHealth()+" Max Mana: "+ mana +" ATK: "+ currentAttackPower +" DEF: "+ currentDefence;		
	}

}
