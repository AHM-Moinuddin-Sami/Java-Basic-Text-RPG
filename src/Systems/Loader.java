package Systems;

import CharacterSkeletons.Player;

public class Loader {
	private int x;
	private int y;
	private Player player;
	
	Loader(){};
	Loader(int x, int y, Player player){
		this.setX(x);
		this.setY(y);
		this.setPlayer(player);
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
