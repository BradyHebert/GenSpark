import java.util.*;

public class Map {
    private int width, height;

    private HashMap<Position, Entity> posMap = new HashMap<>();


    private boolean[][] collisionMap;





    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_GREEN = "\u001B[32m";
    private String ANSI_YELLOW = "\u001B[33m";
    private String ANSI_RED = "\u001B[31m";

    public Map(int width, int height){
        this.width = width;
        this.height = height;
        collisionMap = new boolean[width][height];

    }

    public boolean checkCollision(Position pos){
        return collisionMap[pos.getX()][pos.getY()];

    }


    public void setPOS(Position pos, Entity x){
        if(x.isSolid()) {
            collisionMap[x.getPOS().getX()][x.getPOS().getY()] = false;
            collisionMap[pos.getX()][pos.getY()] = true;
        }
        posMap.put(pos, x);
        x.setPOS(pos);
    }

    public void updatePOS(Position pos, Entity x){
        boolean allowMove = true;
        Iterator it = posMap.entrySet().iterator();
        while (it.hasNext()){

            HashMap.Entry entry = (HashMap.Entry)it.next();
            Position key = (Position) entry.getKey();
            Entity val = (Entity) entry.getValue();
            if (key.equals(pos)) {
                if (x.isPlayer()) {
                    if (val.isSolid()) {
                        if (!val.isAttackable()) {
                            allowMove = false;
                        }
                    }
                }
            }

            if (key.equals(x.getPOS()) && val.equals(x) && allowMove){
                it.remove();


            }
        }
        if (allowMove) {
            if(x.isSolid()) {
                collisionMap[x.getPOS().getX()][x.getPOS().getY()] = false;
                collisionMap[pos.getX()][pos.getY()] = true;
            }
            posMap.put(pos, x);
            x.setPOS(pos);

        } else {
            posMap.put(x.getPOS(), x);
            x.setPOS(x.getPOS());
        }
    }

    public Position pathfinderA(Position pos, Position tar){
        boolean finished = false;
        Position ans = pos;
        ArrayList<Position> path = new ArrayList<>();
        ArrayList<Position> open = new ArrayList<>();
        ArrayList<Position> closed = new ArrayList<>();

        open.add(pos);

        while(!open.isEmpty()&&!finished){
            Position q = open.get(0);
            for (Position node : open) {
                int nodeF = node.getG() + node.distanceFrom(tar);
                int qF = q.getG()+ q.distanceFrom(tar);
                if (nodeF < qF)
                    q = node;
            }
            open.remove(q);
            ArrayList<Position> neighbors = findNeighbors(q);


            for (Position neighbor : neighbors){
                if (neighbor.equals(tar)){

                    ans = neighbor;
                    finished = true;

                } else {
                    neighbor.setG(q.getG()+1);
                }
                //if open contains node with same POS and lower f skip
                boolean skip = false;
                for (Position openNode : open) {
                    if (openNode.equals(neighbor) && openNode.getG() + openNode.distanceFrom(tar) <= neighbor.getG() + neighbor.distanceFrom(tar)){

                        skip = true;
                    }
                }
                for (Position closedNode : closed) {
                    if (closedNode.equals(neighbor) && closedNode.getG() + closedNode.distanceFrom(tar) <= neighbor.getG() + neighbor.distanceFrom(tar)){

                        skip = true;
                    }
                }
                if (!skip){

                    open.add(neighbor);
                }

            }
            closed.add(q);
        }
        if (finished){
            while (!ans.equals(pos)){
                path.add(0, ans);
                ans = ans.getPrevious();

            }
            return path.get(0);
        } else {
            return pos;
        }
    }

    public ArrayList<Position> findNeighbors (Position pos){

        ArrayList<Position> neighbors = new ArrayList<>();
        ArrayList<Position> test = new ArrayList<>();
        test.add(new Position(pos.getX()+1, pos.getY(), pos));
        test.add(new Position(pos.getX()-1, pos.getY(), pos));
        test.add(new Position(pos.getX(), pos.getY()+1, pos));
        test.add(new Position(pos.getX(), pos.getY()-1, pos));
        for (Position neighbor : test)
            if ((neighbor.getX() >= 0 && neighbor.getX() < width) && (neighbor.getY() >= 0 && neighbor.getY() < height))
                if (!collisionMap[neighbor.getX()][neighbor.getY()])
                    neighbors.add(neighbor);
        return neighbors;
    }





    public void kill(Position pos, Entity x){
        Iterator it = posMap.entrySet().iterator();
        while (it.hasNext()){
            HashMap.Entry entry = (HashMap.Entry)it.next();
            Position key = (Position) entry.getKey();
            Entity val = (Entity) entry.getValue();
            if (pos.equals(key) && x == val){
                it.remove();
            }
        }
    }


    @Override
    public String toString(){
        StringBuilder map = new StringBuilder();
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++) {
                Position pos = new Position(j, i);
                boolean trigger = false;
                Entity val = null;
                for (HashMap.Entry<Position, Entity> entry : posMap.entrySet()) {
                    Position key = entry.getKey();

                    if (pos.equals(key)) {
                            trigger=true;
                            val = entry.getValue();
                    }
                }
                if(trigger){
                    map.append(val.toMap());

                } else {
                    map.append(" . ");
                    collisionMap[j][i]=false;
                }
            } map.append("\n");
        }
        return map.toString();
    }
}
