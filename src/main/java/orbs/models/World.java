package orbs.models;

import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(fixedRate = 500)
    public void timeTick() {
        // Todo: add timetick logic
        System.out.println("+++ World tick");
        updateBots();
    }
    public void updatePlayer() {

    }
    public void updateBots() {
        for(Mage bot: this.bots) {
            Point2D.Double coordsBefore = bot.getCoordinates();
            bot.move(coordsBefore.x + random.nextFloat() % 6, coordsBefore.y + random.nextFloat() % 6);
        }
    }
    private ArrayList<Mage> generateBots() {
        bots = new ArrayList<Mage>();

        int botsAmount =  3;
        for(int i = 0; i < botsAmount; i++) {
            String name = "bot" + i;
            Point2D.Double coordinates = new Point2D.Double(random.nextDouble() % 100, random.nextDouble() % 100);
            bots.add(new Mage(name, coordinates));
        }

        return bots;
    }
}
