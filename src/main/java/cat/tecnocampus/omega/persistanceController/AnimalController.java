package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.Animal;
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

    public List<Animal> getAll() {

        return animalDAO.findAll();
    }
    public Animal getById(String id){
        return animalDAO.findById(id);
    }

}
