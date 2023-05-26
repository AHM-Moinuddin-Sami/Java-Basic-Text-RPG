package Systems;

import CharacterSkeletons.Enemy;

public class EnemyStack {
    private Enemy[] stack;
    private int tos;
    private int length;

    public EnemyStack(int capacity) {
        stack = new Enemy[capacity];
        tos = -1;
        length = 0;
    }

    public boolean isEmpty() {
        return tos == -1;
    }

    public boolean isFull() {
        return tos == stack.length - 1;
    }

    public int getLength() {
        return length;
    }

    public Enemy[] getStack() {
        return stack;
    }

    public void push(Enemy enemy) {
        if (isFull()) {
            Enemy[] newStack = new Enemy[stack.length * 2]; //Doubling the previous size
            
            for (int i = 0; i < stack.length; i++) {
				newStack[i] = stack[i];
			}

            stack = newStack;
        }

        stack[++tos] = enemy;
        length++;
    }

    public Enemy pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No monsters to pop.");
            return null;
        }

        Enemy poppedEnemy = stack[tos--];
        length--;
        return poppedEnemy;
    }

    public Enemy getStckElement(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Invalid index. No monster found.");
            return null;
        }

        return stack[index];
    }

    public void setStckElement(int index, Enemy enemy) {
        if (index < 0 || index >= length) {
            System.out.println("Invalid index. Cannot set monster.");
            return;
        }

        stack[index] = enemy;
    }

    public void remove(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Invalid index. No monster found to remove.");
            return;
        }

        for (int i = index; i < length - 1; i++) {
            stack[i] = stack[i + 1];
        }

        stack[length - 1] = null;
        tos--;
        length--;
    }

    public void deployEnemy(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Invalid index. No monster found to deploy.");
            return;
        }

        for (int i = index; i < length - 1; i++) {
            stack[i] = stack[i + 1];
        }

        stack[length - 1] = null;
        tos--;
        length--;
    }
}
