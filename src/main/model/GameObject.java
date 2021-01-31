package model;

public class GameObject {
    private int posX;
    private int posY;
    private char character;

//    public GameObject(int posX, int posY, char character) {
//        this.posX = posX;
//        this.posY = posY;
//        this.character = character;
//    }

    // getters
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getCharacter() {
        return character;
    }

    //setters
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
