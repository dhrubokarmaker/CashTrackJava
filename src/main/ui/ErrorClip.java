package ui;

import javax.sound.sampled.*;
import java.io.File;

public class ErrorClip {

    private Clip line;

    public ErrorClip() {

        try {
            String sep = System.getProperty("file.separator");
            File soundFile = new File(System.getProperty("user.dir") + sep
                    + "data" + sep + "alarm.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = sound.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                line = null;
            } else {
                line = (Clip) AudioSystem.getLine(info);
                //line.setLoopPoints(0, -1);
                line.open(sound);
            }
        } catch (Exception ex) {
            line = null;
        }

        line.start();
    }
}
