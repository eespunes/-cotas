package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.Animal;
import cat.tecnocampus.omega.domain.Image;
import cat.tecnocampus.omega.persistance.AnimalDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ChallengeController")
public class AnimalController {

    private final AnimalDAO animalDAO;

    public AnimalController(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    @Transactional
    public int addAnimal(Animal animal) {
        return animalDAO.insertAnimal(animal);
    }

    @Transactional
    public int addType(String type) {
        return animalDAO.insertType(type);
    }

    @Transactional
    public int addImage(Image image) {
        return animalDAO.insertImage(image);
    }

    public List<Animal> getAll() {
        return animalDAO.findAll();
    }

    public Animal getById(String id) {
        return animalDAO.findById(id);
    }

    public Image getImage(String id) {
        return animalDAO.findImage(id);
    }

    public List<String> getTypes() {
        return animalDAO.findTypes();
    }

    public List<Animal> getByType(String type) {
        return animalDAO.findByType(type);
    }

    public int setOwner(String name, String id) {
        return animalDAO.updateOwner(name, id);
    }
}
