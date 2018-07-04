package small;

import DAO.elephantDAO;
import PAO.common;
import PAO.pics;
import VO.ElephantVO;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static DAO.elephantDAO.newElephant;

/**
 * Created by neal on 2018/4/18.
 */
public class Actions {
    public static int[] sizeDecipher(String size, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        int zoomWidth = -1;
        int zoomHeight = -1;

        switch (size) {
            case "pix1060":
                zoomWidth = 1060;
                zoomHeight = 596;
                break;
            case "pix725":
                zoomWidth = 725;
                zoomHeight = 515;
                break;
            case "pix446":
                zoomWidth = 446;
                zoomHeight = 370;
                break;
            case "pix430":
                zoomWidth = 430;
                zoomHeight = 309;
                break;
            case "pix400":
                zoomWidth = 400;
                zoomHeight = 300;
                break;
            case "pix227":
                zoomWidth = 227;
                zoomHeight = 170;
                break;
            case "pix192":
                zoomWidth = 192;
                zoomHeight = 144;
                break;
            case "pix115":
                zoomWidth = 115;
                zoomHeight = 65;
                break;
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        int[] sizeDeciphered = {zoomWidth, zoomHeight};

        return sizeDeciphered;
    }

    public static StringBuffer doYonezawa(ArrayList<String> fileList, int zoomWidth, int zoomHeight, String country, String stickerPath, String storagePath) {
        int stickerWidth,stickerHeight;
        int stickerX,stickerY;
        StringBuffer res = new StringBuffer();
        Boolean dotFlag = false;

        switch (zoomWidth){
            case 446:
                stickerWidth = 75;
                stickerHeight = 75;
                stickerX = 10;
                stickerY = 40;
                break;
            case 400:
                stickerWidth = 75;
                stickerHeight = 75;
                stickerX = 10;
                stickerY = 40;
                break;
            case 227:
                stickerWidth = 75;
                stickerHeight = 75;
                stickerX = 10;
                stickerY = 40;
                break;
            case 192:
                stickerWidth = 75;
                stickerHeight = 75;
                stickerX = 10;
                stickerY = 40;
                break;
            default:
                stickerWidth = 75;
                stickerHeight = 75;
                stickerX = 10;
                stickerY = 40;
        }

        for (String file : fileList) {
            try {
                String pureName = common.getPureFileName(file);
                String date = common.getCurrentDate();
                String fullName = common.getFullStorageName(pureName,country,date,String.valueOf(zoomWidth),String.valueOf(zoomHeight),"yonezawa");

                BufferedImage background = ImageIO.read(new File(file));
                BufferedImage cliped = pics.elephantClip2(background, zoomWidth, zoomHeight);
                BufferedImage yonezawa = pics.targetZoom(stickerPath+"/yonezawa-icon-white.png",stickerWidth,stickerHeight);//todo 需要配置yonezawa位置
                BufferedImage processed = pics.combine(cliped,yonezawa,stickerX,stickerY);

                String pureName2 = common.getPureFileName(fullName);
                if (!elephantDAO.ifElephantExist(pureName2)){
                    ElephantVO elephantVO = new ElephantVO(pureName2,storagePath + "/" + fullName,date,country,zoomWidth+"x"+zoomHeight);
                    newElephant(elephantVO);
                } else {
                    System.out.println(fullName + " has already existed");
                }
                ImageIO.write(processed, "png", new File(storagePath + "/" + fullName));//todo 需要新的米泽生产图
                if (dotFlag){
                    res.append(",");
                }
                res.append("\""+pureName2+"\":"+"\"storage/"+fullName+"\"");
                dotFlag = true;
                System.out.println(file + " finished");
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return res;
    }

    public static StringBuffer doZoom(ArrayList<String> fileList, int zoomWidth, int zoomHeight, String country, String storagePath){
        StringBuffer res = new StringBuffer();
        Boolean dotFlag = false;

        for (String file : fileList) {
            try {
                String pureName = common.getPureFileName(file);
                String date = common.getCurrentDate();
                String fullName = common.getFullStorageName(pureName,country,date,String.valueOf(zoomWidth),String.valueOf(zoomHeight),"zoom");

                BufferedImage background = ImageIO.read(new File(file));
                BufferedImage cliped = pics.elephantClip2(background, zoomWidth, zoomHeight);

                String pureName2 = common.getPureFileName(fullName);
                if (!elephantDAO.ifElephantExist(pureName2)){
                    ElephantVO elephantVO = new ElephantVO(pureName2,storagePath + "/" + fullName,date,country,zoomWidth+"x"+zoomHeight);
                    newElephant(elephantVO);
                } else {
                    System.out.println(fullName + " has already existed");
                }
                ImageIO.write(cliped, "png", new File(storagePath + "/" + fullName));//todo 需要新的米泽生产图

                if (dotFlag){
                    res.append(",");
                }
                res.append("\""+pureName2+"\":"+"\"storage/"+fullName+"\"");
                dotFlag = true;
                System.out.println(file + " finished");
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return res;
    }

    public static StringBuffer doSizeTag(ArrayList<String> fileList, int zoomWidth, int zoomHeight, String inch, String weight, String country, String stickerPath, String storagePath) {
        int stickerX,stickerY;
        StringBuffer res = new StringBuffer();
        Boolean dotFlag = false;
        switch (zoomWidth){
            case 446:
                stickerX = 14;
                stickerY = 45;
                break;
            case 400:
                stickerX = 17;
                stickerY = 40;
                break;
            case 227:
                stickerX = 12;
                stickerY = 25;
                break;
            case 192:
                stickerX = 3;
                stickerY = 24;
                break;
            default:
                stickerX = 10;
                stickerY = 40;
        }

        for (String file : fileList) {
            try {
                String pureName = common.getPureFileName(file);
                String date = common.getCurrentDate();
                String fullName = common.getFullStorageName(pureName,country,date,String.valueOf(zoomWidth),String.valueOf(zoomHeight),"sizetag");
                BufferedImage background = ImageIO.read(new File(file));
                BufferedImage cliped = pics.elephantClip2(background, zoomWidth, zoomHeight);
                BufferedImage sizeTag = pics.sizeTag(stickerPath + "/screen-size-icon.png", inch, weight,stickerPath+"/Lato-Regular.ttf");
                BufferedImage processed = pics.combine(cliped, sizeTag, stickerX, stickerY);//todo 需要调试位置

                String pureName2 = common.getPureFileName(fullName);
                if (!elephantDAO.ifElephantExist(pureName2)){
                    ElephantVO elephantVO = new ElephantVO(pureName2,storagePath + "/" + fullName,date,country,zoomWidth+"×"+zoomHeight);
                    newElephant(elephantVO);
                } else {
                    System.out.println(fullName + " has already existed");
                }
                ImageIO.write(processed, "png", new File(storagePath + "/" + fullName));//todo 需要新的tag
                if (dotFlag){
                    res.append(",");
                }
                res.append("\""+pureName2+"\":"+"\"storage/"+fullName+"\"");
                dotFlag = true;
                System.out.println(file + " finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static StringBuffer doBigSizeTag(ArrayList<String> fileList, int zoomWidth, int zoomHeight, String inch, String weight, String country, String stickerPath, String storagePath) {
        int stickerX,stickerY;
        StringBuffer res = new StringBuffer();
        Boolean dotFlag = false;
        switch (zoomWidth){
            case 446:
                stickerX = 14;
                stickerY = 45;
                break;
            case 400:
                stickerX = 3;
                stickerY = 28;
                break;
            case 227:
                stickerX = 12;
                stickerY = 25;
                break;
            case 192:
                stickerX = 3;
                stickerY = 24;
                break;
            default:
                stickerX = 10;
                stickerY = 40;
        }

        for (String file : fileList) {
            try {
                String pureName = common.getPureFileName(file);
                String date = common.getCurrentDate();
                String fullName = common.getFullStorageName(pureName,country,date,String.valueOf(zoomWidth),String.valueOf(zoomHeight),"sizetag");
                BufferedImage background = ImageIO.read(new File(file));
                BufferedImage cliped = pics.elephantClip2(background, zoomWidth, zoomHeight);
                BufferedImage sizeTag = pics.bigSizeTag(stickerPath + "/big-size-tag.png", inch, weight,stickerPath+"/Lato-Regular.ttf");
                BufferedImage processed = pics.combine(cliped, sizeTag, stickerX, stickerY);//todo 需要调试位置

                String pureName2 = common.getPureFileName(fullName);
                if (!elephantDAO.ifElephantExist(pureName2)){
                    ElephantVO elephantVO = new ElephantVO(pureName2,storagePath + "/" + fullName,date,country,zoomWidth+"×"+zoomHeight);
                    newElephant(elephantVO);
                } else {
                    System.out.println(fullName + " has already existed");
                }
                ImageIO.write(processed, "png", new File(storagePath + "/" + fullName));//todo 需要新的tag
                if (dotFlag){
                    res.append(",");
                }
                res.append("\""+pureName2+"\":"+"\"storage/"+fullName+"\"");
                dotFlag = true;
                System.out.println(file + " finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
