package World;

import java.io.Serializable;

import CharacterSkeletons.Enemy;

public class Room<T extends Enemy> implements Serializable {
    private String name;
    private String description;
    private T enemy;
//    private Map<String, Room> exits;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
//        exits = new HashMap<>();
    }
    
    public T getEnemy() {
        return enemy;
    }

    public void setEnemy(T enemy) {
        this.enemy = enemy;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}