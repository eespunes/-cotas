package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.Animal;
import cat.tecnocampus.omega.domain.Image;
import cat.tecnocampus.omega.domain.User;
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
    private final String INSERT_ANIMAL = "INSERT INTO Animals (animal_ID, animal_name,animal_description, animal_type, animal_age, animal_owner,image,visible) VALUES(?, ?, ?, ?, ?,?,?,?)";
    private final String FIND_BY_TYPE = "SELECT * FROM Animals WHERE animal_type=?";
    private final String INSERT_TYPE = "INSERT INTO AnimalTypes (type_name) VALUES (?)";
    private final String FIND_TYPES = "SELECT * from AnimalTypes";

    private final String INSERT_IMAGE = "INSERT INTO Images (image_id,image_data) VALUES (?,?)";
    private final String FIND_IMAGE_BY_ID = "SELECT * from Images WHERE image_id=?";

    private final String UPDATE_ANIMAL = "UPDATE Animals SET animal_owner = ? WHERE animal_ID=?";


    public AnimalDAO(JdbcTemplate jdbcTemplate, UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO = userDAO;
    }

    private Animal animalMapper(ResultSet resultSet) throws SQLException {
        User owner = null;
        if (resultSet.getString("animal_owner") != null)
            owner = userDAO.findByUsername(resultSet.getString("animal_owner"));

        Animal animal = new Animal(resultSet.getString("animal_id"), resultSet.getString("animal_name"), resultSet.getString("animal_description"), resultSet.getString("animal_type"), resultSet.getInt("animal_age"), owner, findImage(resultSet.getString("image")));
        return animal;
    }

    private String typeMapper(ResultSet resultSet) throws SQLException {
        return resultSet.getString("type_name");
    }

    private RowMapper<String> typeMapperEager = (resultSet, i) -> {
        return typeMapper(resultSet);
    };

    private Image imageMapper(ResultSet resultSet) throws SQLException {
        return new Image(resultSet.getString("image_id"), resultSet.getBytes("image_data"));
    }

    private RowMapper<Image> imageMapperEager = (resultSet, i) -> {
        return imageMapper(resultSet);
    };


    public List<Animal> findByType(String animal) {
        return jdbcTemplate.query(FIND_BY_TYPE, new Object[]{animal}, mapperEager);
    }

    private RowMapper<Animal> mapperEager = (resultSet, i) -> {
        return animalMapper(resultSet);
    };

    public List<Animal> findAll() {
        return jdbcTemplate.query(FIND_ALL, new Object[]{}, mapperEager);
    }

    public Animal findById(String id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id}, mapperEager);
    }

    public int insertAnimal(Animal animal) {
        return jdbcTemplate.update(INSERT_ANIMAL, animal.getId(), animal.getName(), animal.getDescription(), animal.getType(), animal.getAge(), animal.getOwner(), animal.getImage().getId(), true);
    }

    public List<String> findTypes() {
        return jdbcTemplate.query(FIND_TYPES, new Object[]{}, typeMapperEager);
    }


    public int insertType(String type) {
        if (!findTypes().contains(type))
            return jdbcTemplate.update(INSERT_TYPE, type);
        else return 1;

    }

    public Image findImage(String id) {
        return jdbcTemplate.queryForObject(FIND_IMAGE_BY_ID, new Object[]{id}, imageMapperEager);
    }


    public int insertImage(Image image) {
        return jdbcTemplate.update(INSERT_IMAGE, image.getId(), image.getData());

    }

    public int updateOwner(String name, String id) {
        return jdbcTemplate.update(UPDATE_ANIMAL, name, id);
    }
}
