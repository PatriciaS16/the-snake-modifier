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

    private final Map<String, String> audioFiles;

    public AudioPlayer() {
        audioFiles = new HashMap<>();
    }

 //method to add audioFile to the map
    public void addAudio(String name, String filePath){
        audioFiles.put(name, filePath);
    }

    //method to play the audio by the name
    public void playAudio(String name){
        String filePath = audioFiles.get(name);
        if(filePath == null){
            System.out.println("Audio file not found: " + name);
        return;
        }
        try{
            //get the audio as a InputStream
            InputStream audioStream = getClass().getResourceAsStream(filePath);
            if(audioStream == null) {
                System.out.println("File not found in this path" + filePath);
                return;
            }

            //convert the inputStream in an AudioInputStream
            AudioInputStream audio = AudioSystem.getAudioInputStream(audioStream);

            //get a sound clip
            Clip clip =AudioSystem.getClip();

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

            clip.drain();

            }catch (UnsupportedAudioFileException e){
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
    }


