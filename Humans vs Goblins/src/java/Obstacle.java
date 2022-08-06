public class Obstacle extends Entity{
    public Obstacle(Position pos){
        this.POS = pos;
        collision = true;
    }
    @Override
    public String toMap(){
        return "|||";

    }

}
