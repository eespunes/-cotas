package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.Image;
import cat.tecnocampus.omega.persistanceController.UserController;
import cat.tecnocampus.omega.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class UserWebController {

    private UserController userController;

    public UserWebController(UserController userController) {
        this.userController = userController;
    }


    @GetMapping("mainPage")
    public String mainPage() {
        return "MainPage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("allUsers")
    public String allUsers(Model model, Principal principal) {

        model.addAttribute("users", userController.findAll());
        return "InitialPage";
    }

    @GetMapping("createUser")
    public String getCreateUser(Model model) {
        model.addAttribute("createUser", new User());
        //return "RegisterUser";
        return "signup";
    }

    @PostMapping("createUser")
    public String postCreateUser(@Valid User user, @RequestParam("photo") MultipartFile photo, Errors errors, Model model, RedirectAttributes redirectAttributes) throws IOException {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "signup";
        }
        model.addAttribute("username", user.getUsername());
        user.setImage(new Image(photo.getBytes()));
        userController.addUser(user);
        redirectAttributes.addAttribute("username", user.getUsername());
        return "redirect:/users/{username}";
    }

    @GetMapping("user")
    public String showUser(Principal principal, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", principal.getName());
        return "redirect:/users/{username}";
    }

    @GetMapping("users/{username}")
    public String showUser(@PathVariable String username, Model model, Principal principal) {
        if (principal.getName().equals(username)) {
            model.addAttribute("user", userController.getUser(username));
            return "showUser";
        }


        return "/";
    }

    @GetMapping("profile")
    public String profileUser(Principal principal, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", principal.getName());
        return "redirect:/profile/users/{username}";
    }

    @GetMapping("profile/users/{username}")
    public String profileUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userController.getUser(username));
        return "showUser";
    }
}
