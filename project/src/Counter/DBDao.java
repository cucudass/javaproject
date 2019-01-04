package Counter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBDao {
	static Data data = new Data();
    private Connection conn;
    private static final String USERNAME = "counter";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mysql://localhost/count?useUnicode=true&characterEncoding=euckr";
    private PreparedStatement pstmt, pstmt1, pstmt2 = null;
    private Statement stmt, stmt1 = null;
    private ResultSet rs, rs2;
    
	public DBDao() {
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
	public void CreateOrChangeDatabase() {
		String dbName = new String("count");
		try {
			String dbSql = "SELECT * FROM Information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
			pstmt = conn.prepareStatement(dbSql);
			pstmt.setString(1, dbName);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				Statement stmt = conn.createStatement();
				String sql = "create database " + dbName;
				boolean re = stmt.execute(sql);
				if(!re) System.out.println("데이터베이스 생성 실패"+re);
				stmt.close();
			} 
			conn.setCatalog(dbName);
		} catch (Exception e) {
			System.out.println("CreateOrChangeDatabase err : " + e);
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)pstmt.close();
			} catch (Exception e) {

			}
		}
	}
	public void CreateTable(){ //count DB에 sale 테이블 생성 
		String dbName = new String("count");
		String tName = new String("sale");
		try {
			CreateOrChangeDatabase();
			String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
			pstmt = conn.prepareStatement(tableSql);
			pstmt.setString(1, dbName);
			pstmt.setString(2, tName);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				String sql = "create table "+ tName +"(menu varchar(20), price int, amount int)";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("생성 성공");
			}
		} catch (SQLException e) {
			System.out.println("CreateTable err : " + e);
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)pstmt.close();
			} catch (SQLException e) {
			}
		}
	}
	public void InsertInto() { //sale 테이블 입력
		String sql = "insert into sale (menu, price,amount) values (?,?,1)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Menu.dm);
			pstmt.setInt(2, Menu.dp);
		    pstmt.executeUpdate();
			System.out.println("insert Table");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void UpdateAmount() {  // 가상테이블에 이미 주문된 값 있을경우 수량 + 1
		String sql = "update sale set amount = amount + 1 where menu = ?;";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Menu.dm);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String Sizeup() { //sale 테이블 입력(drink change)
		String sql = "insert into sale (menu, price,amount) values (?,?,1)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Drinkselec.drin);
			pstmt.setInt(2, Drinkselec.drip);
		    pstmt.executeUpdate();
			System.out.println("insert Table");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Drinkselec.drin;
	}
	public void Addtopping() { //sale 테이블 입력(topping 추가)
		String sql = "insert into sale (menu, price,amount) values (?,?,1)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Topping.topm);
			pstmt.setInt(2, Topping.topp);
		    pstmt.executeUpdate();
			System.out.println("insert Table");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void UpdateTopping() {
		String sql = "update sale set amount = amount + 1 where menu = ?;";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Topping.topm);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void UpdateSizeup() {
		String sql = "update sale set amount = amount + 1 where menu = ?;";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, Drinkselec.drin);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void Maxno(){
		String sql = "select max(no) from kitchen";
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Cash.checkno = rs.getInt("max(no)");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public ArrayList DataSel(){
		String sql = "select menu,amount from sale";
		ArrayList arr = new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Data d = new Data();
				d.setMenu(rs.getString("menu"));
				d.setAmount(rs.getInt("amount"));
				arr.add(d);
			}
			System.out.println("검색 성공");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public ArrayList DataAmountSel(){
		String sql = "select amount from sale";
		ArrayList arr = new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Data d = new Data();
				d.setAmount(rs.getInt("amount"));
				arr.add(d);
			}
			System.out.println("검색 성공");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public void InsertKt(int no, int num,String menu,int amount){
		String sql = "insert into kitchen values(?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, num);
			pstmt.setString(3, menu);
			pstmt.setInt(4, amount);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public Data SelectM(String i, int no) {
		String sql = "select * from " + i + " where no = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setMenu(rs.getString("menu"));
				data.setPrice(rs.getInt("price"));
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
		return data;
	}
	public Data SelectT(String i, int no) {//토핑 선택
		String sql = "select * from " + i + " where no = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setMenu(rs.getString("topping"));
				data.setPrice(rs.getInt("price"));
				Topping.topm = data.getMenu();
				Topping.topp = data.getPrice();
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
		return data;
	}
	public Data SelectD(String i, int no) {
		String sql = "select * from " + i + " where no = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setMenu(rs.getString("drink"));
				data.setPrice(rs.getInt("price"));
				Drinkselec.drip = data.getPrice();
				Drinkselec.drin = data.getMenu();
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
		return data;
	}
	
	public void DeleteAll() {//sale 테이블 전체 컬럼 삭제
		String sql = "delete from sale;";
		try {
			stmt = conn.createStatement();
		    stmt.executeUpdate(sql);
		    System.out.println("Table deleted");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void AddUpdateSelect() { //sale 테이블에서 선택한 컬럼 삭제
		String sql = "update sale set amount = amount-1 where menu = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu.am);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void AddDeleteSelect() { //sale 테이블에서 선택한 컬럼 삭제
		String sql = "delete from sale where menu = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu.am);
			pstmt.executeUpdate();
		    System.out.println("Table selecet deleted");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void UpdateSelec() { //sale 테이블에서 선택한 컬럼 삭제
		String sql = "update sale set amount = amount-1 where menu = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu.dm);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void DeleteSelec() { //sale 테이블에서 선택한 컬럼 삭제
		String sql = "delete from sale where menu = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Menu.dm);
			pstmt.executeUpdate();
		    System.out.println("Table selecet deleted");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void dbClose() throws SQLException {
	conn.close();
	System.out.println("연결 해제!!");
	}
}
