package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.Image;
import cat.tecnocampus.omega.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_USER = "INSERT INTO Users (username, password, first_name, last_name, email, birthday, visible, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private final String INSERT_ROLE = "INSERT INTO Roles (role,username) VALUES(?, ?)";
    private final String FIND_ALL = "SELECT * FROM Users";
    private final String FIND_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    private final String DELETE_USER = "UPDATE Users SET enable=false WHERE username=?";
    private final String UPDATE_USER = "UPDATE Users SET image = ? WHERE username=?";

    private final String INSERT_IMAGE = "INSERT INTO Images (image_id,image_data) VALUES (?,?)";
    private final String FIND_IMAGE_BY_ID = "SELECT * from Images WHERE image_id=?";

    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .birthday(resultSet.getDate("birthday"))
                .image(findImage(resultSet.getString("image")))
                .build();
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Image imageMapper(ResultSet resultSet) throws SQLException {
        return new Image(resultSet.getString("image_id"), resultSet.getBytes("image_data"));
    }

    private RowMapper<Image> imageMapperEager = (resultSet, i) -> {
        return imageMapper(resultSet);
    };

    public int insertUser(User user) {
        return jdbcTemplate.update(INSERT_USER, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthday(), true, user.getImage().getId());
    }

    public int insertRole(User user) {
        return jdbcTemplate.update(INSERT_ROLE, "USER", user.getUsername());
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(FIND_BY_USERNAME, new Object[]{username}, mapper);
    }

    public int deleteUser(User user) {
        return jdbcTemplate.update(DELETE_USER, user.getUsername());
    }

    public Image findImage(String id) {
        return jdbcTemplate.queryForObject(FIND_IMAGE_BY_ID, new Object[]{id}, imageMapperEager);
    }


    public int insertImage(Image image) {
        return jdbcTemplate.update(INSERT_IMAGE, image.getId(), image.getData());

    }
}
