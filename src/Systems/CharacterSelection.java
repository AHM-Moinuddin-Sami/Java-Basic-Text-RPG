package Systems;

import java.util.Scanner;

import CharacterSkeletons.Player;
import PlayerSubclasses.Assassin;
import PlayerSubclasses.Knight;
import PlayerSubclasses.Mage;

public class CharacterSelection {
	private Player knight = new Knight();
	private Player mage = new Mage();
	private Player assassin = new Assassin();
	
	public Player characterSelector(Scanner inp) {
		String choice;
		while(true) {
			System.out.println("Choose your class:");
			System.out.println("1. Knight");
			System.out.println("2. Mage");
			System.out.println("3. Assassin");
			System.out.println("Write class name to select class and write status-className to see default status");
			choice = inp.nextLine();
			
			if(choice.equalsIgnoreCase("knight"))
			{
				System.out.println("You have chosen Knight");
				return new Knight();
			}
			else if(choice.equalsIgnoreCase("status-knight"))
			{
				System.out.println(knight.defaultStats());
				System.out.println();
				continue;
			}
			else if(choice.equalsIgnoreCase("mage"))
			{
				System.out.println("You have chosen Mage");
				return new Mage();
			}
			else if(choice.equalsIgnoreCase("status-mage"))
			{
				System.out.println(mage.defaultStats());
				System.out.println();
				continue;
			}
			else if(choice.equalsIgnoreCase("assassin"))
			{
				System.out.println("You have chosen Assassin");
				return new Assassin();
			}
			else if(choice.equalsIgnoreCase("status-assassin"))
			{
				System.out.println(assassin.defaultStats());
				System.out.println();
				continue;
			}
			else
			{
				System.out.println("Invalid input.");
				System.out.println();
				continue;
			}
		}
	}
}
