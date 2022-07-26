/*
* Written by Brady Hebert for Genspark class project 2.
* July 26, 2022
* This program is a game. The program generates a random number between 1 and 20 and allows
* the user to guess numbers in an attempt to match it. If the player is successful in 6 guesses
* he wins, otherwise he loses. Regardless of win or lose, after the game is over the program will
* allow the user to choose between playing again and quitting.
* */
import java.util.*;
public class GuessTheNumber {
    public static void main(String[] args) {

        String name; //string for players name
        Scanner in = new Scanner(System.in); //main scanner input

        System.out.println("Hello! What is your name? ");
        name = in.nextLine();


        play(name, in); //call method to start game
    }

    //this method contains the entire play loop
    static void play(String name, Scanner in){
        Random rand = new Random();
        int num = rand.nextInt(21);//generate our random number
        int tries = 0;//initialize the players number of attempts
        int guess;//int variable to hold the players guess
        System.out.println("Well, " + name + ", I am thinking of a number between 1 and 20. \n Take a guess.");

        while (tries < 6){//game will loop until tries = 6
            guess = in.nextInt();//catch the players guess
            tries++;//increment the tries variable

            if (guess == num){//win condition
                System.out.println("Good Job, " + name + "! You guessed my number in " + tries + " guesses!");
                System.out.println("Would you like to play again? (y or n)");
                char playAgain = in.next().charAt(0);
                if (playAgain == 'y'){//if player responds with char y then we call the play method again to restart the game
                    play(name, in);
                } else {
                    System.exit(0);//otherwise we exit
                }
            } else if (guess > num){//direct the player in the correct direction
                System.out.println("Your guess is too high.\n Take a guess.");
            } else {
                System.out.println("Your guess is too low. \n Take a guess.");
            }
        }
            System.out.println("Sorry, you didn't manage to guess the number in 6 guesses. \n Would you like to play again? (y or n)"); //lose condition
            char playAgain = in.next().charAt(0);
            if (playAgain == 'y'){//like above, we give the player a chance to restart
                play(name, in);
            } else {
                System.exit(0);
            }

        }

    }

