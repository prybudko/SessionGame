package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame {
    private static GameWindow game_window;
    private static int i = 0;
    private static int j = 0;
    private static int w = 0;
    private static long last_frame_time;
    private static int score;
    private static int rand = 1;
    private static int dopka = 1;
    private static GameField game_field;

    public static void main(String[] args) throws IOException {
        go();
    }

    public  static void go() throws IOException{

        GradeImage.initializeImage();
        tuningWindow();

        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                float A_right = GradeImage.getGrade_left() + GradeImage.getA().getWidth(null);
                float A_bottom = GradeImage.getGrade_top() + GradeImage.getA().getHeight(null);
                float B_right = GradeImage.getGrade_left() + GradeImage.getB().getWidth(null);
                float B_bottom = GradeImage.getGrade_top() + GradeImage.getB().getHeight(null);
                float C_right = GradeImage.getGrade_left() + GradeImage.getC().getWidth(null);
                float C_bottom = GradeImage.getGrade_top() + GradeImage.getC().getHeight(null);
                float D_right = GradeImage.getGrade_left() + GradeImage.getD().getWidth(null);
                float D_bottom = GradeImage.getGrade_top() + GradeImage.getD().getHeight(null);
                float E_right = GradeImage.getGrade_left() + GradeImage.getE().getWidth(null);
                float E_bottom = GradeImage.getGrade_top() + GradeImage.getE().getHeight(null);
                float F_right = GradeImage.getGrade_left() + GradeImage.getF().getWidth(null);
                float F_bottom = GradeImage.getGrade_top() + GradeImage.getF().getHeight(null);

                boolean is_grade = x >= GradeImage.getGrade_left() && x<= A_right && y >= GradeImage.getGrade_top() && y <= A_bottom &&
                        x >= GradeImage.getGrade_left() && x<= B_right && y >= GradeImage.getGrade_top() && y <= B_bottom &&
                        x >= GradeImage.getGrade_left() && x<= C_right && y >= GradeImage.getGrade_top() && y <= C_bottom &&
                        x >= GradeImage.getGrade_left() && x<= D_right && y >= GradeImage.getGrade_top() && y <= D_bottom &&
                        x >= GradeImage.getGrade_left() && x<= E_right && y >= GradeImage.getGrade_top() && y <= E_bottom &&
                        x >= GradeImage.getGrade_left() && x<= F_right && y >= GradeImage.getGrade_top() && y <= F_bottom;
                if (is_grade && rand == 5){
                    dopka = 2;
                    GradeImage.setGrade_left(-1000);

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

        GradeImage.setGrade_top(GradeImage.getGrade_top() + GradeImage.getGrade_v() * delta);
        GradeImage.setGrade_top1(GradeImage.getGrade_top1() + GradeImage.getGrade_v() * delta);

        g.drawImage (GradeImage.getBackground(),0,0,null);
        g.drawImage (GradeImage.getZv1(), 0, 0, null);

        if (dopka == 2) {
            i = i + 1;
            if (i == 2) {
                Music.run();
            }
            g.drawImage (GradeImage.getGame_over(),170,50,null);
            return;
        }

        gradesPosition(g);

        if (GradeImage.getGrade_v() >= 40 && i != 2 && j != 1) g.drawImage (GradeImage.getZv2(), 0, 0, null);
        if (GradeImage.getGrade_v() >= 40 && i != 2 && j == 1) g.drawImage (GradeImage.getZv1(), 0, 0, null);
        if (GradeImage.getGrade_top1() > game_window.getHeight() && rand == 5 ) {
             firstPosition();
         } else if (GradeImage.getGrade_top() > game_window.getHeight() ){
             i = i + 1;
             if (i == 2 && GradeImage.getGrade_v() >= 10+30 && j == 0) {
                 GradeImage.setGrade_top(-100);
                 GradeImage.setGrade_top1(-100);
                 GradeImage.setGrade_left((int) (Math.random() * (game_field.getWidth() - GradeImage.getA().getWidth(null))));
                 GradeImage.setGrade_v(GradeImage.getGrade_v() + 1);
                 score++;
                 rand = (int) (Math.random()*6);
                 game_window.setTitle("Score: " + score);
                 j++;
                 g.drawImage (GradeImage.getZv1(), 0, 0, null);
             }
             else {
                 g.drawImage(GradeImage.getGame_over(), 170, 50, null);
                 w = w + 1;
                 g.drawImage(GradeImage.getGame_over(), 170, 50, null);
                 if (w == 3) {
                     g.drawImage(GradeImage.getGame_over(), 170, 50, null);
                     Music.run();
                 }
             }
         }
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
        GradeImage.setGrade_top(-100);
        GradeImage.setGrade_top1(-100);
        GradeImage.setGrade_left((int) (Math.random() * (game_field.getWidth() - GradeImage.getA().getWidth(null))));
        GradeImage.setGrade_v(GradeImage.getGrade_v() + 1);
        score++;
        rand = (int) (Math.random()*6);
        game_window.setTitle("Score: " + score);
    }

    private static void gradesPosition(Graphics g) {
        if (rand == 0) {
            g.drawImage(GradeImage.getA(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
        }else if (rand == 1) {
            g.drawImage(GradeImage.getB(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
        }else if (rand == 2) {
            g.drawImage(GradeImage.getC(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
        }else if (rand == 3) {
            g.drawImage(GradeImage.getD(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
        }else if (rand == 4) {
            g.drawImage(GradeImage.getE(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
        }else if (rand == 5) {
            g.drawImage(GradeImage.getF(), (int) GradeImage.getGrade_left(), (int) GradeImage.getGrade_top(), null);
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