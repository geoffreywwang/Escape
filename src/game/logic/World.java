package game.logic;
import java.util.ArrayList;

public class World {
    private Map island;
    private ArrayList<Mission> archive;
    private int costTotal;

    public World(Map island) {
        this.island = island;
        archive = new ArrayList<>();
    }

    public void runMission(Mission mission) {
        island.update(mission);
        archive.add(mission);
        costTotal += mission.getUnit().getCost();
    }
}