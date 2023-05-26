package CharacterSkeletons;

import java.io.Serializable;

public abstract class MainSkeleton implements Actor, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2443823801473462777L;
	private double health;
    private double attackPower;
    private double defence;
    
    public MainSkeleton(double health, double attackPower, double defence) {
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;

    }   
	
	public double getMaxHealth() {
		return health;
	}
	
	public double getMaxDef() {
		return defence;
	}
	
	public double getMaxAtk() {
		return attackPower;
	}
	
	public void setMaxHealth(double hp) {
		health = hp;
	}
	public void setMaxDef(double def) {
		defence = def;
	}
	public void setMaxAttack(double atk) {
		attackPower = atk;
	}
	
}
