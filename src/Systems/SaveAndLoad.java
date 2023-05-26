package Systems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import CharacterSkeletons.Player;
import World.Room;

public class SaveAndLoad {
	private int playerX;
	private int playerY;
	private Player player;
	private Room[][] world;
	
	public SaveAndLoad(Player player){
		this.player = player;
	};
	
	SaveAndLoad(int playerX, int playerY, Player player, Room[][] world){
		this.playerX = playerX;
		this.playerY = playerY;
		this.player = player;	
		this.world = world;
		
	}
	
    public void saveGame() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
//        	Writing player's current position and current hp, mana attack and defence
        	
            outputStream.writeObject(playerX);
            outputStream.writeObject(playerY);
            outputStream.writeObject(player);
            outputStream.writeObject(player.getHealth());
            outputStream.writeObject(player.getMana());
            outputStream.writeObject(player.getAttack());
            outputStream.writeObject(player.getDefence());
            
        } catch (IOException e) {
            System.out.println("Failed to save the game.");
        }
    }
    
    public Loader loadGame() {
    	Loader loader = new Loader();
    	
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
//        	Reading saved player position and current hp, mana, attack and defence
            playerX = (int) inputStream.readObject();
            playerY = (int) inputStream.readObject();
            Player player = (Player) inputStream.readObject();
            double hp = (double) inputStream.readObject();
            double mana = (double) inputStream.readObject();
            double atk = (double) inputStream.readObject();
            double def = (double) inputStream.readObject();
            
            player.setHealth(hp);
            player.setMana(mana);
            player.setAttack(atk);
            player.setDefence(def);
            
            loader = new Loader(playerX, playerY, player);
            return loader;
            
        } catch (IOException | ClassNotFoundException e) { //In case of load file not existing
            System.out.println("Failed to load the game."+e);
        }
        return loader;
    }
}
