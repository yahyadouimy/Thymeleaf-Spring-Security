package ma.enset.hopital.web;

import ma.enset.hopital.repositories.PatientRp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import ma.enset.hopital.entities.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller @AllArgsConstructor
public class PatientController {
    private PatientRp patientRp;


    @GetMapping(path="/index")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Page<Patient> patients=patientRp.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",patients.getContent());
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",patients.getPageable());
        model.addAttribute("keyword",keyword);
        return "patients";

    };
    @GetMapping(path = "/")
    public  String redirect(){
        return "redirect:/index";
    }


    @GetMapping(path = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String delete(Long id,int page,String keyword){
        patientRp.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }



@GetMapping(path = "/formPatient")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String formPatient(Model model){
       model.addAttribute("patient",new Patient());

        return "formPatient";
    }

    @PostMapping(path = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String save(Model model,
                        @Valid  Patient patient,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return  "formPatient";
       patientRp.save(patient);
        return "redirect:index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  String editPatient(Model model,Long id,int page,String keyword){
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("id",id);
        Patient patient=patientRp.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient",patient);

        return "editPatient";

    }
}