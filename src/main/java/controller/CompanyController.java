package controller;

import model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repository.CompanyRepository;

import java.util.List;


@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companies(ModelMap modelMap){
        List<Company> companies = companyRepository.findAll();
        modelMap.addAttribute("companies",companies);
        return "/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(){
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String addCompanyPost(@ModelAttribute Company company){
        companyRepository.save(company);
        return "redirect:/companies";
    }

}
