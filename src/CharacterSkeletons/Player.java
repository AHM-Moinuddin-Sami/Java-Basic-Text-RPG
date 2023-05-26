package CharacterSkeletons;

import java.util.Scanner;

public abstract class Player extends MainSkeleton implements Actor {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2182911395397510559L;
	protected static double currentHealth;
    protected static double currentAttackPower;
    protected static double currentDefence;
	protected double mana;
	protected static double currentMana;
	protected int escapeChance;
    
    public Player(double health, double attackPower, double defence, double mana, int escapeChance) {
    	super(health, attackPower, defence);
    	this.mana = mana;
	    currentHealth = health;
	    currentAttackPower = attackPower;
	    currentDefence = defence;
    	currentMana = mana;
    }
    
	public double getHealth() {
		return currentHealth;
	}
    
    public double getDefence() {
		return currentDefence;
	}

    public double getAttack() {
        return currentAttackPower;
    }
    
    public double getMana() {
    	return currentMana;
    }
    
    public void setHealth(double health) {
		currentHealth = health;
	}
    
    public void setMana(double mana) {
    	currentMana = mana;
    }
    
    public void setDefence(double defence) {
		currentDefence = defence;
	}

    public void setAttack(double attack) {
        currentAttackPower = attack;
    }
    
    public void manaRegen() {
    	if(currentMana < mana)
    		currentMana ++;
    }
    
	public double takeDamage(double damage) {
        double damageTaken = (damage-(currentDefence*.5));
        
        if(damageTaken>0)
        {
        	currentHealth -= damageTaken;
        	return damageTaken;
        }
        else
        	return 0;
    }
	
	public abstract boolean options(Scanner inp, Enemy enemy);
	public abstract String defaultStats();
//	public abstract double basicAttack();
//	public abstract double heal();
//	public abstract double shield();
	
	public boolean isDead() {
		return currentHealth<=0;
	}

}
