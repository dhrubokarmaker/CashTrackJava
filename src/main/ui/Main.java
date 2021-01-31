package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen();
        Boundary boundary = new Boundary('#');
        screen.makeEmptyScreen();
        boundary.addUpperLowerRows(screen);
        boundary.addLeftRightColumns(screen);

        screen.printGameScreen();
        Snake snake = new Snake(5,6,'$');
        screen.addObjectToScreen(snake);
        System.out.println("new");
        screen.printGameScreen();
    }
}
