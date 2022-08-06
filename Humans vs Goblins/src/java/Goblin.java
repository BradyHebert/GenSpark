import java.util.Random;

public class Goblin extends Entity {

    //private int sight;

    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_RESET = "\u001B[0m";

    public Goblin(){

    }

    public Goblin(int HP, int STR, Position POS, Position OLD){

        this.HP = HP;
        this.STR = STR;
        this.POS = POS;
        this.AC = 10;
        //this.sight = 4;
        this.ARMOR = "rags";
        this.WEAPON = "dagger";
        this.WEAPONDAMAGE = 4;
        this.ALIVE = true;
        this.OLD = OLD;

        this.attackable = true;
    }
    @Override
    public String toString(){
        return ANSI_GREEN + "Goblin" + ANSI_RESET;
    }

    @Override
    public String toMap(){
        return ANSI_GREEN + " G " + ANSI_RESET;
    }

    @Override
    public void bark() {

        System.out.print(this.toString() + " says ");
        Random rand = new Random();
        int roll = rand.nextInt(4);
        switch (roll){
            case 0:
                System.out.println("\"Augh! Don't hurt me!\"");
                break;
            case 1:
                System.out.println("\"Give gold! Me stab!\"");
                break;
            case 2:
                System.out.println("\"Wazzat?\"");
                break;
            case 3:
                System.out.println("\"No run! Me kill!\"");
                break;
        }
    }


    /*public int getSight() {
        return sight;
    }*/



}
