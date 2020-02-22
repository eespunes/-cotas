package cat.tecnocampus.omega.domain;

import org.springframework.web.multipart.MultipartFile;

public class Animal {

    private String id;
    private String name;
    private String description;
    private String type;
    private int age;
    private User owner;

    public Animal() {
    }

    public Animal(String id, String name, String description, String type, int age, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.age = age;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public User getOwner() {
        return owner;
    }
}
