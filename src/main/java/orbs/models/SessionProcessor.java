package orbs.models;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

// Client sends to socket his: coordinates relative to his screen, key presses, mouse clicks.
// GameSessionProcessor processes them - from screen-relative to world-relative, then he updates the World
public class SessionProcessor {
    @Autowired
    private World world;

    public void startSession() {
        // Todo: add logic
    }

    public void printAllBots() {
        // Todo: Add printAllBots logic
        ArrayList<Mage> bots = world.getBots();

        System.out.println("** SessionProcessor's printAllBots() start **");
        for(Mage bot: bots)
            System.out.println(bot.toJson());
        System.out.println("** SessionProcessor's printAllBots() end **");
    }
}
