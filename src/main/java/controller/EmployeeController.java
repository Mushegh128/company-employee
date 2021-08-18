package controller;

import model.Company;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import repository.CompanyRepository;
import repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/employeesAll")
    public String employees(ModelMap modelMap) {
        try {
            List<Employee> employees = employeeRepository.findAll();
            modelMap.addAttribute("employees", employees);
            return "employees";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

    @GetMapping("/addEmployee")
    public String addEmployee(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies", companies);
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployeePost(@ModelAttribute Employee employee) {
        Company company = employee.getCompany();
        int size = company.getSize();
        company.setSize(size++);
        companyRepository.save(company);
        employeeRepository.save(employee);
        return "redirect:/employeeAll";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            Employee employeePr = employee.get();
            Company company = employeePr.getCompany();
            int size = company.getSize();
            company.setSize(size--);
            companyRepository.save(company);
        }
        employeeRepository.deleteById(id);
        return "redirect:/employeesAll";
    }


}
