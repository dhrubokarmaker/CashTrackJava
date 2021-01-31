package model;

public class Boundary extends GameObject {

    // EFFECTS: Adds boundary to screen
    public Boundary(char character) {
        setCharacter(character);
    }

    public void addUpperLowerRows(Screen screen) {
        for (int i = 0; i < screen.getScreenWidth(); i++) {
            setPosX(i);
            setPosY(0);
            screen.addObjectToScreen(this);
        }
        for (int i = 0; i < screen.getScreenWidth(); i++) {
            setPosX(i);
            setPosY(screen.getScreenHeight() - 1);
            screen.addObjectToScreen(this);
        }
    }
    public void addLeftRightColumns(Screen screen) {
        for (int i = 1; i < screen.getScreenHeight(); i++) {
            setPosX(0);
            setPosY(i);
            screen.addObjectToScreen(this);
        }
        for (int i = 1; i < screen.getScreenHeight(); i++) {
            setPosX(screen.getScreenWidth() - 1);
            setPosY(i);
            screen.addObjectToScreen(this);
        }
    }
}
