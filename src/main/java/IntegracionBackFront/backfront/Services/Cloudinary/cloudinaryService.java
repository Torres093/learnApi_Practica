package IntegracionBackFront.backfront.Services.Cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class cloudinaryService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png"};

    private final Cloudinary cloudinary;

    public cloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public String uploadImage(MultipartFile file)throws IOException {
        validateImage(file);

    }

    private void validateImage(MultipartFile file) {
        if (file.isEmpty()){
            throw new IllegalArgumentException("El archivo esta vacío.");
        }
        if (file.getSize() > MAX_FILE_SIZE){
            throw new IllegalArgumentException("El archivo es demasiado grande");
        }
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IllegalArgumentException("Nombre invalido");
        }

        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
        if (!Arrays.asList(ALLOWED_EXTENSIONS).contains(extension)){
            throw new IllegalArgumentException("Solo se perminten X archivos");
        }

        if (!file.getSize())
    }
}
