import java.util.*;

import static java.lang.Thread.sleep;

public class Game {
    Map map;
    Human human;
    //Goblin goblin;
    Treasure treasure;
    Cave cave;
    Shop shop;
    ArrayList<Goblin> goblins = new ArrayList<>();
    ArrayList<Treasure> treasures = new ArrayList<>();

    ArrayList<Obstacle> obstacles = new ArrayList<>();
    int mapSize = 20;
    Random rand = new Random();
    Scanner in = new Scanner(System.in);
    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_PURPLE = "\u001B[35m";
    String ANSI_YELLOW = "\u001B[33m";



    public Game(){
        init();

    }
    public void init(){
        map = new Map(mapSize, mapSize);
        int num = 30 + roll(30);

        for (int i = 0; i < num; i++){
            Obstacle obstacle = new Obstacle(getNewSpawn());
            map.setPOS(obstacle.getPOS(),obstacle);
            obstacles.add(obstacle);

        }
        treasure = new Treasure(getNewSpawn());
        shop = new Shop(getNewSpawn());
        cave = new Cave(25, 3, getNewSpawn());
        Goblin goblin = new Goblin(10, 1, getNewGoblin(), cave.getPOS());

        human = new Human(20, 3, getNewSpawn());
        treasures.add(treasure);
        goblins.add(goblin);
        map.setPOS(human.getPOS(), human);
        map.setPOS(cave.getPOS(), cave);
        map.setPOS(treasure.getPOS(), treasure);
        map.setPOS(shop.getPOS(), shop);
        map.setPOS(goblin.getPOS(), goblin);
        System.out.println("Slay Goblins and collect treasure. When you are strong enough, enter the cave and slay the Goblin King.");
        System.out.println("You have " + human.getGold() + " gold pieces and a map to nearby treasure. You wield a " + human.getWEAPON() + " and are clad in " + human.getARMOR() + ".");
        System.out.println("What will you do? Move by entering n for north, s for south, e for east, or w for west. Enter i for inventory or c for character stats.");



        try{
            play();
        } catch (Exception e){

        }
    }
    public void play() {
        if (goblins.isEmpty()){
            Goblin goblin = new Goblin(10, 1, getNewGoblin(), cave.getPOS());
            goblins.add(goblin);
            map.setPOS(goblin.getPOS(), goblin);
            System.out.println("A new goblin emerges from the cave!");
        }else if (roll(20) > 19){
            Goblin goblin = new Goblin(10, 1, getNewGoblin(), cave.getPOS());
            goblins.add(goblin);
            map.setPOS(goblin.getPOS(), goblin);
            System.out.println("A new goblin emerges from the cave!");
        }

        System.out.print(map.toString());
        System.out.println("What now?");
        char input = ' ';
        try {
            input = in.next().toLowerCase().charAt(0);
        } catch (Exception e) {

        }
        if (!Character.toString(input).matches("^[nsewic]$")){
            System.out.println("Invalid input.");
            play();
        } else if (input == 'i'){
            System.out.println("You have " + human.getGold() + " gold pieces. You wield a " + human.getWEAPON() + " and are clad in " + human.getARMOR() + ".");
            play();
        } else if (input == 'c'){
            System.out.println("Health: " + human.getHP() + "/" + human.getMaxHP() + " . Strength:  " + human.getSTR() + ".");
            play();
        }else {
            move(human, input);
            for(int i = 0; i < goblins.size(); i++) {
                checkTouching(human, goblins.get(i));
            }
            for (int i = 0; i < treasures.size(); i++) {
                checkTouching(human, treasures.get(i));
            }
            checkTouching(human, shop);
            checkTouching(human, cave);
        }
        moveGoblins(goblins);

        play();
    }
    private void checkTouching(Entity x, Entity y) {
        if (x.getPOS().equals(y.getPOS())) {
            if ((x == human && goblins.contains(y))||(y == human && goblins.contains(x))) {
                x.bark();
                combat(x, y);
            }
            else if (x == human && treasures.contains(y)){
                treasure = (Treasure) y;
                treasure.generate(human);
                map.kill(y.getPOS(), y);
                kill(y.getPOS(), y);
                treasures.remove(y);
            } else if (x == human && y == shop) {
                shop.generateShop(human, in);
                map.kill(y.getPOS(), y);
                kill(y.getPOS(), y);
            }
            else if (x == human && y == cave){
                System.out.println("The goblins scurry from you as you enter the cave. None bar your way, but shepard you down and down the tunnels until you arrive in the " + cave.toString() + "'s throne room.");
                pause();

                System.out.println("The "+cave.toString() + " is a monstrously large and vicious looking goblin. He stands over twice the height of the other goblins, and when he moves his bulk chases him in heavy waves.");
                pause();
                System.out.println("As the " + cave.toString() + " rises to his feet, a horde of goblins gather in every passageway, sealing you in with this behemoth goblin.");
                cave.bark();
                combat(human, cave);
            }
        }
    }
    private void moveGoblins(ArrayList<Goblin> goblins) {
        for (Goblin goblin : goblins) {
            Position step = map.pathfinderA(goblin.getPOS(), human.getPOS());
            map.updatePOS(step, goblin);
            checkTouching(goblin, human);

        }
    }
    public void pause()  {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void combat(Entity x, Entity y) {
        boolean crit = false;
        String p1 = x.toString();
        String p2 = y.toString();
        pause();
        System.out.print(p1 + " attacks with their " + x.getWEAPON() + ". ");

        int roll = roll(20);
        int xAttack = roll + x.STR;
        if (roll > 19){
            crit = true;
            System.out.print(ANSI_RED + "CRITICAL HIT!!! " + ANSI_RESET);
        }
        System.out.print(p1 + " rolled a " + xAttack +" (" + roll + " + " + x.STR + ")! ");
        pause();
        if (xAttack >= y.getAC()) {
            int xDamage;
            if(crit){
                xDamage = (roll(x.WEAPONDAMAGE) * 2) + x.STR;
            } else {
                xDamage = roll(x.WEAPONDAMAGE) + x.STR;
            }
            y.setHP(y.getHP() - xDamage);
            if (y.getHP() < 1) {
                System.out.print(p1 + " hits! " + p1 + " deals " );
                System.out.print(ANSI_RED + xDamage + ANSI_RESET);
                System.out.print(" to " + p2 + ". " + p2 + " is slain!\n");
                pause();

                if (goblins.contains(y)) {
                    map.kill(y.getPOS(), y);
                    kill(y.getPOS(), y);
                    goblins.remove(y);
                    treasure = new Treasure(getNewSpawn());
                    treasures.add(treasure);
                    map.setPOS(treasure.getPOS(), treasure);
                    loot();


                } else if (y==cave){
                    win();
                } else {
                    gameOver();
                }

                play();
            } else {
                System.out.print(p1 + " hits! " + p1 + " deals ");
                System.out.print(ANSI_RED + xDamage + ANSI_RESET);
                System.out.print(" to " + p2 + ". " + p2 + "'s new health is ");
                System.out.print(ANSI_PURPLE + y.getHP() + ANSI_RESET);
                System.out.println(".");
                combat(y, x);
            }
        } else {
            System.out.println(p1 + " misses!");
            combat(y, x);
        }
    }

    private void win() {
        System.out.println("Congratulations! You have rid the land of the goblin menace. You win!");
        System.exit(0);
    }

    public void gameOver() {
        System.out.println("You have been slain!");
        System.exit(0);
    }

    public void loot()  {
        System.out.print("You loot the goblin's body and find a map to treasure and ");
        int gold = roll(10);
        System.out.print(gold + " gold pieces!\n");
        human.setGold(human.getGold()+gold);
        pause();
        play();
    }

    public int roll(int x){
        return rand.nextInt(x)+1;
    }
    public Position getNewSpawn(){
        Position pos = new Position(rand.nextInt(mapSize-2)+1, rand.nextInt(mapSize-2)+1);
        boolean pass = false;
        if (!map.checkCollision(pos)){
            return pos;
        } else {
            return getNewSpawn();
        }
     }

    public Position getNewGoblin(){
        Position pos = new Position(cave.getPOS().getX() + rand.nextInt(3)-1,cave.getPOS().getY() + rand.nextInt(3)-1, cave.getPOS());
        if (!map.checkCollision(pos)){
            return pos;
        } else {
            return getNewGoblin();
        }

    }

    public void kill(Position pos, Entity x){
        x.kill();
    }

     public void move(Entity x, char input){
        Position pos;
         switch(input){
             case 'n':
                 if (x.getPOS().getY()==0){
                     break;
                 }
                 pos = new Position(x.getPOS().getX(), x.getPOS().getY()-1);
                 map.updatePOS(pos, x);
                 break;
             case 's':
                 if (x.getPOS().getY()==mapSize-1){
                     break;
                 }
                 pos = new Position(x.getPOS().getX(), x.getPOS().getY()+1);
                 map.updatePOS(pos, x);
                 break;
             case 'e':
                 if (x.getPOS().getX()==mapSize-1){
                     break;
                 }
                 pos = new Position(x.getPOS().getX()+1, x.getPOS().getY());
                 map.updatePOS(pos, x);
                 break;
             case 'w':
                 if (x.getPOS().getX()==0){
                     break;
                 }
                 pos = new Position(x.getPOS().getX()-1, x.getPOS().getY());
                 map.updatePOS(pos, x);
                 break;
         }


     }



}
