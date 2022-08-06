import java.util.Random;

import static java.lang.Thread.sleep;

public class Treasure extends Entity {
    private Position pos;
    private int gold = 0;
    private boolean armor = false;
    private boolean weapon = false;
    private boolean potion = false;
    private boolean heal = false;
    Random rand = new Random();
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_PURPLE = "\u001B[35m";
    private String ANSI_YELLOW = "\u001B[33m";

    Treasure(Position pos){
        this.pos = pos;
        gold = 0;
        armor = false;
        weapon = false;
        potion = false;
        this.attackable = true;
    }

    public Position getPOS(){
        return pos;
    }

    public void pause()  {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void generate(Human human){
        //roll 100

        int outcome = rand.nextInt(100)+1;
        if (outcome > 0) gold();
        if (outcome > 20) gold();
        if (outcome > 40) heal();
        if (outcome > 60) potion();
        if (outcome > 80) gear();
        if (outcome > 90) gold();

        System.out.print("You have found a treasure chest! Inside you find ");
        System.out.print(ANSI_YELLOW + gold + ANSI_RESET);
        System.out.println(" gold pieces.");
        human.setGold(human.getGold()+gold);
        pause();
        if(potion){
            System.out.println("You also find a flask of greater constitution! The flask is metal and bears dwarven runes upon it.");
            pause();
            System.out.println("The liquid inside smells of strong spirits, and the taste is strong and warming.");
            pause();
            System.out.println("Your maximum health has increased!");
            human.setMaxHP(human.getMaxHP()+5);
            human.setHP(human.getHP() + 5);

            pause();
        }
        if(heal){
            System.out.println("You also find a small healing potion! The liquid inside this vial is thick and the color of roses, streaked throughout with golden dust.");
            pause();
            System.out.println("The potion smells of flowers and honey, and is sickeningly sweet upon the tongue.");
            pause();
            System.out.println("Your aches and pains fade away.");
            if (human.getHP() > human.getMaxHP() / 2)
                human.setHP(human.getMaxHP());
            else
                human.setHP(human.getHP()+(human.getMaxHP()/2));
            pause();
        }
        if (armor){
            System.out.print("You also find new armor inside the chest! ");
            if (human.getAC()==10){
                System.out.println("This armor is made from tough but flexible leather, and is reinforced with close-set rivets.");
                human.setAC(12);
                human.setARMOR("studded leather armor");
            } //studded leather = ac12
            else if (human.getAC()==12){
                System.out.println("This armor consists of a coat and leggings of leather covered with overlapping pieces of metal, much like the scales of a fish.");
                human.setAC(14);
                human.setARMOR("scale mail armor");
            }//scale mail = ac14
            else if (human.getAC()==14){
                System.out.println("This chain mail is made of interlocking metal rings, and includes a layer of quilted fabric worn underneath the mail to prevent chafing and to cushion the impact of blows.");
                human.setAC(16);
                human.setARMOR("chain mail armor");
            }//chain mail = ac16
            else {
                System.out.println("However, your armor is better.");
            }//armor but yours is better
            pause();
        }
        if (weapon) {
            System.out.print("Lucky day! You find a new weapon inside the chest! ");
            if (human.WEAPONDAMAGE==6){
                System.out.println("This longsword is keen and versatile.");
                human.setDAMAGE(8);
                human.setWEAPON("longsword");
            } //longsword = 8
            else if (human.WEAPONDAMAGE==8){
                System.out.println("This battleaxe is perfect for cleaving through hordes of goblins.");
                human.setDAMAGE(10);
                human.setWEAPON("battleaxe");
            }//battleaxe = 10
            else if (human.WEAPONDAMAGE==10){
                System.out.println("This warhammer will crush the goblins and their pathetic armor.");
                human.setDAMAGE(12);
                human.setWEAPON("warhammer");
            }//warhammer = 12
            else {
                System.out.println("However, your weapon is superior.");
            }//weapons, but yours is better
            pause();
        }

        }

    private void heal() {
        heal = true;
    }

    private void potion() {
        potion = true;
    }

    private void weapon() {
        weapon = true;
    }

    private void armor() {
        armor = true;
    }

    private void gear(){
        int outcome = rand.nextInt(20)+1;
        if (outcome >= 20){
            weapon();
            armor();
        } else if (outcome > 10){
            weapon();
        } else {
            armor();
        }
    }

    private void gold() {
        gold += rand.nextInt(10)+1;
    }


    @Override
    public String toString(){
        return ANSI_YELLOW + "Treasure" + ANSI_RESET;
    }

    @Override
    public String toMap(){
        return ANSI_YELLOW + " T " + ANSI_RESET;
    }




}
