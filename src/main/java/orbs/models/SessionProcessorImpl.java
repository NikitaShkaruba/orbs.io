package orbs.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Client sends to socket his: coordinates relative to his screen, key presses, mouse clicks.
// GameSessionProcessor processes them - from screen-relative to world-relative, then he updates the World
@Service
//@Scope("session")
public class SessionProcessorImpl implements SessionProcessor {
    public void handleInput() {
        // Todo: Add handleInput logic
        System.out.println("Hello from SessionProcessor");
    }
}
