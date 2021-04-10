package ui;

import javax.sound.sampled.*;
import java.io.File;
// Represents an audio clip that plays when user enters illegal inputs

public class ErrorClip {

    private Clip line;

    //EFFECTS: Constructs an Error Clip and plays the clip.
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
                line.open(sound);
            }
        } catch (Exception ex) {
            line = null;
        }

        line.start();
    }
}
