package org.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogginController {
    @GetMapping("/")
    public String InicioSesion(HttpServletRequest request, Model model){
        return "Login";
    }
}
