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
@RequestMapping("employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService){
    this.employeeService = employeeService;
  }

  //Viser siden for medarbejder login
  @GetMapping("/login")
  public String showEmployeeLogin(Model model){
    model.addAttribute("employeeModel", new EmployeeModel());
    return "employee/employeeLogin";
  }

  //Håndterer indsendelse af login-formular
  @PostMapping("/login")
  public String processEmployeeLogin(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
    EmployeeModel loggedInUser = employeeService.validateUser(username, password);

    if (loggedInUser != null){
      session.setAttribute("loggedInUser", loggedInUser);
      return "redirect:/";
    } else {
      redirectAttributes.addFlashAttribute("loginError", "Brugernavn eller Kodeord er forkert.");
      return "redirect:/employee/login";
    }
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("loggedInUser");
    return "redirect:/";
  }

  //viser siden for registrering af ny medarbejder
  @GetMapping("/register")
  public String showRegisterNewEmployee( Model model, HttpSession session) {
    EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

    if (loggedInUser != null) {
      model.addAttribute("employeeModel", new EmployeeModel());
      return "employee/registerNewEmployee";
    } else {
      return "redirect:/";
    }
  }

  @PostMapping("/register")
  public String processRegisterNewEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel, RedirectAttributes redirectAttributes) {
    employeeService.saveNewEmployee(employeeModel);
    return "redirect:/employee/menu";
  }

  // Viser listen over medarbejdere
  @GetMapping("/list")
  public String showEmployeeList(Model model, HttpSession session) {
    EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

    if (loggedInUser != null) {
      List<EmployeeModel> employeeList = employeeService.getAllEmployees();
      model.addAttribute("employeeList", employeeList);
      return "employee/employeeList";
    } else {
      return "redirect:/";
    }
  }

  // Viser siden for redigering af medarbejder/vej til profile
  @GetMapping("/edit")
  public String showEditEmployee( Model model, HttpSession session) {
    EmployeeModel loggedInUser = (EmployeeModel) session.getAttribute("loggedInUser");

    EmployeeModel employeeModel = employeeService.getEmployeeByUsername(loggedInUser.getUsername());

    if (loggedInUser != null) {
      model.addAttribute("employeeModel", employeeModel);
      return "employee/updateEmployee";
    } else {
      return "redirect:/";
    }
  }

  // Håndterer redigering af medarbejderoplysninger
  @PostMapping("/update")
  public String processEditEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employee/list";
  }

  //gemmer nye opdateringer
  @PostMapping("/saveUpdate")
  public String saveUpdatedEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employee/list";
  }


  // Sletter en medarbejder ud fra brugernavn
  @PostMapping("/delete")
  public String deleteEmployee(@RequestParam String username) {
    employeeService.deleteEmployee(username);
    return "redirect:/employee/list";
  }
}
