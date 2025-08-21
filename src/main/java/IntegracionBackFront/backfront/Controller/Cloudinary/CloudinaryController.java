package IntegracionBackFront.backfront.Controller.Cloudinary;

import IntegracionBackFront.backfront.Services.Cloudinary.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/image")
public class CloudinaryController {
    @Autowired
    private final CloudinaryService service;

    public CloudinaryController(CloudinaryService service) {
        this.service = service;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("Image")MultipartFile file){
        try {

            String imageUrl = service.uploadImage(file);
            return ResponseEntity.ok(Map.of(
                "message", "Imagen subida exitosamente",
                "url", imageUrl
            ));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("error al subir la imagen");
        }
    }
    @PostMapping("/upload-to-folder")
    public ResponseEntity<?> uploadImageToFolder(
            @RequestParam("image") MultipartFile file,
            @RequestParam String folder
    ){
        try {
            String imageURL = service.uploadImage(file, folder);
            return  ResponseEntity.ok(Map.of(
                    "message", "imagen subida correctamente",
                    "url", imageURL
            ));
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body("Error al subir la imagen");
        }
    }
}
