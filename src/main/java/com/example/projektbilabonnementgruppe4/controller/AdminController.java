package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.AdminModel;
import com.example.projektbilabonnementgruppe4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

  private final AdminService adminService;

  @Autowired
  public AdminController(AdminService adminService){
    this.adminService=adminService;
  }

//Viser siden for medarbejder login
  @GetMapping("/employeeLogin")
  public String showEmployeeLogin(){
    return "employeeLogin";
  }

  //Håndterer indsendelse af login-formular
  @PostMapping("/employeeLogin")
  public String processEmployeeLogin(@RequestParam String navn, @RequestParam String password, RedirectAttributes redirectAttributes) {
    if (navn.equals("Nutella")&& password.equals("Smoer")){
      return "redirect:/registerNewEmployee";
    }else {
      redirectAttributes.addFlashAttribute("errorMessage","Forkert brugernavn eller adgangskode");
      return "redirect:/employeeLogin";
    }
  }

  //viser siden for registrering af ny medarbejder
  @GetMapping("/registerNewEmployee")
  public String showRegisterNewEmployee(Model model){
    model.addAttribute("employeeModel", new AdminModel());
    return "registerNewEmployee";
  }

  @PostMapping("/registerNewEmployee")
  public String processRegisterNewEmployee(@ModelAttribute("employeeModel") AdminModel adminModel, RedirectAttributes redirectAttributes) {
    adminService.saveNewEmployee(adminModel);
    return "redirect:/employeeLogin";
  }

}
