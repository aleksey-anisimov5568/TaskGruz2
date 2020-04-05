package taskGruz;

import java.util.ArrayList;

public class Track {
    private ArrayList<Integer> list;
    private int time;
    private int cost;
    private int distance;
    private int maxSpeed;
    
    public Track(ArrayList<Integer> list, int time, int cost, int distance, int maxSpeed){
        this.list = list;
        this.time = time;
        this.cost = cost;
        this.distance = distance;
        this.maxSpeed = maxSpeed;
    }
    
    public Track(ArrayList<Integer> list){
        this(list,0,0,0,0);
    }
    
    public Track(){
        this(new ArrayList<>());
    }
    
    public Track copy(){
        return new Track((ArrayList)list.clone(), time, cost, distance, getMaxSpeed());
    }
    
    public void add(int value){
        list.add(value);
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    
}
