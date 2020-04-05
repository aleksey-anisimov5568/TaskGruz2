package taskGruz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private ArrayList<Node> elems = new ArrayList<>();

    public void add(int name, ArrayList<String> links) {
        elems.add(new Node(name, links));
    }

    public void add(int name) {
        elems.add(new Node(name, new ArrayList<>()));
    }

    public void add(int name, String link) {
        for (int i = 0; i < elems.size(); i++) {
            if (elems.get(i).getValue() == name) {
                elems.get(i).add(name, link);
                return;
            }
        }
    }

    public ArrayList<Track> found(String str, String a, String b, String maxSpeed, String maxTime) {
        ArrayList<Track> tracks = new ArrayList<>();
        ArrayList<Integer> route = new ArrayList<>();
        ArrayList<Track> sortWay0 = new ArrayList<>();
        ArrayList<Track> sortWay = new ArrayList<>();
        ArrayList<Track> sortWay1 = new ArrayList<>();
        ArrayList<Track> sortWay2 = new ArrayList<>();
        found(elemsFound(Integer.parseInt(a)), null, Integer.parseInt(b), new Track(), tracks, Integer.parseInt(maxSpeed), 0, Integer.parseInt(maxTime), 0, 0, -1);
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getCost() < cost) {
                cost = tracks.get(i).getCost();
            }
            System.out.println(tracks.get(i).getList() + " time: " + tracks.get(i).getTime() + " cost: " + tracks.get(i).getCost() + " distance: " + tracks.get(i).getDistance() + " maxSpeed: " + tracks.get(i).getMaxSpeed());
        }
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getCost() == cost) {
                sortWay0.add(tracks.get(i));
            }
        }
        if (sortWay0.size() > 1) {
            int time = Integer.MAX_VALUE;
            for (int i = 0; i < sortWay0.size(); i++) {
                if (sortWay0.get(i).getTime() < time) {
                    time = sortWay0.get(i).getTime();
                }
            }
            for (int i = 0; i < sortWay0.size(); i++) {
                if (sortWay0.get(i).getTime() == time) {
                    sortWay.add(sortWay0.get(i));
                }
            }
        }else{
            return sortWay0;
        }
            if (sortWay.size() > 1) {
                int distance = Integer.MAX_VALUE;
                for (int i = 0; i < sortWay.size(); i++) {
                    if (sortWay.get(i).getDistance() < distance) {
                        distance = sortWay.get(i).getDistance();
                    }
                }
                for (int i = 0; i < sortWay.size(); i++) {
                    if (sortWay.get(i).getDistance() == distance) {
                        sortWay1.add(sortWay.get(i));
                    }
                }
            } else {
                return sortWay;
            }
            if (sortWay1.size() > 1) {
                int maxSpeed1 = Integer.MAX_VALUE;
                for (int i = 0; i < sortWay1.size(); i++) {
                    if (sortWay1.get(i).getMaxSpeed() < maxSpeed1) {
                        maxSpeed1 = sortWay.get(i).getMaxSpeed();
                    }
                }
                for (int i = 0; i < sortWay1.size(); i++) {
                    if (sortWay1.get(i).getMaxSpeed() == maxSpeed1) {
                        sortWay2.add(sortWay1.get(i));
                    }
                }
            } else {
                return sortWay1;
            }

            return sortWay2;
        }

    

    private void found(Node elem, Node prev, int b, Track track, ArrayList<Track> list, int maxSpeed, int currentTime, int time, int distance, int cost, int maxSpeed2) {
        track.add(elem.getValue());
        track.setTime(currentTime);
        track.setDistance(distance);
        track.setCost(cost);
        track.setMaxSpeed(maxSpeed2);
        if (elem.getValue() == b) {
            list.add(track);
        }
        ArrayList<Way> links = elem.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Node node = elemsFound(links.get(i).getLink());
            //System.out.println(elem.getValue() + " " + links.get(i).getLink() + " " + maxSpeed + " " + links.get(i).getMaxSpeed() + " " + links.get(i).getLength() + " " + links.get(i).getLength()/(double)links.get(i).getMaxSpeed());
            if (!elems.get(i).equals(prev) && currentTime + ((double) links.get(i).getLength() / ((links.get(i).getMaxSpeed() < maxSpeed) ? ((double) links.get(i).getMaxSpeed()) : ((double) maxSpeed))) * 3600 <= time) {
                found(node, prev, b, track.copy(), list, maxSpeed, (int) (currentTime + (links.get(i).getLength() / ((links.get(i).getMaxSpeed() < maxSpeed) ? ((double) links.get(i).getMaxSpeed()) : ((double) maxSpeed))) * 3600),
                        time, distance + links.get(i).getLength(), cost + links.get(i).getCost(), Math.min(maxSpeed, links.get(i).getMaxSpeed()));
            }
        }
    }

    private class DoubleData {

        int x, y;

        public DoubleData(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean routeCheck(ArrayList<Integer> route, ArrayList<DoubleData> check) {
        for (int i = 0; i < check.size(); i++) {
            if (route.contains(check.get(i).x) && route.contains(check.get(i).y) && route.indexOf(check.get(i).x) < route.indexOf(check.get(i).y)) {
                for (int j = 0; j < check.size(); j++) {
                    if (i != j && route.contains((check.get(j).y)) && route.indexOf(check.get(i).y) < route.indexOf(check.get(j).y) && check.get(i).y == check.get(j).x) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Node elemsFound(int name) {
        for (int i = 0; i < elems.size(); i++) {
            if (elems.get(i).getValue() == name) {
                return elems.get(i);
            }
        }
        return null;
    }

}
