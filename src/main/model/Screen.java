package model;

public class Screen {
    // Game screen
    public static final int SCREEN_WIDTH = 20;
    public static final int SCREEN_HEIGHT = 10;
    private char[][] screenMatrix;

    // Screen constructor
    public Screen() {
        screenMatrix = new char[SCREEN_WIDTH][SCREEN_HEIGHT];
    }

    // MODIFIES: this
    // EFFECTS: Makes empty game screen matrix
    public void makeEmptyScreen() {
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                screenMatrix[j][i] = '.';
            }
        }
    }


    // EFFECTS : Prints game screen
    public void printGameScreen() {
        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                System.out.print(screenMatrix[j][i]);
            }
            System.out.println();
        }

    }

    // REQUIRES: Object must be within the screenMatrix boundary
    // MODIFIES: this
    // EFFECTS : Adds Object to screenMatrix
//    public void addObjectToScreen() {
//
//    }


}
