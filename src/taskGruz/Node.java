package taskGruz;

import java.util.ArrayList;

public class Node {

    private int value;
    private ArrayList<Way> links;

    public Node(int value) {
        this.value = value;
        this.links = new ArrayList<>();
    }

    public Node(int value, ArrayList<String> links) {
        ArrayList<Way> links1 = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            String[] arr = links.get(i).split(":");
            links1.add(new Way(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4])));
        }
        this.value = value;
        this.links = links1;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Way> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        ArrayList<Way> links1 = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            String[] arr = links.get(i).split(":");
            links1.add(new Way(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4])));
        }
        this.links = links1;
    }

    public void add(int name, String link){
        String[] arr = link.split(":");
        //System.out.println(Integer.parseInt(arr[0]) + " " + Integer.parseInt(arr[1]) + " " + Integer.parseInt(arr[2]) + " " + name + " " + Integer.parseInt(arr[3]));
        links.add(new Way(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), name, Integer.parseInt(arr[3])));
    }
}
