import java.util.Random;

public class Cave {


    public void rollCave(int choice){
        Random rand = new Random();//generate winning cave
        int treasureCave = rand.nextInt(2);

        if (choice == treasureCave){//compare player choice to winning cave, then deliver result to player
            System.out.println("Hands you the treasure!");
        } else {
            System.out.println("Gobbles you down in one bite!");
        }
    }
}
