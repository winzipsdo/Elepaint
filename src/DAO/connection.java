package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by neal on 2017/11/6.
 */
public class connection {

    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.176.2.203:3306/merge";
//        String url = "jdbc:mysql://localhost:3306/merge";
        String username = "wpc";
        String password = "wpcmysql";//todo
//        String username = "root";
//        String password = "123";
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn) {
        // 判断conn是否为空
        if (conn != null) {
            try {
                conn.close();    // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
