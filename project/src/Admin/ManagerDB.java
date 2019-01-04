package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ManagerDB {
    private Connection conn;
    private static final String USERNAME = "counter";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mariadb://localhost/count?useUnicode=true&characterEncoding=euckr";
    static String id,pw;
    Login log;
    Statement stmt;
    Admin ad;
    Data d;
	public ManagerDB() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("연결 성공!!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패!!");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("클래스 적재 실패!!");
		}
	}

	void InsertDay() {					// 일일결산 저장
		String sql = "insert into dsale(day,dprice,amount) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ad.cal.get(Calendar.DAY_OF_MONTH));
			pstmt.setInt(2, Integer.parseInt(ad.sum));
			pstmt.setInt(3, Integer.parseInt(ad.amountsum));
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void InsertMonth() {					// 일일결산 저장
		String sql = "insert into msale(month,mprice,amount) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (ad.cal.get(Calendar.MONTH)+1));
			pstmt.setInt(2, Integer.parseInt(ad.sum));
			pstmt.setInt(3, Integer.parseInt(ad.amountsum));
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void InsertYear() {					// 일일결산 저장
		String sql = "insert into ysale(year,yprice,amount) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ad.cal.get(Calendar.YEAR));
			pstmt.setInt(2, Integer.parseInt(ad.sum));
			pstmt.setInt(3, Integer.parseInt(ad.amountsum));
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	AdminData LoginCheck() { 		//로그인
		AdminData s = new AdminData();
		int t = 0;
		id = log.Idtext.getText().trim();
		pw = log.Pwtext.getText().trim();
		String sql = "select * from admin";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				s.setId(rs.getString("id"));
				s.setPw(rs.getString("passwd"));
				if(id.equals(rs.getString("id"))) {
					if(pw.equals(rs.getString("passwd"))) {
						dbClose();
						log.log.setVisible(false);
						JOptionPane.showMessageDialog(null,"admin님 환영합니다","알림",JOptionPane.INFORMATION_MESSAGE);
						t =1;
						Login.logdata = t;
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인하세요", "알림", JOptionPane.ERROR_MESSAGE);
						t=0;
						Login.logdata = t;
					}
				}else {
					JOptionPane.showMessageDialog(null, "아이디를 확인하세요", "알림", JOptionPane.ERROR_MESSAGE);
					t=0;
					Login.logdata = t;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	//
	public Vector AlList() {	// 하루 매출
		Vector data = new Vector();
		String sql = "select menu,price,amount from orders where amount > 0 and price > 0";
		PreparedStatement pstmt = null;
		d = new Data();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String menu = rs.getString("menu");
				int price = rs.getInt("price");
				int amount = rs.getInt("amount");
				Vector row = new Vector();
				row.add(menu);
				row.add(price);
				row.add(amount);
				data.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector DayList() {	// 일 매출
		Vector data = new Vector();
		String sql = "select day,dprice,amount from dsale";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String day = rs.getString("day");
				int price = rs.getInt("dprice");
				int amount = rs.getInt("amount");
				Vector row = new Vector();
				row.add(day);
				row.add(price);
				row.add(amount);
				data.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector MonthList() {	// 월 매출
		Vector data = new Vector();
		String sql = "select month,mprice,amount from msale";
		PreparedStatement pstmt = null;		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String month = rs.getString("month");
				int price = rs.getInt("mprice");
				int amount = rs.getInt("amount");
				Vector row = new Vector();
				row.add(month);
				row.add(price);
				row.add(amount);
				data.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector YearList() {	// 연 매출
		Vector data = new Vector();
		String sql = "select year,yprice,amount from ysale";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String year = rs.getString("year");
				int price = rs.getInt("yprice");
				int amount = rs.getInt("amount");
				Vector row = new Vector();
				row.add(year);
				row.add(price);
				row.add(amount);
				data.add(row);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	public void DeleteSettle(String t) {
		String sql1 = "delete from "+t;
		try {
			stmt=conn.createStatement();
		    stmt.executeUpdate(sql1);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public void AlterAI(String t) {
		String sql = "alter table " + t +" auto_increment=1;";
		try {
			stmt=conn.createStatement();
		    stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void ResetOrders() {
		String sql = "update orders set amount = 0;";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbClose() throws SQLException {
		conn.close();
		System.out.println("연결 해제!!");
	}
}
