package VO;

/**
 * Created by neal on 2017/11/6.
 */
public class SourceVO {
    String fileName;
    String filePath;
    String date;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SourceVO() {
    }

    public SourceVO(String fileName, String filePath, String date) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.date = date;
    }
}
