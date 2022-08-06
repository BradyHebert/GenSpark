public class Entity {
    public Entity(){

    }
    protected Position POS, OLD;
    protected int HP, STR, AC, WEAPONDAMAGE;
    protected String WEAPON, ARMOR;
    protected boolean ALIVE;
    protected boolean isPlayer = false;
    protected boolean collision = true;
    protected boolean attackable = false;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }

    public String getARMOR(){return ARMOR;}

    public void setARMOR(String ARMOR){this.ARMOR = ARMOR;}
    public String getWEAPON(){
        return WEAPON;
    }
    public void setWEAPON(String weapon){
        this.WEAPON = weapon;
    }

    public void setDAMAGE(int WEAPONDAMAGE){this.WEAPONDAMAGE = WEAPONDAMAGE;}

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public Position getPOS() {
        return POS;
    }

    public void setPOS(Position POS) {
        //this.OLD = this.POS;
        this.POS = POS;
    }

    public Position getOLD() {
        return OLD;
    }

    public void setOLD(Position POS) {
        this.OLD = OLD;
    }
    public boolean isALIVE(){
        return ALIVE;
    }

    public void kill() {
        this.ALIVE = false;
        //this.setPOS(new Position(-1, -1));
    }

    public boolean isAttackable(){return attackable;}

    public boolean isPlayer(){
        return isPlayer;
    }
    public boolean isSolid(){
        return collision;
    }
    public String toString(){
        return "Entity";
    }

    public String toMap(){
        return " E ";
    }

    public void bark(){

    }




}
