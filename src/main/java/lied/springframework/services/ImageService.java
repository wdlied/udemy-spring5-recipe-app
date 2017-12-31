package lied.springframework.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

public interface ImageService {

    void saveImageFile(long recipeId, MultipartFile file);
}
