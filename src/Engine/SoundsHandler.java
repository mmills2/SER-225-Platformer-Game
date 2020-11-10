package Engine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
//import java.util.Timer;

import javax.sound.sampled.*;
import javax.swing.*;

import Game.ScreenCoordinator;
import Level.Player;
import Level.PlayerState;
import Screens.PlayLevelScreen;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundsHandler {
    private ScreenManager screenManager;
    Timer timer;

    public SoundsHandler(String string) {
        DeathSound();

    }
    public void DeathSound()
    {      try {
        // Open an audio input stream.
        URL url = new URL("https://bigsoundbank.com/UPLOAD/wav/0477.wav");
        //URL url2 = new URL("https://bigsoundbank.com/UPLOAD/wav/0267.wav");

        File f = new File("src/Scream.wav");
        AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(f);

        // Get a sound clip resource.
        Clip clip = AudioSystem.getClip();
        //Clip clip1 = AudioSystem.getClip();
        // Open audio clip and load samples from the audio input stream.
        //clip1.open(audioIn1);
        clip.open(audioIn1);
        int count=0;
        //System.out.println(count);


        clip.loop(000);

        if (PlayLevelScreen.isDead()==true) {


            clip.start();

        }




    } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
        System.out.println("n");
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("0");
    } catch (LineUnavailableException e) {
        System.out.println("e");
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    private class Time implements LineListener{



        @Override
        public void update(LineEvent event) {
            // TODO Auto-generated method stub

        }

    }

}