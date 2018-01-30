package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Asus on 18.01.2017.
 */
public class Music {

        public static void run(Graphics graphics){
            try {
                FileInputStream f = new FileInputStream("sound.mp3");
                try {
                    Player player = new Player(f);
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

