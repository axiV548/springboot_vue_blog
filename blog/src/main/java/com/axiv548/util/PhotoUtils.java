package com.axiv548.util;


import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.jpeg.JpegDirectory;
import com.drew.metadata.png.PngDirectory;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * PhotoUtils
 *
 * @date 2021/5/5 17:32
 */
public class PhotoUtils {

    private static File jpegFile = new File("C:/java/springboot_vue/blog/src/main/resources/static/DSC_3277.JPG");

    @Value("${img.dir}")
    private static String realPath;


    public static Map<String, Object> photoInformation(MultipartFile photo) {

        Metadata metadata;
        SimpleDateFormat dateInput = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        Map<String, Object> photoInformation = new HashMap<>();
        try {
            metadata = ImageMetadataReader.readMetadata(photo.getInputStream());
            BufferedImage bufferedImage = ImageIO.read(jpegFile);
            for (Directory directory : metadata.getDirectories()) {

                if ("ExifSubIFDDirectory".equalsIgnoreCase(directory.getClass().getSimpleName())) {
                    photoInformation.put("img_width", directory.getString(ExifDirectoryBase.TAG_EXIF_IMAGE_WIDTH));
                    photoInformation.put("img_height", directory.getString(ExifDirectoryBase.TAG_EXIF_IMAGE_HEIGHT));

                    if (directory.getString(ExifDirectoryBase.TAG_DATETIME_ORIGINAL) != null) {
                        photoInformation.put("img_filming_time", dateInput.parse(directory.getString(ExifDirectoryBase.TAG_DATETIME_ORIGINAL)));
                    } else {
                        photoInformation.put("img_filming_time", null);
                    }

                } else if ("JpegDirectory".equalsIgnoreCase(directory.getClass().getSimpleName())) {
                    photoInformation.put("img_width", directory.getString(JpegDirectory.TAG_IMAGE_WIDTH));
                    photoInformation.put("img_height", directory.getString(JpegDirectory.TAG_IMAGE_HEIGHT));
                    photoInformation.put("img_filming_time", null);

                } else if ("PngDirectory".equalsIgnoreCase(directory.getClass().getSimpleName())) {
                    photoInformation.put("img_width", directory.getString(PngDirectory.TAG_IMAGE_WIDTH));
                    photoInformation.put("img_height", directory.getString(PngDirectory.TAG_IMAGE_HEIGHT));
                    photoInformation.put("img_filming_time", null);

                } else {
                    photoInformation.put("img_width", bufferedImage.getWidth());
                    photoInformation.put("img_height", bufferedImage.getHeight());
                    photoInformation.put("img_filming_time", null);
                }

            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return photoInformation;
    }


    public static Map<String, Object> photoSave(String imgPath, MultipartFile photo) throws IOException {

        //            获取图片信息
        Map<String, Object> photoInfo = photoInformation(photo);

        Double photoWidth = Double.parseDouble(photoInfo.get("img_width").toString());
        Double photoScale = 1920/photoWidth;

        String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());

        photoInfo.put("newFileName", newFileName);

//            压缩保存到本地文件
        Thumbnails.of(photo.getInputStream())
                .scale(photoScale)
                .toFile(imgPath + newFileName);

        return photoInfo;

    }

//
//    public static void main(String[] args) throws IOException {
////        System.out.println((Date) photoInformation().get("filming_time"));
//        BufferedImage bufferedImage = ImageIO.read(jpegFile);
//
////        System.out.println("img_filming_time: " + bufferedImage.get());
//        System.out.println("Height: " + bufferedImage.getHeight());
//    }
}


