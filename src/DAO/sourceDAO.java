package DAO;

import VO.SourceVO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neal on 2017/11/6.
 */
public class sourceDAO {
    public static void newSource(SourceVO sourceVO) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "INSERT INTO merge.source(filename, filepath, loaddate) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sourceVO.getFileName());
            ps.setString(2, sourceVO.getFilePath());
            ps.setString(3, sourceVO.getDate());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            connection.closeConn(conn);
        }
    }

    public static List<SourceVO> getSourceList() {
        List<SourceVO> fileList = new ArrayList<SourceVO>();
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM source ORDER BY loaddate DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SourceVO sourceVO = new SourceVO();
                sourceVO.setFileName(rs.getString("filename"));
                sourceVO.setFilePath(rs.getString("filepath"));
                sourceVO.setDate(rs.getString("loaddate"));
                fileList.add(sourceVO);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return fileList;
    }

    public static boolean delSourceByName(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "DELETE FROM merge.source WHERE filename = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, filename);

            SourceVO sourceVO = getSourceByName(filename);
            File file = new File(sourceVO.getFilePath());
            if (file.delete()){
                System.out.println(filename + " has been deleted");
            } else {
                System.out.println(filename + " delete failed");
            }
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        } finally {
            connection.closeConn(conn);
        }
    }

    public static SourceVO getSourceByName(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM merge.source WHERE filename = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, filename);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SourceVO sourceVO = new SourceVO();
                sourceVO.setFileName(rs.getString("filename"));
                sourceVO.setFilePath(rs.getString("filepath"));
                sourceVO.setDate(rs.getString("loaddate"));
                return sourceVO;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return null;
    }

    public static boolean ifSourceExist(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM merge.source WHERE filename = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, filename);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return false;
    }

    public static List<SourceVO> querySourceList(String keyword) {

        String key = "%" + keyword + "%";

        List<SourceVO> fileList = new ArrayList<SourceVO>();
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM source WHERE filename LIKE ? ORDER BY loaddate DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SourceVO sourceVO = new SourceVO();
                sourceVO.setFileName(rs.getString("filename"));
                sourceVO.setFilePath(rs.getString("filepath"));
                sourceVO.setDate(rs.getString("loaddate"));
                fileList.add(sourceVO);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return fileList;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(ifSourceExist("01_Thinkpad_T470p_14inch_Hero_Shot_Front_forward_facing_Web_browser_screen 17-03-08-777.png"));
    }
}
