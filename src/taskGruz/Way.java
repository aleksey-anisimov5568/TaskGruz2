package taskGruz;

public class Way {
    private int length;
    private int maxSpeed;
    private int cost;
    private int elem;
    private int link;
    
    public Way(int length, int maxSpeed, int cost, int elem, int link){
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.cost = cost;
        this.elem = elem;
        this.link = link;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getElem() {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }
    
    
}
