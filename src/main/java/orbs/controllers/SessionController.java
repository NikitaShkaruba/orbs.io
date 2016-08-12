package orbs.controllers;

import orbs.models.SessionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {
    @Autowired
    private SessionProcessor sessionProcessor;

    // Returns page with login form
	@RequestMapping(value = "/")
	public String index() {
        // Todo: add actual logic
		return "login_form";
	}

    // Stores user to Database, adds mage to World bean, adds an instance of GameSessionProcessor
    // to the container, returns page with GameField
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        // Todo: add actual post logic
        return "game";
    }

    // GameSessionProcessor redirects user to this die page with some data(like history, kda, etc..)
    @RequestMapping(value = "/death")
    public String death() {
        // Todo: add actual post logic
        return "death_partial";
    }

    @RequestMapping(value="/bot_stats", produces="application/json")
    @ResponseBody
    public String botStats(Model model) {
        String json = sessionProcessor.getAllBotsAsJson();
        return json;
    }
}
