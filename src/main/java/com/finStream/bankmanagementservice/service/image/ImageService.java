package com.finStream.bankmanagementservice.service.image;

import com.finStream.bankmanagementservice.entity.Image;
import com.finStream.bankmanagementservice.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {


    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    public List<Image> list(){
        return imageRepository.findByOrderById();
    }

    public Image getOne(int id){
        return imageRepository.findById(id).orElse(new Image());
    }

    public Image save(Image image){
        return imageRepository.save(image);
    }

    public void delete(int id){
        imageRepository.deleteById(id);
    }

    public boolean exists(int id){
        return imageRepository.existsById(id);
    }

    public Image uploadAndSaveImage(MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            throw new IllegalArgumentException("Invalid image!");
        }

        Map<String, Object> result = cloudinaryService.upload(multipartFile);
        Image image = new Image(
                (String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id")
        );

        return this.save(image);
    }
}
