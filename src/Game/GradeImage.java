package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GradeImage {

    private static BufferedImage background;
    private static BufferedImage game_over;
    private static BufferedImage A;
    private static BufferedImage B;
    private static BufferedImage C;
    private static BufferedImage D;
    private static BufferedImage E;
    private static BufferedImage F;
    private static BufferedImage zv1;
    private static BufferedImage zv2;
    private static float grade_left = 200;
    private static float grade_top = -100;
    private static float grade_top1 = -100;
    private static float grade_v = 10;


    public static void initializeImage() throws IOException {
        background = ImageIO.read(new File("src/Resources/fon.png"));
        A = ImageIO.read(new File("src/Resources/A.png"));
        B = ImageIO.read(new File("src/Resources/B.png"));
        C = ImageIO.read(new File("src/Resources/C.png"));
        D = ImageIO.read(new File("src/Resources/D.png"));
        E = ImageIO.read(new File("src/Resources/E.png"));
        F = ImageIO.read(new File("src/Resources/F.png"));
        zv1 = ImageIO.read(new File("src/Resources/zv1.jpg"));
        zv2 = ImageIO.read(new File("src/Resources/zv2.jpg"));
        game_over = ImageIO.read(new File("src/Resources/dopka.png"));
    }

    public static BufferedImage getBackground() {
        return background;
    }

    public static BufferedImage getGame_over() {
        return game_over;
    }

    public static BufferedImage getA() {
        return A;
    }

    public static BufferedImage getB() {
        return B;
    }

    public static BufferedImage getC() {
        return C;
    }

    public static BufferedImage getD() {
        return D;
    }

    public static BufferedImage getE() {
        return E;
    }

    public static BufferedImage getF() {
        return F;
    }

    public static BufferedImage getZv1() {
        return zv1;
    }

    public static BufferedImage getZv2() {
        return zv2;
    }

    public static float getGrade_left() {
        return grade_left;
    }

    public static void setGrade_left(float grade_left) {
        GradeImage.grade_left = grade_left;
    }

    public static float getGrade_top() {
        return grade_top;
    }

    public static void setGrade_top(float grade_top) {
        GradeImage.grade_top = grade_top;
    }

    public static float getGrade_top1() {
        return grade_top1;
    }

    public static void setGrade_top1(float grade_top1) {
        GradeImage.grade_top1 = grade_top1;
    }

    public static float getGrade_v() {
        return grade_v;
    }

    public static void setGrade_v(float grade_v) {
        GradeImage.grade_v = grade_v;
    }
}
