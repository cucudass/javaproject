package elecboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BoardDB {
	 private Connection conn;
	    private static final String USERNAME = "counter";
	    private static final String PASSWORD = "12345";
	    private static final String URL = "jdbc:mysql://localhost/count?useUnicode=true&characterEncoding=euckr";
	    private PreparedStatement pstmt, pstmt1, pstmt2 = null;
	    private Statement stmt, stmt1 = null;
	    private ResultSet rs, rs2;
		Data d;
		public BoardDB() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("연결 실패!!");
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("클래스 적재 실패!!");
			}
		}
		public ArrayList Selnum() {
			String sql = "select distinct ordernum from kitchen";
			ArrayList ar = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					d= new Data();
					d.setNo(rs.getInt("ordernum"));
					ar.add(d);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return ar;
		}
		public ArrayList Finish() {
			String sql = "select distinct ordernum from finish";
			ArrayList ar = new ArrayList();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					d = new Data();
					d.setNo(rs.getInt("ordernum"));
					ar.add(d);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return ar;
		}
}
