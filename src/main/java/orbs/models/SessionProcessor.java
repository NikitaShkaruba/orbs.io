package orbs.models;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import java.util.ArrayList;

// Client sends to socket his: coordinates relative to his screen, key presses, mouse clicks.
// GameSessionProcessor processes them - from screen-relative to world-relative, then he updates the World
public class SessionProcessor {
    @Autowired private World world;

    public void startSession() {
        // Todo: add logic
    }

    public String getAllBotsAsJson() {
        Gson gson = new Gson();
        ArrayList<Mage> bots = world.getBots();
        return gson.toJson(bots);
    }
}
