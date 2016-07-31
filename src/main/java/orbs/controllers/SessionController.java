package orbs.controllers;

import orbs.models.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {
    // Returns page with login form
	@RequestMapping(value = "/")
	public String index() {
		// render index.html
		return "login_form";
	}

    // Stores user to Database, adds mage to World bean, adds an instance of GameSessionProcessor
    // to the container, returns page with GameField
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user) {
        // Todo: add actual post logic
        return "game";
    }

    // GameSessionProcessor redirects user to this die page with some data(like history, kda, etc..)
    @RequestMapping(value = "/death")
    public String death() {
        // Todo: add actual post logic
        // render index.html
        return "death_partial";
    }
}
