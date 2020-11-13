package Engine;

import java.awt.*;
import java.io.*;
import javax.sound.sampled.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundsHandler {
    protected Clip clip1;
    protected String fileName;

    public SoundsHandler(String string) {
        if (string.equals("death")) {
            fileName = "src/died.wav";
            //initialize();
        }
        if (string.equals("jump")) {
            fileName = "src/Jump.wav";
            //initialize();
        }
        if (string.equals("theme")) {
            fileName = "src/Theme.wav";
            //initialize();
        }
    }

    public void initialize(){
        try {
            // Open an audio input stream.
            File f2 = new File(fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f2);

            // Get a sound clip resource.
            clip1 = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip1.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("0");
        } catch (LineUnavailableException e) {
            System.out.println("e");
            e.printStackTrace();
        }
    }

    public void startSound(int count){
        initialize();
        clip1.loop(count);
        clip1.start();
    }

    public void stopSound(){ clip1.stop();}
}