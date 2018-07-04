package PAO;

import java.io.File;
import java.util.Calendar;

/**
 * Created by neal on 2017/11/3.
 */
public class common {
    //获取不带文件路径及其尾缀的文件名
    public static String getPureFileName(String filePath) {
        String[] pathSplit = filePath.split("/");//todo 根据平台修改斜杠
        String fileName = pathSplit[pathSplit.length - 1];
        String pureName = fileName.substring(0, fileName.length() - 4);
        return pureName;
    }

    public static void initial() {
        String upload = "/Users/neal/IdeaProjects/JsCombiner/out/artifacts/JsCombiner_war_exploded/upload";
        String storage = "/Users/neal/IdeaProjects/JsCombiner/out/artifacts/JsCombiner_war_exploded/storage";

        File file = new File(upload);
        if (file.listFiles().length != 0) {
            for (File fileTemp : file.listFiles()) {
                fileTemp.delete();
                System.out.println(fileTemp.getName()+" has been deleted");
            }
        } else {
            System.out.println(upload + " is empty");
        }
        file = new File(storage);
        if (file.listFiles().length != 0) {
            for (File fileTemp : file.listFiles()) {
                fileTemp.delete();
                System.out.println(fileTemp.getName()+" has been deleted");
            }
        } else {
            System.out.println(storage + " is empty");
        }

    }

    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int date = year * 10000 + month * 100 + day;

        return String.valueOf(date);
    }

    public static String getFullStorageName(String pureName, String country, String date, String zoomWidth, String zoomHeight,String type) {
        String full = country.toLowerCase()+"-"+pureName+"-"+date+"-"+zoomWidth+"x"+zoomHeight+"-"+type+".png";
        return full;
    }

    public static void main(String[] args) {
        String test = "1:2.2.3.3";
        String[] testArr = test.split("\\.");
        System.out.println(testArr.length);
    }
}
