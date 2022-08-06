public class Position {
    private int x, y;
    private int g = 0;
    private Position previous;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position(int x, int y, Position previous){
        this.x = x;
        this.y = y;
        this.previous = previous;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position pos){
        if (x == pos.getX() && y == pos.getY()){
            return true;
        } else {
            return false;
        }
    }

    public int distanceFrom(Position pos){
        int xDist = Math.abs(x-pos.getX());
        int yDist = Math.abs(y-pos.getY());
        return xDist + yDist;
    }

    public String toString(){
        return "("+ x + ", " + y + ") ";
    }


    public Position getPrevious() {
        return previous;
    }

    public void setPrevious(Position previous) {
        this.previous = previous;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
