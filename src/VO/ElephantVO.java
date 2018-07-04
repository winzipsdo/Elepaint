package VO;

/**
 * Created by neal on 2017/11/7.
 */
public class ElephantVO {
    String filename;
    String filepath;
    String elephantdate;
    String country;
    String size;

    public ElephantVO() {
    }

    public ElephantVO(String filename, String filepath, String elephantdate, String country, String size) {
        this.filename = filename;
        this.filepath = filepath;
        this.elephantdate = elephantdate;
        this.country = country;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getElephantdate() {
        return elephantdate;
    }

    public void setElephantdate(String elephantdate) {
        this.elephantdate = elephantdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
