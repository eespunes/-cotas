package cat.tecnocampus.omega.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {

    @NotNull(message = "username cannot be null")
    @Size(min = 4, max = 20,message = "username must be between 4 and 15 characters long")
    private String username;

    @NotNull(message = "password cannot be null")
    @Size(min = 4, max = 20,message = "password must be between 4 and 15 characters long")
    private String password;

    private String firstName;
    private String lastName;

    @NotNull(message = "email cannot be null")
    @Size(min = 4, max = 60,message = "email must be between 4 and 15 characters long")
    private String email;

    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date birthday;

    private Image image;

    public User() {}

    public User(UserBuilder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.birthday = builder.birthday;
        this.image=builder.image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String noop = password.substring(0, 5);
        if (noop.equals("{noop}"))
            this.password = password;
        else
            this.password = "{noop}" + password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private Date birthday;
        private Image image;

        public UserBuilder() {}

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder image(Image image) {
            this.image = image;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
