package orbs.models.beans;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

// Stores all the game information. It's like a world itself. He stores all the players info,
// all the casted spells, all the statistic about highscore, etc... This bean is updated every 0.5(for now) seconds.
// And this bean uses information from GamsSessionProcessor to update himself
public class World {
    private ArrayList<Mage> players;
    private ArrayList<Mage> bots;
    private Random random;

    public World() {
        random = new Random();
        players = new ArrayList<Mage>();
        bots = generateBots();
    }

    public ArrayList<Mage> getPlayers() {
        return players;
    }
    public ArrayList<Mage> getBots() {
        return bots;
    }

    public void timeTick() {
        // Todo: add timetick logic
        updateBots();
    }
    public void updatePlayer() {

    }
    public void updateBots() {
        for(Mage bot: bots) {
            Point2D.Double coordsBefore = bot.getCoordinates();
            bot.move(coordsBefore.x + random.nextFloat() % 6, coordsBefore.y + random.nextFloat() % 6);
        }
    }
    private ArrayList<Mage> generateBots() {
        Random rand = new Random();
        bots = new ArrayList<Mage>();

        int botsAmount =  3; //rand.nextInt(8) + 2;
        for(int i = 0; i < botsAmount; i++)
            bots.add(new Mage(rand.nextDouble() % 100, rand.nextDouble() % 100)); // % 100 limits random number from 0 - 99

        return bots;
    }
}
