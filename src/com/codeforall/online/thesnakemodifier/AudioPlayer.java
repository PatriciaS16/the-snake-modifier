package com.codeforall.online.thesnakemodifier;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;



public class AudioPlayer {

    private Clip backgroundMusic;
    private final Map<String, String> soundEffects;

    public AudioPlayer() {
        soundEffects = new HashMap<>();
    }

    //Prepare the background music
    public void addBackgroudMusic(String name, String filePath) {
        try (InputStream audioStream = getClass().getResourceAsStream(filePath)) {
            if (audioStream == null) {
                System.out.println("File not found: " + name);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); //create a loop for the music keeps playing
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
        //open the Background Music
        public void startBackgroundMusic() {
            if (backgroundMusic == null) {
                backgroundMusic.start();
            }
        }
        //Stop the backgroundMusic
        public void stopBackgroundMusic() {
            if (backgroundMusic != null) {
                backgroundMusic.stop();
            }
        }


 //method to add audioFile to the map
    public void addSoundEffects(String name, String filePath){
        soundEffects.put(name, filePath);
    }

    //method to play the audio by the name
    public void playSoundEffects(String name) {
        String filePath = soundEffects.get(name);
        if (filePath == null) {
            System.out.println("Audio file not found: " + name);
            return;
        }
        try {
            //get the audio as a InputStream
            InputStream audioStream = getClass().getResourceAsStream(filePath);
            if (audioStream == null) {
                System.out.println("File not found in this path" + filePath);
                return;
            }

            //convert the inputStream in an AudioInputStream
            AudioInputStream audio = AudioSystem.getAudioInputStream(audioStream);

            //get a sound clip
            Clip clip = AudioSystem.getClip();

            //add a listener for stop the audio play
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            //open the audio clip
            clip.open(audio);

            //start the clip
            clip.start();
            System.out.println("Audio started");


        } catch (UnsupportedAudioFileException e) {
            System.out.println("The audio file is not supported");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.out.println("Audio line to reproduce the file is not available");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error playing the audio file");
            e.printStackTrace();
        }
    }
    public void close() {
        if (backgroundMusic != null) {
            backgroundMusic.close();
        }
        

        }
    }


