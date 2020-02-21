package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.Animal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnimalDAO {

    private JdbcTemplate jdbcTemplate;
    private UserDAO userDAO;
    private final String FIND_ALL = "SELECT * FROM Animals";
    private final String FIND_BY_ID = "SELECT * FROM Animals WHERE animal_id = ?";
    private final String INSERT_ANIMAL = "INSERT INTO Animals (animal_ID, animal_name,animal_description, animal_type, animal_age, animal_owner) VALUES(?, ?, ?, ?, ?,?)";
    private final String FIND_BY_TYPE = "SELECT * FROM Animals WHERE animal_type=?";


    public AnimalDAO(JdbcTemplate jdbcTemplate, UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO = userDAO;
    }

    private Animal animalMapper(ResultSet resultSet) throws SQLException {
        Animal animal = new Animal(resultSet.getString("animal_ID"), resultSet.getString("animal_name"), resultSet.getString("animal_description"), resultSet.getString("animal_type"), resultSet.getInt("animal_age"), userDAO.findByUsername(resultSet.getString("animal_owner")));
        return animal;
    }

    public List<Animal> findByType(String animal) {
        return jdbcTemplate.query(FIND_BY_TYPE, new Object[]{animal, "Animal"}, mapperEager);
    }

    private RowMapper<Animal> mapperEager = (resultSet, i) -> {
        return animalMapper(resultSet);
    };

    public List<Animal> findAll() {
        return jdbcTemplate.query(FIND_ALL, new Object[]{"Animal"}, mapperEager);
    }

    public Animal findById(String id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id, "Animal"}, mapperEager);
    }

    public int insertAnimal(Animal animal) {
        return jdbcTemplate.update(INSERT_ANIMAL, animal.getId(), animal.getName(), animal.getDescription(), animal.getType(), animal.getAge(), animal.getOwner());
    }
}
