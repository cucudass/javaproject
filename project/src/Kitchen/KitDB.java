package Kitchen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KitDB {
    private Connection conn;
    private static final String USERNAME = "counter";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mariadb://localhost/count?characterEncoding=UTF-8&serverTimezone=UTC";
    private PreparedStatement pstmt, pstmt1, pstmt2 = null;
    private Statement stmt, stmt1 = null;
    private ResultSet rs, rs2;
    private Data data;
    static int numdata;
    
	public KitDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("주방db 연결 성공!!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패!!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("클래스 적재 실패!!");
		}
	}
	public void SelNum(int no) {
		String sql = "Select ordernum from kitchen where no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Kitchen.ornum = rs.getInt("ordernum");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void ReSetNum(int no){
		String sql = "Update kitchen set no = no -1 where no > ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void Delete(int no) {
		String sql = "delete from kitchen where ordernum = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,	no);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void insert(int no) {
		String sql = "insert into kitchen(no,ordernum) values (?,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList Selmenu(int no) {
		String sql = "Select menu from kitchen where no = ?";
		ArrayList arr = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Data d = new Data();
				d.setMenu(rs.getString("menu"));
				arr.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	public void SelNo(int no) {
		String sql = "select no from kitchen where ordernum = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Kitchen.no = rs.getInt("no");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList SelAmount(int no) {
		String sql = "Select amount from kitchen where no = ?";
		ArrayList arr1 = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Data d = new Data();
				d.setAmount(rs.getInt("amount"));
				arr1.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return arr1;
	}
	// 재고관리 증감
	public ArrayList Join(int no) {
		String sql = "select a.menu from kitchen a,orders b where a.menu = b.menu and a.ordernum = ?;";
		ArrayList arr = new ArrayList();
	try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
	while(rs.next()) {
		Data d = new Data();
		d.setMenu(rs.getString("menu"));
		arr.add(d);
	}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return arr;
}
	public void Check(String object, int no) {
		data = new Data();
	String sql = "select amount from kitchen where menu = ? and ordernum = ?";
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,object);
		pstmt.setInt(2, no);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			data.setAmount(rs.getInt("amount"));
			Kitchen.count = data.getAmount();
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
}
	public void InsertAmount(String s) {
		String sql = "update orders set amount = amount+"+Kitchen.count+" where menu = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}
	}
	public void UpdateCur(String s) {
		String sql = "update stock set current = current-"+Kitchen.count+" where menu = ?;";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, s);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void UpdateSetting(String s) { //orders 테이블 no 일정하게 증가
		String sql1 = "set @count = 0;";
		String sql2 = "update "+s+" set no = @count:=@count+1;";
		try {
			pstmt=conn.prepareStatement(sql1);
			pstmt1=conn.prepareStatement(sql2);
			pstmt.executeQuery();
			pstmt1.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public void InsertFin(int no){
		String sql = "insert into finish(ordernum) values (?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void dbClose() throws SQLException {
	conn.close();
	System.out.println("연결 해제!!");
	}
}
