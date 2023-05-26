package Navigation;

import CharacterSkeletons.Enemy;
import CharacterSkeletons.Player;
import Systems.EnemyStack;
import World.*;

public class PlayerMovement<T extends Enemy> extends GenerateWorld<Enemy>{
    private int playerX;
    private int playerY;
    Player player;
    Battle battle = new Battle();
    
    public PlayerMovement(int x, int y, Player player, EnemyStack enemyStack){
    	super(enemyStack);
    	playerX = x;
    	playerY = y;
    	this.player = player;
    }
    
    public void movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;


        if (isValidCoordinate(newX, newY)) {
            playerX = newX;
            playerY = newY;
            System.out.println("You moved to (" + playerX + ", " + playerY + ").");
            player.manaRegen();
            System.out.println(getWorld()[playerX][playerY].getDescription());
        } else {
            System.out.println("You cannot move in that direction.");
        }
    }
    
    public void setCoord(int x, int y) {
    	playerX=x;
    	playerY=y;
    }
    
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < getWorld().length && y >= 0 && y < getWorld()[0].length;
    }
    
    public void checkEncounter() {
    	
    	@SuppressWarnings("unchecked")
		Room<T> room = getWorld()[playerX][playerY];
        if (room != null && room.getEnemy() != null) {
            T enemy = room.getEnemy();
            if(enemy.getHealth()>0)
            {
            	System.out.println("You have encountered an enemy: " + enemy.getName());
                battle.battle(player, enemy);
            }
        }
    }
    
    public int getPlayerX() {
    	return playerX;
    }
    
    public int getPlayerY() {
    	return playerY;
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    }
}
