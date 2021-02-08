package main.renderer;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    private final String IMAGES_TEST_DIR = "src/tests/images";

    @Test
    public void writeImageTest() {

        new File(IMAGES_TEST_DIR).mkdirs();

        ImageWriter imageWriter = new ImageWriter("\\" + IMAGES_TEST_DIR + "\\" + "Image writer test", 500, 500, 500, 500);

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {

                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }
        imageWriter.writeToimage();
    }

        @Test
        public void writeImageTestArray() {

            new File(IMAGES_TEST_DIR).mkdirs();

            ImageWriter imageWriter = new ImageWriter("\\" + IMAGES_TEST_DIR + "\\" + "Image writer test Array", 500, 500, 500, 500);

            int[] array=new int[]{255,255,255};
            for (int i = 0; i < imageWriter.getHeight(); i++) {
                for (int j = 0; j < imageWriter.getWidth(); j++) {

                    if (i % 50 == 0 || j % 50 == 0)
                        imageWriter.writePixel(j, i, array);

                }
            }
            imageWriter.writeToimage();
    }
    @Test
    public void writeImageTestColor() {

        new File(IMAGES_TEST_DIR).mkdirs();

        ImageWriter imageWriter = new ImageWriter("\\" + IMAGES_TEST_DIR + "\\" + "Image writer test Color", 500, 500, 500, 500);

        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {

                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, Color.WHITE);

            }
        }
        imageWriter.writeToimage();
    }
}