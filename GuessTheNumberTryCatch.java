package genspark.assignments.section3;/*
 * Written by Brady Hebert for Genspark class project 3.
 * July 26, 2022
 * This program is a game. The program generates a random number between 1 and 20 and allows
 * the user to guess numbers in an attempt to match it. If the player is successful in 6 guesses
 * he wins, otherwise he loses. Regardless of win or lose, after the game is over the program will
 * allow the user to choose between playing again and quitting.
 * this version implements try/catch blocks around user input.
 * */
import java.util.*;
public class GuessTheNumberTryCatch {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in); //main scanner input

        System.out.println("Hello! What is your name? ");
        String name = null;
        try {
            name = in.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid.");
            in.next();
        }


        play(name, in); //call method to start game
    }

    //this method contains the entire play loop
    static void play(String name, Scanner in){
        Random rand = new Random();
        int num = rand.nextInt(21);//generate our random number
        int tries = 0;//initialize the players number of attempts

        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20. \n Take a guess.");

        while (tries < 6){//game will loop until tries = 6
            int guess = 0;
            try {
                guess = in.nextInt();//catch the players guess

            } catch (InputMismatchException e) {
                System.out.println("Integers only.");
                in.next();
            }
            tries++;//increment the tries variable

            if (guess == num){//win condition
                System.out.println("Good Job, " + name + "! You guessed my number in " + tries + " guesses!");
                gameOver(name, in);
            } else if (guess < 1 || guess > 20){
                System.out.println("Pick a number between 1 and 20.");
            }else if (guess > num){//direct the player in the correct direction
                System.out.println("Your guess is too high.\n Take a guess.");
            } else {
                System.out.println("Your guess is too low. \n Take a guess.");
            }
        }
        System.out.println("Sorry, you didn't manage to guess the number in 6 guesses. \n Would you like to play again? (y or n)"); //lose condition
        gameOver(name, in);

    }

    static void gameOver(String name, Scanner in){
        System.out.println("Would you like to play again? (y or n)");
        try{
            char ans = in.next().charAt(0);
            ans = Character.toLowerCase(ans);
            if (ans=='y'){
                play(name, in);
            } else if (ans=='n'){
                System.exit(0);
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid answer.");
            in.next();
        }
    }

}

