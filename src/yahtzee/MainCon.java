package yahtzee;

import java.util.Scanner;

public class MainCon {
    public static void main(String[] args) {
        System.out.println("Welcome to Yahtzee, let's start");
        String i;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1 - Single player\n2 - Multiplayer\n3 - Exit\n");
            i = sc.nextLine();
            switch (i) {
                case "1":
                    GameEngine game = new GameEngine();
                    break;

                case "2":
                    System.out.println("Multiplayer is coming (like the winter)");
                    break;

                case "3":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose 1, 2 or 3");
                    break;

            }
        } while (true);
    }

}