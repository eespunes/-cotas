package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.Animal;
import cat.tecnocampus.omega.domain.Image;
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
import java.io.IOException;
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

    @GetMapping("/seguro")
    public String seguro() {
        return "seguro";
    }

    @GetMapping("/ludogat")
    public String ludogat() {
        return "ludogat";
    }


    @GetMapping("registrarAnimal")
    public String getCreateAnimal(Model model) {
        model.addAttribute(new Animal());
        model.addAttribute("animalTypeList", animalController.getTypes());
        return "registrarAnimal";
    }

    @PostMapping("registrarAnimal")
    public String postCreateAnimal(@Valid Animal animal, @RequestParam("photo") MultipartFile photo, Errors errors, Model model, RedirectAttributes redirectAttributes) throws IOException {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "signup";
        }
        animal.setImage(new Image(photo.getBytes()));
        animalController.addImage(animal.getImage());
        animalController.addType(animal.getType());
        animalController.addAnimal(animal);
        return "redirect:/animal/all";
    }

    @GetMapping("animal/all")
    public String getAnimals(Model model) {
        model.addAttribute("typeList", animalController.getTypes());
        model.addAttribute("animalList", animalController.getAll());
        return "animales";
    }

    @GetMapping("animal/all/{type}")
    public String getAnimals(Model model, @PathVariable String type) {
        model.addAttribute("typeList", animalController.getTypes());
        model.addAttribute("animalList", animalController.getByType(type));
        return "animales";
    }

    @GetMapping("animal/apadrinado/{id}")
    public String getApadriname(Model model, @PathVariable String id) {
        return "apadrinaAnimal";
    }

    @PostMapping("animal/apadrinado/{id}")
    public String postApadriname(@PathVariable String id, Principal principal) {
        animalController.setOwner(principal.getName(), animalController.getById(id).getId());
        return "redirect:/animal/all";
    }
}
