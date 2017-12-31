package lied.springframework.services;

import lied.springframework.domain.Recipe;
import lied.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(long recipeId, MultipartFile file) {
        log.debug("received a file");

        try {
            Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).get();

            Byte[] byteObject = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteObject[i++] = b;
            }

            recipe.setImage(byteObject);

            recipeRepository.save(recipe);

        } catch (IOException e) {
            log.error("Error occured", e);

            e.printStackTrace();
        }


    }
}
