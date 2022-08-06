import java.util.Random;

public class Cave extends Entity {
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    public Cave(int HP, int STR, Position POS){
        this.HP = HP;
        this.STR = STR;
        this.POS = POS;
        this.AC = 15;

        this.ARMOR = "Scrap plate";
        this.WEAPON = "savage club";
        this.WEAPONDAMAGE = 8;
        this.ALIVE = true;
        this.collision = true;
        this.attackable = true;
    }
    public void bark(){

        System.out.print(this.toString() + " says ");
        Random rand = new Random();
        int roll = rand.nextInt(4);
        switch (roll){
            case 0:
                System.out.println("\"Wazzat? Yoos comin ta meh? 'Stead o scurryin away, yoos comin strait ta meh?\"");
                break;
            case 1:
                System.out.println("\"Meh gon crush ya, then meh gon slice ya, then meh gon ate ya!\"");
                break;
            case 2:
                System.out.println("\"Wazzat stink? Stink o dem hummies. Lit da fire bois. Meh es ateing hummie dis nite!\"");
                break;
            case 3:
                System.out.println("\"Yoos been de one bashin all dem gobbies? Good. Dey weak n needed bashin. Just like yoos!\"");
                break;
        }
    }
@Override
    public String toString(){
        return ANSI_GREEN + "Goblin King" + ANSI_RESET;
    }
    @Override
    public String toMap(){
        return ANSI_RED + " C " + ANSI_RESET;
    }
}
