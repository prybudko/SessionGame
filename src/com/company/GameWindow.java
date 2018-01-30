package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame {

    private static GameWindow game_window;
    private static Image background;
    private static Image game_over;
    private static Image A;
    private static Image B;
    private static Image C;
    private static Image D;
    private static Image E;
    private static Image F;
    private static Image zv1;
    private static Image zv2;
    private static float grade_left = 200;
    private static float grade_top = -100;
    private static float grade_top1 = -100;
    private static float gleft = 170;
    private static float gtop = 50;
    private static int i = 0;
    private static int j = 0;
    private static int w = 0;
    private static long last_frame_time;
    private static float grade_v = 10;
    private static int score;
    private static int rand = 1;
    private static int dopka = 1;
    private static GameField game_field;

    public static void main(String[] args) throws IOException {
        go();
    }

    public  static void go() throws IOException{

        initializeImage();
        tuningWindow();

        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                float A_right = grade_left +A.getWidth(null);
                float A_bottom = grade_top +A.getHeight(null);
                float B_right = grade_left +B.getWidth(null);
                float B_bottom = grade_top +B.getHeight(null);
                float C_right = grade_left +C.getWidth(null);
                float C_bottom = grade_top +C.getHeight(null);
                float D_right = grade_left +D.getWidth(null);
                float D_bottom = grade_top +D.getHeight(null);
                float E_right = grade_left +E.getWidth(null);
                float E_bottom = grade_top +E.getHeight(null);
                float F_right = grade_left +F.getWidth(null);
                float F_bottom = grade_top +F.getHeight(null);

                boolean is_grade = x >= grade_left && x<= A_right && y >= grade_top && y <= A_bottom &&
                        x >= grade_left && x<= B_right && y >= grade_top && y <= B_bottom &&
                        x >= grade_left && x<= C_right && y >= grade_top && y <= C_bottom &&
                        x >= grade_left && x<= D_right && y >= grade_top && y <= D_bottom &&
                        x >= grade_left && x<= E_right && y >= grade_top && y <= E_bottom &&
                        x >= grade_left && x<= F_right && y >= grade_top && y <= F_bottom;
                if (is_grade && rand == 5){
                    dopka = 2;
                    grade_left = -1000;

                } else if (is_grade){
                    firstPosition();
                }
            }
        });
        game_window.add(game_field);
        game_window.setVisible(true);
    }

    private static void onRepaid (Graphics g) throws IOException{
        long current_time = System.nanoTime();
        float delta = (current_time-last_frame_time)*0.000000009f;
        last_frame_time = current_time;

        grade_top = grade_top + grade_v *delta;
        grade_top1 = grade_top1 + grade_v *delta;

        g.drawImage (background,0,0,null);
        g.drawImage (zv1, 0, 0, null);

        if (dopka == 2) {
            i = i + 1;
            if (i == 2) {
                Music.run(g);
            }
            g.drawImage (game_over,170,50,null);
            return;
        }

        gradesPosition(g);

        if (grade_v >= 40 && i != 2 && j != 1) g.drawImage (zv2, 0, 0, null);
        if (grade_v >= 40 && i != 2 && j == 1) g.drawImage (zv1, 0, 0, null);
        if (grade_top1 > game_window.getHeight() && rand == 5 ) {
             firstPosition();
         } else if (grade_top > game_window.getHeight() ){
             i = i + 1;
             if (i == 2 && grade_v >= 10+30 && j == 0) {
                 grade_top = -100;
                 grade_top1 = -100;
                 grade_left = (int) (Math.random() * (game_field.getWidth() - A.getWidth(null)));
                 grade_v = grade_v + 1;
                 score++;
                 rand = (int) (Math.random()*6);
                 game_window.setTitle("Score: " + score);
                 j++;
                 g.drawImage (zv1, 0, 0, null);
             }
             else {
                 g.drawImage(game_over, 170, 50, null);
                 w = w + 1;
                 g.drawImage(game_over, 170, 50, null);
                 if (w == 3) {
                     g.drawImage(game_over, 170, 50, null);
                     Music.run(g);
                 }
             }
         }
    }

    private static void initializeImage() throws IOException{
        background = ImageIO.read(GameWindow.class.getResourceAsStream("fon.png"));
        A = ImageIO.read(GameWindow.class.getResourceAsStream("A.png"));
        B = ImageIO.read(GameWindow.class.getResourceAsStream("B.png"));
        C = ImageIO.read(GameWindow.class.getResourceAsStream("C.png"));
        D = ImageIO.read(GameWindow.class.getResourceAsStream("D.png"));
        E = ImageIO.read(GameWindow.class.getResourceAsStream("E.png"));
        F = ImageIO.read(GameWindow.class.getResourceAsStream("F.png"));
        zv1 = ImageIO.read(GameWindow.class.getResourceAsStream("zv1.jpg"));
        zv2 = ImageIO.read(GameWindow.class.getResourceAsStream("zv2.jpg"));
        game_over = ImageIO.read(GameWindow.class.getResourceAsStream("dopka.png"));
    }

    private static void tuningWindow() {
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 100);
        game_window.setSize(760,600);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        game_field = new GameField();
    }

    private static void firstPosition(){
        grade_top = -100;
        grade_top1 = -100;
        grade_left = (int) (Math.random() * (game_field.getWidth() - A.getWidth(null)));
        grade_v = grade_v + 1;
        score++;
        rand = (int) (Math.random()*6);
        game_window.setTitle("Score: " + score);
    }

    private static void gradesPosition(Graphics g) {
        if (rand == 0) {
            g.drawImage(A, (int) grade_left, (int) grade_top, null);
        }else if (rand == 1) {
            g.drawImage(B, (int) grade_left, (int) grade_top, null);
        }else if (rand == 2) {
            g.drawImage(C, (int) grade_left, (int) grade_top, null);
        }else if (rand == 3) {
            g.drawImage(D, (int) grade_left, (int) grade_top, null);
        }else if (rand == 4) {
            g.drawImage(E, (int) grade_left, (int) grade_top, null);
        }else if (rand == 5) {
            g.drawImage(F, (int) grade_left, (int) grade_top1, null);
        }
    }

    public static class GameField extends JPanel{

        @Override
        protected  void paintComponent (Graphics g){
            super.paintComponent(g);
            try {
                onRepaid(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();

        }
    }
 }