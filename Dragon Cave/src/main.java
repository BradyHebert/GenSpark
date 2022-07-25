import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        System.out.println("You are in a land full of dragons. In front of you, you see two caves.\nIn one cave, the dragon is friendly and will share his treasure with you. The other dragon is greedy and hungry, and will eat you on sight.");
        System.out.println();
        Scanner reader = new Scanner(System.in);
        System.out.println("Which cave will you go into? (1 or 2)");
        int choice = reader.nextInt();

        while(choice != 1 && choice != 2) {
            System.out.println("Invalid choice.");
            System.out.println();
            System.out.println("Which cave will you go into? (1 or 2)");
            choice = reader.nextInt();
        }
        System.out.println("You approach the cave...\nIt is dark and spooky\nA large dragon jumps out in front of you! He opens his jaws and...");
        Random rand = new Random();
        int treasureCave = rand.nextInt(2);

        if (choice == treasureCave){
            System.out.println("Hands you the treasure!");
        } else {
            System.out.println("Gobbles you down in one bite!");
        }
        System.exit(0);
    }
}
