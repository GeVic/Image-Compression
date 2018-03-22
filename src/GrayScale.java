
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GrayScale {

    BufferedImage image;
    BufferedImage gray_image;
    int[] freq = new int[256];
    int width;
    int height;

    /**
     * It get the gray scale of the image
     */
    public GrayScale() {

        try {
            String input_img_path = "digital_image_processing.jpg"; //Input image here
            String gray_img_path = "grayscale.jpg";
            File input = new File(input_img_path);
            // Reading image
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            // RGB to Gray scale conversion
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color newColor = new Color(red + green + blue,
                            red + green + blue, red + green + blue);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            // Writing converted image to file
            File ouptut = new File(gray_img_path);
            ImageIO.write(image, "jpg", ouptut);

            // Original image
            JFrame frame = new JFrame();
            ImageIcon icon = new ImageIcon(input_img_path);
            JLabel label = new JLabel(icon);
            frame.add(label);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            // Converted image
            JFrame frame1 = new JFrame();
            ImageIcon icon1 = new ImageIcon(gray_img_path);
            JLabel label1 = new JLabel(icon1);
            frame1.add(label1);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.pack();
            frame1.setVisible(true);

            // Grayscale image manipulation
            gray_image = ImageIO.read(new File(gray_img_path));
            width = gray_image.getWidth();
            height = gray_image.getHeight();
            Raster raster = gray_image.getData();
            int[][] img_arr = new int[width][height];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    img_arr[j][i] = raster.getSample(j, i, 0);
                }
            }
            // Image details
            System.out.println("Image Height :" + height);
            System.out.println("Image Width :" + width);

            // to calculate the frequency
            freq(img_arr);

            String path = "Constructed_from_matrix.jpg";
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color newColor = new Color(img_arr[j][i], img_arr[j][i], img_arr[j][i]);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            // Writing converted image to file
            File ouptut2 = new File(path);
            ImageIO.write(image, "jpg", ouptut2);

        } catch (Exception e) {
        }
    }

    /**
     * here frequency of the element of the array is calculated
     *
     * @param img_arr 2d array of the jpeg
     */
    public int[] freq(int[][] img_arr) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                freq[img_arr[j][i]] += 1;
            }
        }
        return freq;
    }

}
