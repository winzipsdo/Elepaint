package DAO;

import VO.ElephantVO;
import VO.SourceVO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neal on 2017/11/7.
 */
public class elephantDAO {
    public static void newElephant(ElephantVO elephantVO) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "INSERT INTO merge.elephant(filename, filepath, elephantdate, country, size) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, elephantVO.getFilename());
            ps.setString(2, elephantVO.getFilepath());
            ps.setString(3, elephantVO.getElephantdate());
            ps.setString(4, elephantVO.getCountry());
            ps.setString(5, elephantVO.getSize());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            connection.closeConn(conn);
        }
    }

    public static List<ElephantVO> getElephantList() {
        List<ElephantVO> elephantList = new ArrayList<ElephantVO>();
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM elephant ORDER BY elephantdate DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ElephantVO elephantVO = new ElephantVO();
                elephantVO.setFilename(rs.getString("filename"));
                elephantVO.setFilepath(rs.getString("filepath"));
                elephantVO.setElephantdate(rs.getString("elephantdate"));
                elephantVO.setCountry(rs.getString("country"));
                elephantVO.setSize(rs.getString("size"));
                elephantList.add(elephantVO);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return elephantList;
    }

    public static ElephantVO getElephantByName(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM merge.elephant WHERE filename = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, filename);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ElephantVO elephantVO = new ElephantVO();
                elephantVO.setFilename(rs.getString("filename"));
                elephantVO.setFilepath(rs.getString("filepath"));
                elephantVO.setElephantdate(rs.getString("elephantdate"));
                elephantVO.setCountry(rs.getString("country"));
                elephantVO.setSize(rs.getString("size"));
                return elephantVO;
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

    public static boolean delElephantByName(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "DELETE FROM merge.elephant WHERE filename = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, filename);

            ElephantVO elephantVO = getElephantByName(filename);
            File file = new File(elephantVO.getFilepath());
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

    public static boolean ifElephantExist(String filename) throws SQLException {
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM merge.elephant WHERE filename = ?";
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

    public static List<ElephantVO> queryElephantList(String keyword) {

        String key = "%" + keyword + "%";

        List<ElephantVO> elephantList = new ArrayList<ElephantVO>();
        Connection conn = connection.getConn();
        String sql = "SELECT * FROM elephant WHERE filename LIKE ? ORDER BY elephantdate DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ElephantVO elephantVO = new ElephantVO();
                elephantVO.setFilename(rs.getString("filename"));
                elephantVO.setFilepath(rs.getString("filepath"));
                elephantVO.setElephantdate(rs.getString("elephantdate"));
                elephantVO.setCountry(rs.getString("country"));
                elephantVO.setSize(rs.getString("size"));
                elephantList.add(elephantVO);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConn(conn);
        }
        return elephantList;
    }

    public static void main(String[] args) {
    }
}
