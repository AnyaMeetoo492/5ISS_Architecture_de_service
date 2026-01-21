package fr.insa.ms.Interface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterfaceController {
    
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}
