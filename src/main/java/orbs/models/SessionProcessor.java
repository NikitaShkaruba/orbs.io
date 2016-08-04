package orbs.models;

import org.springframework.beans.factory.annotation.Autowired;

// Client sends to socket his: coordinates relative to his screen, key presses, mouse clicks.
// GameSessionProcessor processes them - from screen-relative to world-relative, then he updates the World
public class SessionProcessor {
    @Autowired
    private World world;

    public void createSession() {
        // Todo: Add createSession logic
        System.out.println("----------------- Bots amount from WORLD singleton object is: " + world.getBots().size() + " -------------------");
    }
}
