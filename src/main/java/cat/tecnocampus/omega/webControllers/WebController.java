package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.Animal;
import cat.tecnocampus.omega.domain.User;
import cat.tecnocampus.omega.persistanceController.AnimalController;
import cat.tecnocampus.omega.persistanceController.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class WebController {

    private AnimalController animalController;

    public WebController(AnimalController animalController) {
        this.animalController = animalController;
    }

    @GetMapping("/introduccion")
    public String introduccion() {
        return "introduccion";
    }

    @GetMapping("/mision_vision_valores")
    public String mision_vision_valores() {
        return "mision_vision_valores";
    }

    @GetMapping("/ayudanos")
    public String ayudanos() {
        return "ayudanos";
    }

    @GetMapping("/colabora")
    public String colabora() {
        return "colabora";
    }

    @GetMapping("/socio")
    public String socio() {
        return "socio";
    }

    @GetMapping("/socio+")
    public String socioPlus() {
        return "socio+";
    }

    @GetMapping("/apadrina")
    public String apadrina() {
        return "apadrina";
    }

    @GetMapping("/apadrina/animales")
    public String verAnimales() {
        return "apadrina/animales";
    }

    @GetMapping("/voluntariado")
    public String voluntariado() {
        return "voluntariado";
    }

    @GetMapping("/donacion")
    public String donacion() {
        return "donacion";
    }

    @GetMapping("/veterinario")
    public String veterinario() {
        return "veterinario";
    }

    @GetMapping("/ludogat")
    public String ludogat() {
        return "ludogat";
    }


    @GetMapping("registrarAnimal")
    public String getCreateUser(Model model) {
        model.addAttribute(new Animal());
//        model.addAttribute("categoryList",animalController.getTypes());
        //return "RegisterUser";
        return "registrarAnimal";
    }

    @PostMapping("registrarAnimal")
    public String postCreateUser(@Valid Animal animal, @RequestParam("photo") MultipartFile photo, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "signup";
        }
        animalController.addType(animal.getType());
        animalController.addAnimal(animal);
        return "redirect:/introduccion";
    }
}
