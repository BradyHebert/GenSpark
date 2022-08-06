public class Human extends Entity {
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLUE = "\u001B[34m";
    private int gold;
    private int maxHP;

    public Human(){

    }
    public Human(int HP, int STR, Position POS){
        this.HP = HP;
        this.maxHP = HP;
        this.STR = STR;
        this.POS = POS;
        this.AC = 10;
        this.ARMOR = "cloth armor";
        this.WEAPON = "short sword";
        this.WEAPONDAMAGE = 6;
        this.gold = 0;
        this.ALIVE = true;
        this.isPlayer = true;
        this.attackable = true;
        this.collision = false;
    }

    @Override
    public String toString(){
        return ANSI_BLUE+ "Human" + ANSI_RESET;
    }
    @Override
    public String toMap(){
        return ANSI_BLUE+ " H " + ANSI_RESET;
    }
    @Override
    public void bark(){
        System.out.println("Have at you, fiend!");
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }


}
