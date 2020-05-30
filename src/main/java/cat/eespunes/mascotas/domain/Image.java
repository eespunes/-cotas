package cat.eespunes.mascotas.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class Image {
    private String id;
    private byte[] data;

    public Image() {
    }

    public Image(String id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public Image(byte[] data) {
        id = UUID.randomUUID().toString();
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String get64Data() throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(data), "UTF-8");
    }
}

