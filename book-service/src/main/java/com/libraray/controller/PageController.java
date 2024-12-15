package com.libraray.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";  // This will return welcome.html
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";  // This will return welcome.html
    }

    @GetMapping("/student")
    public String employees() {
        return "students.html";  // Return employees.html for the employees page
    }

    @GetMapping("/employee")
    public String employeeForm() {
        return "employee_form.html";  // Return employee_form.html for the employee form
    }

    @GetMapping("/books")
    public String books() {
        return "book.html";  // Return books.html for the books page
    }
}
