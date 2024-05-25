package com.example.projektbilabonnementgruppe4.controller;

import com.example.projektbilabonnementgruppe4.model.Employee;
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

    //Autowire indsætter EmployeService-instansen i EmployeeController
    @Autowired
    private EmployeeService employeeService;

    // Viser medarbejderloginsiden
    @GetMapping("/login")
    public String showEmployeeLogin(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employeeLogin";
    }

    //Håndterer indsendelse af login-formular
    @PostMapping("/login")
    public String processEmployeeLogin(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        // Validerer brugernavn og kodeord og returnerer den indloggede bruger.
        Employee loggedInUser = employeeService.validateUser(username, password);

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Brugernavn eller Kodeord er forkert.");
            return "redirect:employee/login";
        }
    }

    //Når brugeren logger ud fjernes loggedInUser fra session og sendes tilbage til startsiden.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        return "redirect:/";
    }

    //Viser registreringssiden for nye medarbejdere
    @GetMapping("/register")
    public String showRegisterNewEmployee(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("employee", new Employee());
            return "employee/registerNewEmployee";
        } else {
            return "redirect:/";
        }
    }

    //Behandler indsendelse af registreringsformular og gemmer den nye medarbejder i DB.
    @PostMapping("/register")
    public String processRegisterNewEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        employeeService.saveNewEmployee(employee);
        return "redirect:employee/list";
    }

    // Sendes til listen over medarbejdere, når "tilbage til menu" knappen trykkes.
    @GetMapping("/menu")
    public String showMenu(HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            return "redirect:employee/list";
        } else {
            return "redirect:/";
        }
    }

    //Viser listen over medarbejdere
    @GetMapping("/list")
    public String showEmployeeList(Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            List<Employee> employeeList = employeeService.getAllEmployees();
            model.addAttribute("employeeList", employeeList);
            return "employee/employeeList";
        } else {
            return "redirect:/";
        }
    }

    //Viser siden til redigering af en medarbejder, hvis en bruger er logget ind
    @GetMapping("/edit")
    public String showEditEmployee(@RequestParam("username") String username, Model model, HttpSession session) {
        Employee loggedInUser = (Employee) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Employee employee = employeeService.getEmployeeByUsername(username);
            model.addAttribute("employee", employee);
            return "employee/updateEmployee";
        } else {
            return "redirect:/";
        }
    }

    //Behandler redigering af medarbejderoplysninger.
    @PostMapping("/update")
    public String processEditEmployee(@ModelAttribute("employee") Employee updatedEmployee) {
        employeeService.editEmployee(updatedEmployee);
        return "redirect:employee/list";
    }

    // Sletter en medarbejder baseret brugernavn
    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam String username) {
        employeeService.deleteEmployee(username);
        return "redirect:employee/list";
    }
}

