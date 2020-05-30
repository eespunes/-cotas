package cat.eespunes.mascotas.webControllers;

import cat.eespunes.mascotas.domain.Animal;
import cat.eespunes.mascotas.domain.Image;
import cat.eespunes.mascotas.persistanceController.AnimalController;
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

    @GetMapping("/mascotas")
    public String mascotas() {
        return "mascotas";
    }

    @GetMapping("/ludogat")
    public String ludogat() {
        return "ludogat";
    }

    @GetMapping("/cliente")
    public String cliente() {
        return "cliente";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/forma_juridica")
    public String forma_juridica() {
        return "forma_juridica";
    }

    @GetMapping("/medio_ambiente")
    public String medio_ambiente() {
        return "medio_ambiente";
    }

    @GetMapping("/prl")
    public String prl() {
        return "prl";
    }

    @GetMapping("/apadrina/animales")
    public String verAnimales() {
        return "apadrina/animales";
    }

    @GetMapping("/competencia")
    public String competencia() {
        return "competencia";
    }

    @GetMapping("/dafo")
    public String dafo() {
        return "dafo";
    }

    @GetMapping("/organigrama")
    public String organigrama() {
        return "organigrama";
    }

    @GetMapping("/publicidad")
    public String publicidad() {
        return "publicidad";
    }

    @GetMapping("/inversion_coste")
    public String inversion_coste() {
        return "inversion_coste";
    }

    @GetMapping("/contabilidad_ratios_1")
    public String contabilidad_ratios_1() {
        return "contabilidad_ratios_1";
    }

    @GetMapping("/contabilidad_ratios_2")
    public String contabilidad_ratios_2() {
        return "contabilidad_ratios_2";
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
