package entites;

/**
 *
 * @author alexis
 */
public class CNode {
    private String status;
    private int tabXY[];
    private int x;
    private int y;
    private int poids;

    public CNode(int x, int y, String status) {
        this.setStatus(status);
        this.setX(x);
        this.setY(y);
        int tabXY[] = {x,y};
        this.setTabXY(tabXY);
        this.setPoids(Integer.MAX_VALUE);
    }

    public CNode(int[] tabXY, String status) {
        this.setStatus(status);
        this.setTabXY(tabXY);
        this.setX(tabXY[0]);
        this.setY(tabXY[1]);
    }
    
    public void setPoids(int poids) {
        this.poids = poids;
    }
    
    public int getPoids(){
        return this.poids;
    }
    
    public int[] getTabXY() {
        return tabXY;
    }

    public void setTabXY(int[] tabXY) {
        if (tabXY.length == 2){
            this.tabXY = tabXY;
        }
        else{
            System.out.println("tab length != 2 !");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y != 0){
            this.y = y;
        }
        else{
            System.out.println("y = "+y+" !");
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int X) {
        if (x == 0){
            this.x = X;
        }
        else{
            System.out.println("x = "+x+" !");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == "undefined" || status == "visited" || status == "neighboor" || status == "end" || status == "start" || status == "current"){
            this.status = status;
        }
        else{
            this.status = "undefined";
        }
    }
    
    @Override
    public String toString(){
        return this.getX() + ":" + this.getY() + " " + this.getStatus() + " " + this.getPoids();
    }
    
}
