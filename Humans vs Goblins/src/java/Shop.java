import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Shop extends Entity{
    Human customer;
    Scanner in;
    int gold = 0;
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_RESET = "\u001B[0m";
    public Shop(Position pos){
        this.POS = pos;
        this.attackable = true;

    }
    public void generateShop(Human human, Scanner in) {
        this.customer = human;
        this.in = in;
        this.gold = customer.getGold();
        System.out.println("The shrew-like man behind the counter scowls as you enter the dingy shop. ");
        pause();
        System.out.println("\"I am a seller of powerful potions. I sell only the strongest potions meant for the strongest beings.\"");
        pause();
        System.out.println("You slide two fingers into your coin purse and begin to count the coins there. You have " + gold + " gold coins.");
        pause();
        System.out.println("The man gives you a wicked grin as you count your coins. In a voice that isn't quite laughter he asks \"Would you like to see my potions?\" (y/n)");
        char input = getInput();
        switch (input){
            case 'y' :
                wares();
                break;
            case 'n' :
                break;
        }
    }

    public void pause()  {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void wares() {
        pause();
        System.out.println("\"I sell only one brew traveller, the Potion of Monstrous Strength.\"");
        pause();
        System.out.println("His eyes crawl down and up your body, and his scowl deepens. \"This potion is too strong for any but the strongest. This potion is fit for a beast, let alone a man.\"");
        pause();
        System.out.println("After a silent pause full of accusation, the potion seller sighs and looks down at the counter. \"I will sell you this brew, though it may kill you. The price is 100 gold pieces.");
        pause();
        System.out.println("If your purse is heavy and constitution hardy, This potion will grant you the strength of monsters. Are you willing? (y/n)");
        char input = getInput();
            if (input == 'y'){
                if (gold >= 100){
                    //potion given, str increased
                    System.out.println("As your hard won gold falls onto the wooden counter, the potion seller retrieves an iron lockbox from beneath the counter.");
                    pause();
                    System.out.println("From within the box emerges a vial of clear glass. It is smaller than you thought it would be.");
                    pause();
                    System.out.println("Dark liquid the color of strong coffee sits within. You give it a swirl and can see streaks of gold and silver within.");
                    pause();
                    System.out.println("The cork stopper sits snug in the mouth of vial, desperately trying to contain the brew within despite your efforts.");
                    pause();
                    System.out.println("As the stopper falls away, a stench of decay and wet fur fills your mouth and nose. The miasma is suffocating and disgusting, but the urge to drink the liquid within only strengthens in you.");
                    pause();
                    System.out.println("Without hesitation, you bring the vial to your lips and upend the draught into your mouth. The potion burns like stiff drink on its way down your throat.");
                    pause();
                    if (customer.getHP() > 30){
                        System.out.println("Not a moment after the potion settles in your gut, a sensation of fire erupts within and travels down every finger and toe. " +
                                "Not once, but over and over, like the crashing of surf upon stone cliffs.");
                        pause();
                        System.out.println("Your entire body is in chaos. Every muscle of your arms and legs flex and cramp with no regard for your wishes.");
                        pause();
                        System.out.println("Your mind is euphoric, and as the spasms pass you fell stronger than you ever have before.");
                        pause();
                        System.out.println("You turn to thank the potion seller, but he is gone. The hut is empty and the sun has set.");
                        customer.setGold(customer.getGold()-100);
                        customer.setSTR(customer.getSTR()*2);
                        customer.setMaxHP(customer.getMaxHP()*2);
                        customer.setHP(customer.getMaxHP());
                    } else {
                        System.out.println("Immediately,you realize this was folly. You were not ready, and now your overconfidence has ended you.");
                        pause();
                        System.out.println("Bile flees your stomach like wind before the storm. Maybe if you could purge this foul contagion, but no. It is too late.");
                        pause();
                        System.out.println("Your thought slow, as your muscle seize. Legs that you can no longer control betray you, and you fall to the dirty bare floorboards of the shop.");
                        pause();
                        System.out.println("You desperately try to pull air into your lungs, but your mouth is full of vomit. Now you can see the potion seller, looking down upon your body with clasped hands.");
                        pause();
                        System.out.println("\"Very disappointing.\" The only words he mutters as he steps over you and vanishes into a back room.");
                        pause();
                        System.out.println("As you die, you are certain that this corrupted body will live on after. Already, you can feel the murderous bloodlust behind its eyes and the bottomless hunger in its gut.");
                        System.exit(0);

                    }
                } else {
                    System.out.println("As you begin to lay coin out onto the counter, it becomes obvious you do not have enough to cover the price.");
                    pause();
                    System.out.println("The potion seller's expression turns to mirth and barely contained laughter as he walks you out of the shop.");
                    pause();
                    System.out.println("Your attempts to negotiate the price down fall upon deaf ears, and as you stand outside the door he hands you a small bottle of thick red liquid.");
                    pause();
                    System.out.println("\"This is a good thing, truly. My other potion most likely would have killed you. Drink this, it will make you feel better.\" " +
                            "The potion seller turns and walks back into the small shop, muttering to himself under his breath.");
                    pause();
                    System.out.println("As you finish the drink he gave you, you discover the seller was right. Your aches and pains melt away as your breath comes easier and the day seems brighter.");
                    pause();
                    System.out.println("As you walk away, the only pain you still suffer is the ache of regret and shame.");
                    customer.setHP(customer.getMaxHP());
                }
        } else {
                System.out.println("\"Truly, that is wise.\" The potion seller sighs as he begins to walk you to the door.");
                pause();
                System.out.println("\"I did not exaggerate. The potion might have killed you, if you had been foolhardy enough to drink it.\"");
                pause();
                System.out.println("\"It is disappointing for me, that is true. My greatest wish is to meet someone as strong as my potions, but that is not your problem\"");
                pause();
                System.out.println("The potion seller places a small red vial into your hands as you leave the shop. \"Take this as thanks for visiting my shop. May it bring you good health and fortune on your quest\"");
                pause();
                System.out.println("The potion seller walks back into the shop as you drink the gifted potion. You feel your aches and pains melt away as you finish the brew.");
                pause();
                System.out.println("You step into the shop to thank the potion seller for the gift, but the think layer of dust on  the floor and cobwebs in the corners of the room make it clear that this room has long been abandoned.");
                pause();
                System.out.println("As you gap in astonishment at the sudden change, you notice there is only one trail of footprints in that heavy layer of dust.");
                customer.setHP(customer.getMaxHP());
            }

    }

    private Character getInput(){
        char input = ' ';
        try {
            input = in.next().toLowerCase().charAt(0);
        } catch (Exception e) {

        }
        if (!Character.toString(input).matches("^[yn]$")) {
            System.out.println("Invalid input.");
            getInput();
        }
        return input;
    }
    public String toString(){
        return "Shop";
    }

    public String toMap(){
        return ANSI_CYAN + " S " + ANSI_RESET;
    }


}
