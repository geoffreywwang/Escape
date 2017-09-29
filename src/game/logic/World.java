package game.logic;
import utilities.Vec2d;

import java.util.ArrayList;

public class World {
    private Map map;
    private ArrayList<Mission> archive;
    private int costTotal;

    public World(Map map) {
        this.map = map;
        archive = new ArrayList<>();
    }

    public ArrayList<Vec2d> runMission(Mission mission) {
        archive.add(mission);
        costTotal += mission.getUnit().getCost();
        return map.update(mission);
    }

    public Map getMap() {
        return map;
    }
}