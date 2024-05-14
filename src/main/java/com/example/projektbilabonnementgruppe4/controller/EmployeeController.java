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
      return "redirect:/employee/menu";
    } else {
      redirectAttributes.addFlashAttribute("loginError", "Brugernavn eller Kodeord er forkert.");
      return "redirect:/employee/login";
    }
  }


  //Menu
  @GetMapping("/menu")
  public String showMenuEmployee() {
    return "employee/menuEmployee";
  }
  @PostMapping("/menu")
  public String chooseFromMenu(@RequestParam("action") String action) {
    switch(action) {
      case "registerNewEmployee":
        return "redirect:/employee/register";
      case "editEmployee":
        return "redirect:/employee/list";
      default:
        return "redirect:/employee/menu";
    }
  }



  //viser siden for registrering af ny medarbejder
  @GetMapping("/register")
  public String showRegisterNewEmployee( Model model){
    model.addAttribute("employeeModel", new EmployeeModel());
    return "employee/registerNewEmployee";
  }
  @PostMapping("/register")
  public String processRegisterNewEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel, RedirectAttributes redirectAttributes) {
    employeeService.saveNewEmployee(employeeModel);
    return "redirect:/employee/menu";
  }






  // Viser listen over medarbejdere
  @GetMapping("/list")
  public String showEmployeeList(Model model) {
    List<EmployeeModel> employeeList = employeeService.getAllEmployees();
    model.addAttribute("employeeList", employeeList);
    return "employee/employeeList";
  }

  // Viser siden for redigering af medarbejder
  @GetMapping("/edit")
  public String showEditEmployee(@RequestParam String username, Model model) {
    EmployeeModel employeeModel = employeeService.getEmployeeByUsername(username);
    if (employeeModel == null) {
      return "redirect:/employee/list";
    }
    model.addAttribute("employeeModel", employeeModel);
    return "employee/updateEmployee";
  }




  // Håndterer redigering af medarbejderoplysninger
  @PostMapping("/update")
  public String processEditEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employee/register";
  }

  //gemmer nye opdateringer
  @PostMapping("/saveUpdate")
  public String saveUpdatedEmployee(@ModelAttribute("employeeModel") EmployeeModel updatedEmployeeModel) {
    employeeService.editEmployee(updatedEmployeeModel);
    return "redirect:/employee/edit";
  }





  // Sletter en medarbejder ud fra brugernavn
  @PostMapping("/employee/delete")
  public String deleteEmployee(@RequestParam String username) {
    employeeService.deleteEmployee(username);
    return "redirect:/employee/list";
  }

}
