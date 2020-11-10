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
import Screens.LevelClearedScreen;
import Screens.PlayLevelScreen;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundsHandler {
    private ScreenManager screenManager;
    Timer timer;

    public SoundsHandler(String string) {
        if (string.equals("dead")) {
            DeathSound();
        }
        if (string.equals("jump")) {
            JumpSound();
        }
        if (string.equals("theme")) {
            ThemeSound();
        }

    }
    public SoundsHandler(){
        JumpSound();
    }
    public void DeathSound()
    {      try {
        // Open an audio input stream.

        File f = new File("src/died.wav");

        AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(f);


        // Get a sound clip resource.

        Clip clip = AudioSystem.getClip();


        // Open audio clip and load samples from the audio input stream.

        clip.open(audioIn1);


        //System.out.println(count);


        clip.loop(000);




            clip.start();







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
    public void JumpSound(){
        int count=0;
        try {
        File f2 = new File("src/Jump.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f2);

        Clip clip1 = AudioSystem.getClip();
            clip1.open(audioIn);

            clip1.loop(000);

                clip1.start();






        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("0");
        } catch (LineUnavailableException e) {
            System.out.println("e");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void ThemeSound(){
        int count=0;
        try {
            File f2 = new File("src/Theme.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f2);

            Clip clip1 = AudioSystem.getClip();
            clip1.open(audioIn);

    clip1.loop(100);

    clip1.start();

    if (LevelClearedScreen.isCleared()==true){
        clip1.stop();
    }





        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
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