package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.repository.EmployeeRepository;
import com.example.projektbilabonnementgruppe4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService){
    this.employeeService = employeeService;
  }

//Viser siden for medarbejder login
  @GetMapping("/employeeLogin")
  public String showEmployeeLogin(Model model){
    model.addAttribute("employeeModel", new EmployeeModel());
    return "employeeLogin";
  }

  //Håndterer indsendelse af login-formular
  @PostMapping("/employeeLogin")
  public String processEmployeeLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
    EmployeeModel loggedInUser = employeeService.validateUser(username, password);

    if (loggedInUser != null){
      session.setAttribute("loggedInUser", loggedInUser);
      return "redirect:/registerNewEmployee";
    } else {
      model.addAttribute("errorMessage","Forkert brugernavn eller adgangskode");
      return "/employeeLogin";
    }
  }
  //viser siden for registrering af ny medarbejder
  @GetMapping("/registerNewEmployee")
  public String showRegisterNewEmployee(Model model){
    model.addAttribute("employeeModel", new EmployeeModel());
    return "registerNewEmployee";
  }
  @PostMapping("/registerNewEmployee")
  public String processRegisterNewEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel, RedirectAttributes redirectAttributes) {
    employeeService.saveNewEmployee(employeeModel);
    return "redirect:/employeeLogin";
  }

}
