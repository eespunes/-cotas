package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_USER = "INSERT INTO Users (username, password, first_name, last_name, email, birthday,visible,image) VALUES(?, ?, ?, ?, ?, ?,?,?)";
    private final String FIND_ALL = "SELECT * FROM Users";
    private final String FIND_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    private final String DELETE_USER = "UPDATE Users SET enable=false WHERE username=?";

    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .birthday(resultSet.getDate("birthday"))
                .birthday(resultSet.getDate("birthday"))
                .build();
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertUser(User user) {//, MultipartFile photo) {
        return jdbcTemplate.update(INSERT_USER, user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthday(), true, 0);
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
}
