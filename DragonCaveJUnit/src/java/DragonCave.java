
import java.util.Random;
import java.util.Scanner;
/*
 * Written by Brady Hebert for Genspark class project 4.
 * July 26, 2022
 * This program is a game. The program randomly assigns winning status to one of two caves. The game prompts the user to
 * choose one of the caves. The user either wins or loses, after which the game exits.
 * This version implements try/catch blocks around user input and JUnit tests
 * */
public class DragonCave {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);//init scanner
        System.out.println("You are in a land full of dragons. In front of you, you see two caves.\nIn one cave, the dragon is friendly and will share his treasure with you. The other dragon is greedy and hungry, and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)"); //prompt user
        int choice = 0; //init choice
        try { //catch user input in try/catch block
            choice = in.nextInt();
            if (choice != 1 && choice != 2){
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid choice.");
            System.exit(0);
        }
        Cave cave = new Cave();
        System.out.println("You approach the cave...\nIt is dark and spooky\nA large dragon jumps out in front of you! He opens his jaws and...");
        cave.rollCave(choice);
        System.exit(0);//exit
    }
}


