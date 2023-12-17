package jokerbaba24;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//
import org.jcodec.api.awt.AWTSequenceEncoder;

public class ScreenRecorder {

    private static final int FRAME_RATE = 10; // Frames per second
    private static final int RECORDING_TIME_SECONDS = 10;

    public static void main(String[] args) {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);

            AWTSequenceEncoder encoder = AWTSequenceEncoder.createSequenceEncoder(new File("path/example.mp4"), FRAME_RATE);

            Robot robot = new Robot();

            long startTime = System.currentTimeMillis();

            while ((System.currentTimeMillis() - startTime) / 1000 < RECORDING_TIME_SECONDS) {
                BufferedImage screenCapture = robot.createScreenCapture(screenRect);
                encoder.encodeImage(screenCapture);
                Thread.sleep(1000 / FRAME_RATE);
            }

            encoder.finish();
        } catch (AWTException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
