package Admin;
public class AdminData {
	String Pw,Id,menu,omenu;  // 관리자 Id,pw, 메뉴변수값, orders 메뉴
	int no, price,taxprice,amount,amountcheck,logdata; // no(Auto_Increment), 가격, 지불금액, 판매수량
	public AdminData() {
		
	}
	public int getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(int taxprice) {
		this.taxprice = taxprice;
	}

	public String getPw() {
		return Pw;
	}
	public void setPw(String pw) {
		Pw = pw;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getOmenu() {
		return omenu;
	}
	public void setOmenu(String omenu) {
		this.omenu = omenu;
	}
	public int getAmountcheck() {
		return amountcheck;
	}
	public void setAmountcheck(int amountcheck) {
		this.amountcheck = amountcheck;
	}
	public int getLogdata() {
		return logdata;
	}
	public void setLogdata(int logdata) {
		this.logdata = logdata;
	}
	
}