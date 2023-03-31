package java20migration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
    @GetMapping("/")
    String index(Model model) {
        return "index";
    }

    @PostMapping("/占う")
    String 占い(@RequestParam String お名前, Model model) {
        Fortune fortune = new Fortune(お名前);
        model.addAttribute("name", お名前);
        model.addAttribute("fortune", fortune);
        return "index";

    }
}