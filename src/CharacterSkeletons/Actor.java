package CharacterSkeletons;

//Parent skeleton for all characters

public interface Actor {
	public double getHealth();
	public double getAttack();
	public double getMaxHealth();
	public double getMaxDef();
	public double getMaxAtk();
	public double getDefence();
	public double takeDamage(double damage);
	public boolean isDead();	
}
