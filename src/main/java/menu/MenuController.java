package menu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MenuController {
    // annotation specifies that menu() method handles /menu http(get, post, delete, ...) route
    @RequestMapping("/")
    public String menu(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        // render menu.html
        return "menu";
    }
}
