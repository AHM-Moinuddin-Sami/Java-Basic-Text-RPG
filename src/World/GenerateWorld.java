package World;

import java.util.Random;
import CharacterSkeletons.Enemy;
import Systems.EnemyStack;

public class GenerateWorld<T extends Enemy> {
	

	private Room<T>[][] world;
    
    public GenerateWorld(EnemyStack enemyStack){
    	world = new Room[2][9];
    	createWorld();
    	createEnemies(enemyStack);
    }
    
    private void createWorld() {
        // Create rooms and connect them
    	
    	for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				Room<Enemy> room = new Room<>("Room "+i+"x"+j,"You are in Room "+i+"x"+j+".");
				world[i][j] = (Room<T>) room;
			}
		}
    }
    
    private void createEnemies(EnemyStack enemyStack) {
        Random random = new Random();

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
            	if(i == 1 && j == 1)
            		continue;
                if (enemyStack.getLength() > 0) {
                    int index = random.nextInt(enemyStack.getLength());
                    Enemy enemy = enemyStack.getStckElement(index);
                    world[i][j].setEnemy((T) enemy);
                    enemyStack.remove(index);
                }
            }
        }
    }
    
    public Room[][] getWorld() {
    	return world;
    }
    
    public void setWorld(Room[][] world) {
    	this.world = world;
    }

}
