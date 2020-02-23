package cat.tecnocampus.omega.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class Animal {

    private String id;
    private String name;
    private String description;
    private String type;
    private int age;
    private User owner;
    private Image image;

    public Animal() {
        id = UUID.randomUUID().toString();
        owner=null;
    }

    public Animal(String id, String name, String description, String type, int age, User owner,Image image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.age = age;
        this.owner = owner;
        this.image=image;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
