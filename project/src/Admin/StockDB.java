package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StockDB {
    private Connection conn;
    private static final String USERNAME = "counter";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mariadb://localhost/count?useUnicode=true&characterEncoding=euckr";
    static String id,pw;
    Statement stmt;
    PreparedStatement pstmt;
    StockData sd = new StockData();
	public StockDB() {
		// TODO Auto-generated constructor stub
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
	Number SelectCur(String str){
		String sql = "select current from stock where menu = ?";
		Number cur = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				sd.setCurrent(rs.getInt("current"));
				cur = sd.getCurrent();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cur;
	}
	int SelTotal() {
		String sql = "select total from stock";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				sd.setTotal(rs.getInt("total"));
				StockT.total = sd.getTotal();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sd.getTotal();
	}
	ArrayList SelCurrent() {
		String sql = "select current from stock";
		ArrayList arr = new ArrayList();
		try {
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			StockData d = new StockData();
			d.setCurrent(rs.getInt("current"));
			arr.add(d);
		}
		}catch(SQLException e) {
		e.printStackTrace();	
		}
		return arr;
	}
	int SelMinimum() {
		String sql = "select minimum from stock";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				sd.setMinimum(rs.getInt("minimum"));
				StockT.min = sd.getMinimum();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sd.getMinimum();
	}
	void UpdateCur(String str) {
		String sql = "update stock set current = "+sd.getTotal()+" where menu = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbClose() throws SQLException {
		conn.close();
		System.out.println("연결 해제!!");
	}
}
