import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<String> wordList = new ArrayList<>();
    public static void main(String[] args) {
        int misses=0;
        int score=0;
        ArrayList<Character> missedLetters = new ArrayList<>();
        ArrayList<Character> revealedLetters = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        generateList();
        init(missedLetters, revealedLetters, in, score, misses);




    }
    public static void generateList(){
        try {
            BufferedReader in = new BufferedReader(new FileReader("words.txt"));
            while(in.ready()){
                wordList.add(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }
    public static ArrayList<Character> getWord(ArrayList<String> list){
        Random rand = new Random();
        int n = rand.nextInt(list.size());
        String word = list.get(n);
        int len = word.length();
        ArrayList<Character> wordArray = new ArrayList<>();
        for(int i =0; i < len; i++){
            wordArray.add(word.charAt(i));
        }
        return wordArray;
    }
    public static void play(ArrayList<Character> missedLetters, ArrayList<Character> revealedLetters, ArrayList<Character> word, Scanner in, int score, int misses){
        drawBoard(misses);
        System.out.println("Missed letters: " + missedLetters);
        for(int i = 0; i < revealedLetters.size(); i++){
            System.out.print(revealedLetters.get(i)+" ");
        }
        System.out.println("Guess a letter.");
        try{
            char guess = in.next().charAt(0);
            if (missedLetters.contains(guess)||revealedLetters.contains(guess)){
                System.out.println("You already guessed that letter. Choose again.");
                play(missedLetters, revealedLetters, word, in, score, misses);
            } else if (!Character.isAlphabetic(guess)){
                System.out.println("Letters only. Choose again.");
                play(missedLetters, revealedLetters, word, in, score, misses);
            } else {
                if (word.contains(guess)){
                    for (int i = 0; i < revealedLetters.size(); i++){
                        if (word.get(i).equals(guess)){
                            revealedLetters.set(i, guess);
                            score--;
                            //System.out.println("score: " + score);
                        }
                    }
                } else {
                    missedLetters.add(guess);
                    misses++;
                }
            }
        } catch (Exception e) {
            //something
        }
        if (score == 0 || misses == 6){
            String ans = "";
            for (int i = 0; i < word.size(); i++)
                ans += word.get(i);
            if (score == 0){
                System.out.println("Yes! The secret word is " + ans + "! You have won! \n Do you want to play again? (yes or no)");
                try{
                    if (in.next().charAt(0)=='y'){
                        init(missedLetters, revealedLetters, in, score, misses);
                    } else {
                        System.exit(0);
                    }
                } catch (Exception e){
                    //something
                }
            } else if (misses == 6){
                drawBoard(misses);
                System.out.println("You Lose! The secret word was " + ans + ".\n Do you want to play again? (yes or no)");
                try{
                    if (in.next().charAt(0)=='y'){
                        init(missedLetters, revealedLetters, in, score, misses);
                    } else {
                        System.exit(0);
                    }
                } catch (Exception e){
                    //something
                }
            }
            } else {

            play(missedLetters, revealedLetters, word, in, score, misses);
        }

    }
    public static void drawBoard(int misses){
        switch(misses){
            case 0:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("       =====");
                break;
            case 1:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O       |");
                System.out.println("         |");
                System.out.println("         |");
                System.out.println("       =====");
                break;
            case 2:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O       |");
                System.out.println(" |       |");
                System.out.println("         |");
                System.out.println("       =====");
                break;
            case 3:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O       |");
                System.out.println("/|       |");
                System.out.println("         |");
                System.out.println("       =====");
                break;
            case 4:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O/      |");
                System.out.println("/|       |");
                System.out.println("         |");
                System.out.println("       =====");
                break;
            case 5:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O/      |");
                System.out.println("/|       |");
                System.out.println("/        |");
                System.out.println("       =====");
                break;
            case 6:
                System.out.println("H A N G M A N");
                System.out.println(" + - - - +");
                System.out.println(" O/      |");
                System.out.println("/|       |");
                System.out.println("/|       |");
                System.out.println("       =====");
                break;

        }

    }
    public static void init(ArrayList<Character> missedLetters, ArrayList<Character> revealedLetters, Scanner in, int score, int misses){
        missedLetters.clear();
        revealedLetters.clear();


        ArrayList<Character> word = getWord(wordList);
        for(int i : word){
            revealedLetters.add('_');
        }
        misses = 0;
        score = word.size();
        play(missedLetters, revealedLetters, word, in, score, misses);
    }


}


