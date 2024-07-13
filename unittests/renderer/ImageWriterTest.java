package renderer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.*;
 import static java.awt.Color.BLUE;
import static java.awt.Color.ORANGE;



/**
 * Testing ImageWriter Class
 */
public class ImageWriterTest {

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     */
    @Test
    public void testWriteToImageTest() {
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        Color Orange = new Color(225,116,14);
        Color Blue = new Color(86,123,255);

        for (int i = 0; i < 800; i++)
            for (int j = 0; j < 500; j++)
                imageWriter.writePixel(i, j, i % 50 == 0 || j % 50 == 0 ? Blue : Orange); // 16x10 grid
        imageWriter.writeToImage();
    }

    /**
     * Test method for {@link ImageWriter#writeToImage()}.
     */








}
