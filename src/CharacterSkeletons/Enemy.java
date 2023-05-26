package CharacterSkeletons;

public class Enemy extends MainSkeleton implements Actor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5008889658612471890L;
	protected String name;
    protected double currentHealth;
    protected double currentDefence;
    protected double currentAttack;
    protected int lvl;

    public Enemy(String name, double health, double attackPower, double defence, int lvl) {
    	super(health, attackPower, defence);
    	this.name = name;
	    currentHealth = health;
	    this.lvl = lvl;
	}
    
	public String getName() {		
		return name;
	}
    
	public double getHealth() {
		return currentHealth;
	}
    
    public double getDefence() {
		return super.getMaxDef();
	}

    public double getAttack() {
        return super.getMaxAtk();
    }

	public double takeDamage(double damage) {
        double damageTaken = (double) (damage-(super.getMaxDef()*.5));
        
        if(damageTaken>0)
        {
        	currentHealth -= damageTaken;
        	return damageTaken;
        }
        else
        	return 0;
    }
	
	public boolean isDead() {
		return currentHealth<=0;
	}
    
    public String toString() {
    	return "Name: "+name+" Lvl: "+ lvl +" HP: "+ currentHealth +" ATK: "+ super.getMaxAtk() +" DEF: "+ super.getMaxDef() ;
    }

	public void setName(String newName) {
		name = newName;		
	}

	public void setLevel(int newLevel) {
		lvl = newLevel;
		
	}
    
}
