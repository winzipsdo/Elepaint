/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package small;

import DAO.elephantDAO;
import PAO.common;
import PAO.pics;
import VO.ElephantVO;
import VO.SourceVO;
import DAO.connection;
import DAO.sourceDAO;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.elephantDAO.newElephant;
import static DAO.sourceDAO.getSourceList;
import static DAO.sourceDAO.ifSourceExist;
import static DAO.sourceDAO.newSource;

/**
 * Created by neal on 2017/10/23.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "elephant", urlPatterns = "/elephant")
public class elephant extends javax.servlet.http.HttpServlet {


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        ServletContext application = this.getServletContext();
        String root = application.getRealPath("/");
        String uploadPath = root + "upload";
        String stickerPath = root + "sticker";
        String storagePath = root + "storage";

        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists() && !uploadDirectory.isDirectory()) {
            if (uploadDirectory.mkdir()) {
                System.out.println("upload created successfully");
            } else {
                System.out.println("upload created failed");
            }
        }

        File stickerDirectory = new File(stickerPath);
        if (!stickerDirectory.exists() && !stickerDirectory.isDirectory()) {
            if (stickerDirectory.mkdir()) {
                System.out.println("sticker created successfully");
            } else {
                System.out.println("sticker created failed");
            }
        }

        File storageDirectory = new File(storagePath);
        if (!storageDirectory.exists() && !storageDirectory.isDirectory()) {
            if (storageDirectory.mkdir()) {
                System.out.println("storage created successfully");
            } else {
                System.out.println("storage created failed");
            }
        }
        //create direct

        ArrayList<String> fileList = new ArrayList<String>();
        List<Part> parts = (List<Part>) request.getParts();

        int filenum = parts.size();
        System.out.println("part size : ");

        //文件上传
        for (int i = 0; i < (filenum - 5); i++) {
            //todo 需要优化判断是否上传的文件是图片
            Part part = parts.get(i);
            String fileName = part.getSubmittedFileName();
            String date = common.getCurrentDate();
            String pureName = common.getPureFileName(fileName);
            try {
                if (!ifSourceExist(pureName)){
                    part.write(uploadPath + "/" + fileName);
                    System.out.println("write " + fileName);
                    SourceVO sourceVO = new SourceVO(pureName,uploadPath + "/" + fileName,date);
                    newSource(sourceVO);
                } else {
                    part.write(uploadPath + "/" + fileName);
                    System.out.println("write " + fileName);
                    SourceVO sourceVO = new SourceVO(pureName,uploadPath + "/" + fileName,date);
                    System.out.println(pureName + " has already existed");
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            fileList.add(uploadPath + "/" + fileName);
        }

        String size = request.getParameter("pixvalue");
        String type = request.getParameter("copystyle");
        String inch = request.getParameter("screeninch");
        String weight = request.getParameter("proweight");
        String country = request.getParameter("country");
        String font;//todo

        int[] sizeDeciphered = Actions.sizeDecipher(size, request, response);
        System.out.println("width: " + sizeDeciphered[0]);
        System.out.println("height: " + sizeDeciphered[1]);
        StringBuffer responseText = new StringBuffer("{");

        //类型选择
        switch (type) {
            case "yonezawa":
                responseText.append(Actions.doYonezawa(fileList, sizeDeciphered[0], sizeDeciphered[1],country, stickerPath, storagePath));
                break;
            case "sizetag":
                responseText.append(Actions.doSizeTag(fileList, sizeDeciphered[0], sizeDeciphered[1], inch, weight, country, stickerPath, storagePath));
                break;
            case "zoom":
                responseText.append(Actions.doZoom(fileList, sizeDeciphered[0], sizeDeciphered[1],country, storagePath));
                break;
            case "big_sizetag":
                responseText.append(Actions.doBigSizeTag(fileList, sizeDeciphered[0], sizeDeciphered[1], inch, weight, country, stickerPath, storagePath));
                break;
            default:
                request.getRequestDispatcher("index").forward(request, response);
                break;
        }

        responseText.append("}");

        System.out.println(responseText);

        PrintWriter out = response.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
