package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //Viser startsiden
    @GetMapping("/")
    public String home(HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");
        return "home";
    }
}
