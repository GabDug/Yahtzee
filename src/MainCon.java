import java.util.Scanner;
import java.lang.Math; 

public class MainCon {
    public static void main(String [] args) {
    	System.out.println("Welcome, let's start");
		String i = new String();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1 - Single player\n2 - Multiplayer\n3 - Exit\n");
			
			i = sc.nextLine();
				switch(i) {
					case "1":
						GameEngine game = new GameEngine();
						break;

						
					case "2":
						System.out.println("is comming like the winter ");
						break;
						
					case "3":
					   System.exit(0);
					   break;
					default:
						
						System.out.println("Choose 1, 2 or 3");
						break;
				
				}
			}while(i!="1" || i!="2");
		sc.close();

    }
    
}