package PAO;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by neal on 2017/10/22.
 */


public class pics {

    //输入文件的路径（路径直到文件本身）及宽、高，返回指定宽、高的BufferedImage
    public static BufferedImage targetZoom(String filePath, int width, int height) throws Exception {
        File background = new File(filePath);
        BufferedImage inputImage = ImageIO.read(background);

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) newImage.getGraphics();

        g.drawImage(inputImage, 0, 0, width, height, null);//observer?
        g.dispose();
        newImage.flush();

        return newImage;
    }

    //输入文件对象、宽、高，返回指定宽、高的BufferedImage
    public static BufferedImage targetZoom(File file, int width, int height) throws Exception {

        BufferedImage inputImage = ImageIO.read(file);

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) newImage.getGraphics();

        g.drawImage(inputImage, 0, 0, width, height, null);//observer?
        g.dispose();
        newImage.flush();

        return newImage;
    }

    public static BufferedImage targetZoom(BufferedImage inputImage, int width, int height) throws Exception {

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) newImage.getGraphics();

        g.drawImage(inputImage, 0, 0, width, height, null);//observer?
        g.dispose();
        newImage.flush();

        return newImage;
    }

    //输入背景图、贴纸及贴纸的位置（x、y皆为百分比，如开始于图象中央，x = 50，y = 50）
    // 输出png格式的图片到指定位置，其中outPutFilePath包括文件本身的名字
    public static void combine(BufferedImage background, BufferedImage sticker, int x, int y, String outPutFilePath) {
        int widthBot = background.getWidth();
        int heightBot = background.getHeight();

        Graphics2D g = (Graphics2D) background.getGraphics();
        g.drawImage(sticker, widthBot * x / 100, heightBot * y / 100, null);


        File outputImage = new File(outPutFilePath);
        try {
            ImageIO.write(background, "png", outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.dispose();
        background.flush();
    }

    public static BufferedImage combine(BufferedImage background, BufferedImage sticker, int x, int y) {
        int widthBot = background.getWidth();
        int heightBot = background.getHeight();

        Graphics2D g = (Graphics2D) background.getGraphics();
        g.drawImage(sticker, widthBot * x / 100, heightBot * y / 100, null);
        g.dispose();
        background.flush();

        return background;
    }


    //同上方法，将背景图与两张贴纸并在一起
    public static void combine(BufferedImage imgBottom,
                               BufferedImage sticker1, int x1, int y1,
                               BufferedImage sticker2, int x2, int y2,
                               String outPutFilePath) {
        int widthBot = imgBottom.getWidth();
        int heightBot = imgBottom.getHeight();

        Graphics2D g = (Graphics2D) imgBottom.getGraphics();
        g.drawImage(sticker1, widthBot * x1 / 100, heightBot * y1 / 100, null);
        g.drawImage(sticker2, widthBot * x2 / 100, heightBot * y2 / 100, null);

        File outputImage = new File(outPutFilePath);
        try {
            ImageIO.write(imgBottom, "png", outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.dispose();
        imgBottom.flush();
    }

    public static BufferedImage yonezawa(BufferedImage background, String pathYonezawa, int x, int y) throws Exception {

        int width = background.getWidth();
        int height = background.getHeight();

        BufferedImage yonezawa = targetZoom(pathYonezawa, 75, 75);
        Graphics2D g = (Graphics2D) background.getGraphics();
        g.drawImage(yonezawa, width * x / 100, height * y / 100, null);
        g.dispose();
        background.flush();

        return background;
    }

    public static BufferedImage sizeTag(String sizePath, String inch, String weight, String pathLato) throws Exception {
        File sizeFile = new File(sizePath);
        BufferedImage sizeImage = ImageIO.read(sizeFile);
        int width = sizeImage.getWidth();
        int height = sizeImage.getHeight();

        Graphics2D g = sizeImage.createGraphics();

        File fileFont = new File(pathLato);
//        File fileFont = new File("sticker/Lato-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT,fileFont);
        font = font.deriveFont(Font.BOLD,14);

        g.setColor(new Color(0, 0, 0));
//        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//消除锯齿
        g.drawString(inch, 17, 30);

//        g.setColor(new Color(255, 255, 255));
//        g.setFont(new Font("Arial", Font.BOLD, 11));
//        g.drawString(weight, 17, 54);

        g.setColor(new Color(255, 255, 255));
        font = font.deriveFont(Font.BOLD,12);
        g.setFont(font);
        g.drawString(weight, 14, 54);

        g.dispose();
        sizeImage.flush();

        return sizeImage;
    }

    public static BufferedImage bigSizeTag(String sizePath, String inch, String weight, String pathLato) throws Exception {
        File sizeFile = new File(sizePath);
        BufferedImage sizeImage = ImageIO.read(sizeFile);
        int width = sizeImage.getWidth();
        int height = sizeImage.getHeight();

        Graphics2D g = sizeImage.createGraphics();

        File fileFont = new File(pathLato);
//        File fileFont = new File("sticker/Lato-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT,fileFont);
        font = font.deriveFont(Font.BOLD,26);

        g.setColor(new Color(0, 0, 0));
//        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//消除锯齿
        g.drawString(inch, 30, 52);

//        g.setColor(new Color(255, 255, 255));
//        g.setFont(new Font("Arial", Font.BOLD, 11));
//        g.drawString(weight, 17, 54);

        g.setColor(new Color(255, 255, 255));
        font = font.deriveFont(Font.BOLD,20);
        g.setFont(font);
        g.drawString(weight, 29, 95);

        g.dispose();
        sizeImage.flush();

        return sizeImage;
    }

    //这是个备用函数，能返回png实体部分距离边缘的值
    public static int[] findPixel(BufferedImage background) {
        int width = background.getWidth();
        int height = background.getHeight();
        int top = -1, right = -1, bottom = -1, left = -1;

        //get top
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = background.getRGB(x, y);
                if (argb < 0 || argb > 16777215) {
                    top = y;
                    break;
                }
            }
            if (top!=-1){
                break;
            }
        }

        //get right
        for (int x = width-1; x > 0; x--) {
            for (int y = 0; y < height; y++) {
                int argb = background.getRGB(x, y);
                if (argb < 0 || argb > 16777215) {
                    right = x;
                    break;
                }
            }
            if (right!=-1){
                break;
            }
        }

        //get bottom
        for (int y = height-1; y > 0; y--) {
            for (int x = 0; x < width; x++) {
                int argb = background.getRGB(x, y);
                if (argb < 0 || argb > 16777215) {
                    bottom = y;
                    break;
                }
            }
            if (bottom!=-1){
                break;
            }
        }

        //get left
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = background.getRGB(x, y);
                if (argb < 0 || argb > 16777215) {
                    left = x;
                    break;
                }
            }
            if (left!=-1){
                break;
            }
        }

        int[] pixel = {top,right,bottom,left};
        return pixel;
    }

    //大而无用的函数
    public static BufferedImage elephantClip(BufferedImage bufferedImage,int zoomWidth,int zoomHeight) throws Exception {//400*300

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        int targetHeight = -1,targetWidth = -1;

        float ratioRaw = (float) width/height;
        float ratioTarget = (float)zoomWidth/zoomHeight;

        int excessX = 0;
        int excessY = 0;

        if (ratioRaw > ratioTarget){//以高缩
            targetHeight = zoomHeight;
            targetWidth = width*zoomHeight/height;
            excessX = targetWidth-zoomWidth;
        }else if (ratioRaw <= ratioTarget){//以宽缩
            targetWidth = zoomWidth;
            targetHeight = height*zoomWidth/width;
            excessY = targetHeight-zoomHeight;
        }

        BufferedImage newImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(bufferedImage, 0, 0, targetWidth, targetHeight, null);//observer?

        BufferedImage subImage = newImage.getSubimage(excessX/2,excessY/2,targetWidth-excessX,targetHeight-excessY);

        g.dispose();
        newImage.flush();
        return subImage;
    }

    public static BufferedImage elephantClip2(BufferedImage bufferedImage,int zoomWidth, int zoomHeight) throws IOException {

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        double zoomRatio = 1.0;

        double targetWidth = zoomWidth*zoomRatio;
        double targetHeight = height*targetWidth/width;

        Image scaled = bufferedImage.getScaledInstance((int)targetWidth,(int)targetHeight,Image.SCALE_SMOOTH);

        BufferedImage newImage = new BufferedImage(zoomWidth,zoomHeight,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D) newImage.getGraphics();
        g.drawImage(scaled, (zoomWidth-(int)targetWidth)/2,(zoomHeight-(int)targetHeight)/2,(int)targetWidth,(int)targetHeight,null);
        newImage.flush();
        g.dispose();

        return newImage;
    }

    public static void main(String[] args) throws Exception {

        String filePath = "pics/lenovo-ideapad-320-15-hero.png";
        String stickerPath = "sticker/big-size-tag.png";


        BufferedImage file = ImageIO.read(new File(filePath));
        BufferedImage sticker = ImageIO.read(new File(stickerPath));

        BufferedImage fileCliped = elephantClip2(file,400,300);
        BufferedImage sizeTag = bigSizeTag(stickerPath,"15.6\"","2.00kg","sticker/Lato-Regular.ttf");

        BufferedImage outPut = combine(fileCliped,sizeTag,3,28);

        ImageIO.write(fileCliped,"png",new File("pics/test.png"));


    }
}
