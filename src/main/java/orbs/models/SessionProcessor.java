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

    public String getAllBotsAsJson() {
        // Todo: Add getAllBotsAsJson logic
        ArrayList<Mage> bots = world.getBots();

        String json = "bots { \n";
        for(Mage bot: bots)
            json += bot.toJson() + "\n";
        json += "}\n";

        return json;
    }
}
