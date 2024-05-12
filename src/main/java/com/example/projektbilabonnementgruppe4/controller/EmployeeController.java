package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.EmployeeModel;
import com.example.projektbilabonnementgruppe4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    return "employee/employeeLogin";
  }

  //Håndterer indsendelse af login-formular
  @PostMapping("/employeeLogin")
  public String processEmployeeLogin(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
    EmployeeModel loggedInUser = employeeService.validateUser(username, password);

    if (loggedInUser != null){
      session.setAttribute("loggedInUser", loggedInUser);
      return "redirect:/menuEmployee";
    } else {
      redirectAttributes.addFlashAttribute("loginError", "Brugernavn eller Kodeord er forkert.");
      return "employee/employeeLogin";
    }
  }


  //Menu
  @GetMapping("/menuEmployee")
  public String showMenuEmployee() {
    return "employee/menuEmployee";
  }
  @PostMapping("/menuEmployee")
  public String chooseFromMenu(@RequestParam("action") String action) {
    switch(action) {
      case "registerNewEmployee":
        return "redirect:/registerNewEmployee";
      case "editEmployee":
        return "redirect:/employeeList"; // Ændret til at bruge GET-anmodning
      default:
        return "redirect:/menuEmployee"; // Redirect back to menu if action is not recognized
    }
  }

  //viser siden for registrering af ny medarbejder
  @GetMapping("/registerNewEmployee")
  public String showRegisterNewEmployee( Model model){
    model.addAttribute("employeeModel", new EmployeeModel());
    return "employee/registerNewEmployee";
  }
  @PostMapping("/registerNewEmployee")
  public String processRegisterNewEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel, RedirectAttributes redirectAttributes) {
    employeeService.saveNewEmployee(employeeModel);
    return "redirect:/menuEmployee";
  }

  // Viser listen over medarbejdere
  @GetMapping("/employeeList")
  public String showEmployeeList(Model model) {
    List<EmployeeModel> employeeList = employeeService.getAllEmployees();
    model.addAttribute("employeeList", employeeList);
    return "employee/employeeList";
  }



  // Viser siden for redigering af medarbejder
  @GetMapping("/editEmployee")
  public String showEditEmployee(@RequestParam String username, Model model) {
    EmployeeModel employeeModel = employeeService.getEmployeeByUsername(username);
    if (employeeModel == null) {
      return "redirect:/employee/employeeList";
    }
    model.addAttribute("employeeModel", employeeModel);
    return "employee/updateEmployee";
  }


  // Håndterer redigering af medarbejderoplysninger
  @PostMapping("/updateEmployee")
  public String processEditEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employeeList";
  }

  //gemmer nye opdateringer
  @PostMapping("/saveUpdateEmployee")
  public String saveUpdatedEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employee/employeeList";
  }


  // Sletter en medarbejder ud fra brugernavn
  @PostMapping("/employee/deleteEmployee")
  public String deleteEmployee(@RequestParam String username) {
    employeeService.deleteEmployee(username);
    return "redirect:/employeeList";
  }

}
