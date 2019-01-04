package Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener {

	static final int SCREEN_WIDTH = 720;
	static final int SCREEN_HEGIHT = 960;
	static DBDao db;
	static Data data;
	static Drinkselec drinkselec;
	static JFrame menu;
	private Image p2i1, p2i2, p2i3, p2i4, p3i1, p3i2, p3i3, p3i4, p4i1, p4i2, p4i3, p4i4, p5i1, p5i2, p5i3, p5i4,p6i1;
	static JLabel p6l1;
	static JButton p6b1, p6b2, p6b3;
	private JPanel chbtn, set, single, sidefd, drink, p6;
	static List p6list;
	static int i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16;
	static int dr1,dr2,dr3,dr4,dr5,dr6,dr7,dr8,tp1,tp2,tp3,tp4;  // 음료변경 및 토핑추가 카운트
	Font btfn = new Font("고딕", Font.BOLD, 28);
	Font lstfn = new Font("고딕", Font.BOLD, 17);
	static String dm, am;
	static int dp, p6l1s1, p6l1s2, ab;
	static int sum = 0;
	Topping topping;
	static Image isets = new ImageIcon(Start.class.getResource("../images/set.png")).getImage();
	static Image isingle = new ImageIcon(Start.class.getResource("../images/burger.png")).getImage();
	static Image iside = new ImageIcon(Start.class.getResource("../images/side.png")).getImage();
	static Image idrink = new ImageIcon(Start.class.getResource("../images/drink.png")).getImage();
	static Image ihome = new ImageIcon(Start.class.getResource("../images/홈.png")).getImage();
	
	ArrayList<JButton> jb = new ArrayList<JButton>();
	ArrayList<JLabel> jl = new ArrayList<JLabel>();
	
	void CreateButton(int num, ImageIcon name, int x, int y, int wid, int hei, JPanel p) {
		jb.add(num,new JButton(name));
		jb.get(num).setBounds(x, y, wid, hei);
		jb.get(num).addActionListener(this);
		jb.get(num).setBackground(Color.white);
		jb.get(num).setBorderPainted(false);
		p.add(jb.get(num));
	}
	void CreateLabel(int num, String name, int x, int y, int wid, int hei, JPanel p) {
		jl.add(num,new JLabel(name));
		jl.get(num).setBounds(x, y, wid, hei);
		p.add(jl.get(num));	
	}
	public Menu() {
		menu = new JFrame();
//		menu.setUndecorated(true); //메뉴바 없애기
		menu.setTitle("Menu");
		menu.setResizable(false);
		menu.setSize(SCREEN_WIDTH, SCREEN_HEGIHT);
		menu.setLocation(550, 50);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel1();
		Panel2();
		Panel3();
		Panel4();
		Panel5();
		Panel6();
		menu.setVisible(true);
	}
	public void Panel1() { // 메뉴 선택버튼
		chbtn = new JPanel();
		chbtn.setLayout(null);
		chbtn.setBounds(0, 0, 720, 120);
		chbtn.setBackground(Color.white);
		CreateButton(0, new ImageIcon(isets), 0, 0, 154, 120, chbtn);
		CreateButton(1, new ImageIcon(isingle), 154, 0, 154, 120, chbtn);
		CreateButton(2, new ImageIcon(iside), 308, 0, 154, 120, chbtn);
		CreateButton(3, new ImageIcon(idrink), 462, 0, 154, 120, chbtn);
		CreateButton(4, new ImageIcon(ihome), 615, 0, 103, 120, chbtn);
		
		menu.add(chbtn);
	}

	public void Panel2() { //세트 패널
		set = new JPanel();
		set.setLayout(null);
		set.setBounds(0, 120, 720, 645);
		set.setBackground(Color.white);
		p2i1 = new ImageIcon(Start.class.getResource("../images/불고기세트.png")).getImage();
		p2i2 = new ImageIcon(Start.class.getResource("../images/새우세트.png")).getImage();
		p2i3 = new ImageIcon(Start.class.getResource("../images/치즈세트.png")).getImage();
		p2i4 = new ImageIcon(Start.class.getResource("../images/치킨세트.png")).getImage();
		
		CreateButton(5, new ImageIcon(p2i1), 20, 30, 320, 250, set);
		CreateButton(6, new ImageIcon(p2i2), 370, 30, 320, 250, set);
		CreateButton(7, new ImageIcon(p2i3), 20, 335, 320, 250, set);
		CreateButton(8, new ImageIcon(p2i4), 370, 335, 320, 250, set);
		
		CreateLabel(0, "<html>불고기버거세트<br> 5000원</html>", 130, 280, 150, 40, set);
		CreateLabel(1, "<html>새우버거세트<br> 5500원</html>", 485, 280, 150, 40, set);
		CreateLabel(2, "<html>치즈버거세트<br> 5000원</html>", 130, 585, 150, 40, set);
		CreateLabel(3, "<html>치킨버거세트<br> 5500원</html>", 485, 585, 150, 40, set);
		
		menu.add(set);
	}
	public void Panel3() { // 단품 패널
		single = new JPanel();
		single.setLayout(null);
		single.setBounds(0, 120, 720, 645);
		single.setBackground(Color.white);
		p3i1 = new ImageIcon(Start.class.getResource("../images/불고기버거.png")).getImage();
		p3i2 = new ImageIcon(Start.class.getResource("../images/새우버거.jpg")).getImage();
		p3i3 = new ImageIcon(Start.class.getResource("../images/치즈버거.png")).getImage();
		p3i4 = new ImageIcon(Start.class.getResource("../images/치킨버거.png")).getImage();
		
		CreateButton(9, new ImageIcon(p3i1), 20, 30, 320, 250, single);
		CreateButton(10, new ImageIcon(p3i2), 370, 30, 320, 250, single);
		CreateButton(11, new ImageIcon(p3i3), 20, 335, 320, 250, single);
		CreateButton(12, new ImageIcon(p3i4), 370, 335, 320, 250, single);
		
		CreateLabel(4, "<html>불고기버거<br>3000원</html>", 130, 280, 150, 40, single);
		CreateLabel(5, "<html>새우버거<br>3500원</html>", 485, 280, 150, 40, single);
		CreateLabel(6, "<html>치즈버거<br>3000원</html>", 130, 585, 150, 40, single);
		CreateLabel(7, "<html>치킨버거<br>3500원</html>", 485, 585, 150, 40, single);
		
		single.setVisible(false);
		menu.add(single);
	}
	public void Panel4() { // 사이드 패널
		sidefd = new JPanel();
		sidefd.setLayout(null);
		sidefd.setBounds(0, 120, 720, 645);
		sidefd.setBackground(Color.white);
		p4i1 = new ImageIcon(Start.class.getResource("../images/치즈스틱.jpg")).getImage();
		p4i2 = new ImageIcon(Start.class.getResource("../images/치킨윙.jpg")).getImage();
		p4i3 = new ImageIcon(Start.class.getResource("../images/샐러드.jpg")).getImage();
		p4i4 = new ImageIcon(Start.class.getResource("../images/감자튀김.jpg")).getImage();
		
		CreateButton(13, new ImageIcon(p4i1), 20, 30, 320, 250, sidefd);
		CreateButton(14, new ImageIcon(p4i2), 370, 30, 320, 250, sidefd);
		CreateButton(15, new ImageIcon(p4i3), 20, 335, 320, 250, sidefd);
		CreateButton(16, new ImageIcon(p4i4), 370, 335, 320, 250, sidefd);
		
		CreateLabel(8, "<html>치즈스틱<br>1500원</html>", 130, 280, 150, 40, sidefd);
		CreateLabel(9, "<html>치킨 윙<br>2500원</html>", 485, 280, 150, 40, sidefd);
		CreateLabel(10, "<html>샐러드<br>2000원</html>", 130, 585, 150, 40, sidefd);
		CreateLabel(11, "<html>감자튀김<br>1000원</html>", 485, 585, 150, 40, sidefd);
	
		sidefd.setVisible(false);
		menu.add(sidefd);
	}
	public void Panel5() { //음료 패널
		drink = new JPanel();
		drink.setLayout(null);
		drink.setBounds(0, 120, 720, 645);
		drink.setBackground(Color.white);
		p5i1 = new ImageIcon(Start.class.getResource("../images/콜라.jpg")).getImage();
		p5i2 = new ImageIcon(Start.class.getResource("../images/스프라이트.jpg")).getImage();
		p5i3 = new ImageIcon(Start.class.getResource("../images/환타.png")).getImage();
		p5i4 = new ImageIcon(Start.class.getResource("../images/아메리카노.jpg")).getImage();
		
		CreateButton(17, new ImageIcon(p5i1), 20, 30, 320, 250, drink);
		CreateButton(18, new ImageIcon(p5i2), 370, 30, 320, 250, drink);
		CreateButton(19, new ImageIcon(p5i3), 20, 335, 320, 250, drink);
		CreateButton(20, new ImageIcon(p5i4), 370, 335, 320, 250, drink);
		
		CreateLabel(12, "<html>콜라<br>1000원</html>", 130, 280, 150, 40, drink);
		CreateLabel(13, "<html>스프라이트<br>1000원</html>", 485, 280, 150, 40, drink);
		CreateLabel(14, "<html>환타<br>1000원</html>", 130, 585, 150, 40, drink);
		CreateLabel(15, "<html>아메리카노<br>1000원</html>", 485, 585, 150, 40, drink);
		
		drink.setVisible(false);
		menu.add(drink);
	}
	public void Panel6() { // 계산 패널
		p6 = new JPanel();
		p6.setLayout(null);
		p6.setBounds(0, 775, 720, 400);
		p6i1 = new ImageIcon(Start.class.getResource("../images/결제.png")).getImage();
		p6list = new List(6);
		p6list.setBounds(10, 812, 300, 120);
		p6list.setBackground(Color.gray);

		p6b1 = new JButton(new ImageIcon(p6i1));
		p6b1.setBounds(415, 812, 299, 120);
		p6b1.addActionListener(this);
		p6b1.setBackground(Color.LIGHT_GRAY);

		p6l1 = new JLabel("<html> <font size=7><b>수량 : 0개 || 금액 : 0원</b></font></html>");
		p6l1.setBounds(140, 760, 510, 40);

		p6b2 = new JButton("<html> <font size=3><b> 선택 취소 </b></font></html>");
		p6b2.setBounds(310, 812, 105, 60);
		p6b2.setBorderPainted(false);
		p6b2.setEnabled(false);
		p6b2.addActionListener(this);

		p6b3 = new JButton("<html><font size=3><b> 전체 취소 </b></font></html>");
		p6b3.setBounds(310, 872, 105, 60);
		p6b3.setBorderPainted(false);
		p6b3.setEnabled(false);
		p6b3.addActionListener(this);

		p6list.setFont(lstfn);

		p6.add(p6b2);
		p6.add(p6b3);
		p6.add(p6l1);
		p6.add(p6list);
		p6.add(p6b1);

		p6.setBackground(Color.WHITE);
		menu.add(p6);
	}
	static boolean Delete(String a) { // 선택 취소 조건 메소드
		return p6list.getSelectedItem().indexOf(a) > -1;
	}

	static void DoNotDelete() {
		int lt = p6list.getSelectedIndex();
		String str1 = p6list.getItem(lt);
		if(str1.indexOf("콜라L")>-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("스프라이트L") >-1){
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("환타L") >-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("아메리카노L") >-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("음료 콜라") > -1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("음료 스프라이트") > -1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("음료 환타") > -1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("음료 아메리카노") > -1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("베이컨토핑")>-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("치즈토핑")>-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("햄토핑")>-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("계란토핑")>-1) {
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else if(str1.indexOf("야채 추가")>-1){
			JOptionPane.showMessageDialog(null, "추가 및 변경한 상품은 따로 취소할 수 없습니다.","알림!",JOptionPane.ERROR_MESSAGE);
		}else{
			Delete();
			p6list.remove(p6list.getSelectedIndex());
		}
	}
	static void DeleteDr() {
		ab = p6list.getSelectedIndex()+1;
		if(ab == p6list.getItemCount()) {
			sum -=0;
		}else {
		String str = p6list.getItem(ab);
		if(str.indexOf("콜라L")>-1) {
			sum = sum -300;	p6list.remove(ab);
			dr2 -=1; am = "콜라(SetL)";
			if(dr2 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("스프라이트L") >-1){
			sum = sum -300;	p6list.remove(ab);
			dr4 -=1; am = "스프라이트(SetL)";
			if(dr4 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("환타L") >-1) {
			sum = sum -300;	p6list.remove(ab);
			dr6 -=1; am = "환타(SetL)";
			if(dr6 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("아메리카노L") >-1) {
			sum = sum -300;	p6list.remove(ab);
			dr8 -=1; am = "아메리카노(SetL)";
			if(dr8 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("콜라") > -1) {
			sum = sum -0;	p6list.remove(ab);
			dr1 -=1; am = "콜라(Set)";
			if(dr1 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("스프라이트") > -1) {
			sum = sum -0;	p6list.remove(ab);
			dr3 -=1; am = "스프라이트(Set)";
			if(dr3 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("환타") > -1) {
			sum = sum -0;	p6list.remove(ab);
			dr5 -=1; am = "환타(Set)";
			if(dr5 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("아메리카노") > -1) {
			sum = sum -0;	p6list.remove(ab);
			dr7 -=1; am = "아메리카노(Set)";
			if(dr7 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("베이컨토핑")>-1) {
			sum = sum - 500;	p6list.remove(ab);
			tp1 -=1; am = "베이컨";
			if(tp1 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("치즈토핑")>-1) {
			sum = sum - 500;	p6list.remove(ab);
			tp2 -=1; am = "치즈";
			if(tp2 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("햄토핑")>-1) {
			sum = sum - 500;	p6list.remove(ab);
			tp3 -=1; am = "햄";
			if(tp3 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("계란토핑")>-1) {
			sum = sum - 500;	p6list.remove(ab);
			tp4 -=1; am = "계란";
			if(tp4 == 0) {
				db.AddDeleteSelect();
			}else {
			db.AddUpdateSelect();
			}
		}else if(str.indexOf("야채 추가")>-1){
			p6list.remove(ab);
		}else{
		
			sum = sum - 0;
		}
	}
}
	static void Delete() { // 선택 삭제 메소드
		db = new DBDao();
		if (Delete("불고기버거세트")) {
			sum = sum - 5000;
			i1 = i1 - 1;
			dm = "불고기버거세트"; 
			DeleteDr();
			if(i1 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			sumText();
		} else if (Delete("새우버거세트")) {
			sum = sum - 5500;
			i2 = i2-1;
			dm = "새우버거세트";
			DeleteDr();
			if(i2 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			sumText();
		} else if (Delete("치즈버거세트")) {
			sum = sum - 5000;
			i3 = i3-1;
			dm = "치즈버거세트";
			DeleteDr();
			if(i3 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			sumText();
		} else if (Delete("치킨버거세트")) {
			sum = sum - 5500;
			i4 -=1;
			dm = "치킨버거세트";
			DeleteDr();
			if(i4 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			sumText();
		} else if (Delete("불고기버거")) {
			sum = sum - 3000;
			i5 -=1;
			dm = "불고기버거";
			DeleteDr();
			if(i5 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			sumText();
		} else if (Delete("새우버거")) {
			sum = sum - 3500;
			i6 -=1;
			dm = "새우버거";DeleteDr(); 
			if(i6 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("치즈버거")) {
			sum = sum - 3000;
			i7 -= 1;
			dm = "치즈버거";DeleteDr();
			if(i7 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("치킨버거")) {
			sum = sum - 3500;
			i8 -= 1;
			dm = "치킨버거";DeleteDr();
			if(i8 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("치즈스틱")) {
			sum = sum - 1500;
			i9 -= 1;
			dm = "치즈스틱";
			if(i9 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("치킨 윙")) {
			sum = sum - 2500;
			i10 -= 1;
			dm = "치킨 윙";
			if(i10 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("샐러드")) {
			sum = sum - 2000 ;
			i11 -= 1;
			dm = "샐러드";
			if(i11 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("감자튀김")) {
			sum = sum - 1000;
			i12 -= 1;
			dm = "감자튀김";
			if(i12 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("콜라")) {
			sum = sum - 1000;
			i13 -=1;
			dm = "콜라";
			if(i13 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("스프라이트")) {
			sum = sum - 1000;
			i14 -= 1;
			dm = "스프라이트";
			if(i14 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("환타")) {
			sum = sum - 1000;
			i15 -=1;
			dm = "환타";
			if(i15 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		} else if (Delete("아메리카노")) {
			sum = sum -1000;
			i16 -= 1;
			dm = "아메리카노";
			if(i16 == 0) {
				db.DeleteSelec();
			}else {
			db.UpdateSelec();
			}
			 sumText();
		}
	}
	static void DeleteAll() { // 전체 삭제 메소드
		sum = 0;
		i1 = 0; dr1 = 0;
		i2 = 0; dr2 = 0;
		i3 = 0; dr3 = 0;
		i4 = 0; dr4 = 0;
		i5 = 0; dr5 = 0;
		i6 = 0; dr6 = 0;
		i7 = 0; dr7 = 0;
		i8 = 0; dr8 = 0;
		i9 = 0; tp1 = 0;
		i10 = 0; tp2 = 0;
		i11 = 0; tp3 = 0;
		i12 = 0; tp4 = 0;
		i13 = 0;
		i14 = 0;
		i15 = 0;
		i16 = 0;
		Drinkselec.drip = 0;
	}
	static void allSum() { // 총값 메소드
		dm = data.getMenu();
		dp = data.getPrice();
		p6b2.setEnabled(true);
		p6b3.setEnabled(true);
		sum = sum + dp;
	}
	static void sumText() { // 수량 및 총합 결과 출력 메소드
		p6l1s1 = i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9 + i10 + i11 + i12 + i13 + i14 + i15 + i16;
		p6l1s2 = sum;
		p6l1.setText("<html> <font size=7><b>수량 : "+p6l1s1+" || 금액 : "+sum+"원</b></font></html>");
		dp = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 상단 메뉴 버튼 이벤트
		if (e.getSource() == jb.get(0)) { //세트
			set.setVisible(true);
			single.setVisible(false);
			sidefd.setVisible(false);
			drink.setVisible(false);
		} else if (e.getSource() == jb.get(1)) { //단품
			set.setVisible(false);
			single.setVisible(true);
			sidefd.setVisible(false);
			drink.setVisible(false);
		} else if (e.getSource() == jb.get(2)) { //사이드
			set.setVisible(false);
			single.setVisible(false);
			sidefd.setVisible(true);
			drink.setVisible(false);
		} else if (e.getSource() == jb.get(3)) { // 음료
			set.setVisible(false);
			single.setVisible(false);
			sidefd.setVisible(false);
			drink.setVisible(true);
		} else if (e.getSource() == jb.get(4)) { // 홈
			Start main = new Start();
			menu.setVisible(false);
			DeleteAll();
		} else if (e.getSource() == p6b1) {
			try {
				new Seat();
				Start.ck = 2;
				Seat.back.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// 음식 선택 버튼 이벤트
		if (e.getSource() == jb.get(5)) { // 불고기버거 세트
			db = new DBDao();
			drinkselec = new Drinkselec();
			data = db.SelectM("sets", 1);
			allSum();
				++i1;
				p6list.add("" + dm + "      " + dp + "원      1개");

			if(i1 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}			sumText();
		}
		if (e.getSource() == jb.get(6)) { // 새우버거 세트
			db = new DBDao();
			drinkselec = new Drinkselec();
			data = db.SelectM("sets", 2);
			allSum();
			++i2;
				p6list.add("" + dm + "          " + dp + "원      1개");
			if(i2 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}			sumText();
		}
		if (e.getSource() == jb.get(7)) { // 치즈버거 세트
			db = new DBDao();
			drinkselec = new Drinkselec();
			data = db.SelectM("sets", 3);
			allSum();
				i3 += 1;
				p6list.add("" + dm + "          " + dp + "원      1개");
			if(i3 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}			sumText();
		}
		if (e.getSource() == jb.get(8)) { // 치킨버거 세트
			db = new DBDao();
			drinkselec = new Drinkselec();
			data = db.SelectM("sets", 4);
			allSum();
				i4 += 1;
				p6list.add("" + dm + "          " + dp + "원      1개");
			if(i4 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(9)) { // 불고기 버거
			db = new DBDao();
			topping = new Topping();
			data = db.SelectM("single", 1);
			allSum();
				i5 += 1;
				p6list.add("" + dm + "             " + dp + "원      1개");
			if(i5 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(10)) { // 새우 버거
			db = new DBDao();
			topping = new Topping();
			data = db.SelectM("single", 2);
			allSum();
				i6 += 1;
				p6list.add("" + dm + "                 " + dp + "원      1개");
			if(i6 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(11)) { // 치즈 버거
			db = new DBDao();
			topping = new Topping();
			data = db.SelectM("single", 3);
			sum = sum + dp;
			allSum();
				i7 += 1;
				p6list.add("" + dm + "                 " + dp + "원      1개");
			if(i7 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(12)) { // 치킨 버거
			db = new DBDao();
			topping = new Topping();
			data = db.SelectM("single", 4);
			sum = sum + dp;
			allSum();
				i8 += 1;
				p6list.add("" + dm + "                 " + dp + "원      1개");
			if(i8 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(13)) { // 치즈스틱
			db = new DBDao();
			data = db.SelectM("side", 1);
			allSum();
				i9 += 1;
				p6list.add("" + dm + "                 " + dp + "원      1개");
			if(i9 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(14)) { // 치킨 윙
			db = new DBDao();
			data = db.SelectM("side", 2);
			allSum();
				i10 += 1;
				p6list.add("" + dm + "                     " + dp + "원      1개");
			if(i10 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(15)) { // 샐러드
			db = new DBDao();
			data = db.SelectM("side", 3);
			allSum();
				i11 += 1;
				p6list.add("" + dm + "                     " + dp + "원      1개");
			if(i11 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(16)) { // 감자튀김
			db = new DBDao();
			data = db.SelectM("side", 4);
			allSum();
				i12 += 1;
				p6list.add("" + dm + "                 " + dp + "원      1개");
			if(i12 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		} // 사이드
		if (e.getSource() == jb.get(17)) { // 콜라
			db = new DBDao();
			data = db.SelectM("drink", 1);
			allSum();
				i13 += 1;
				p6list.add("" + dm + "                        " + dp + "원      1개");
			if(i13 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(18)) { // 스프라이트
			db = new DBDao();
			data = db.SelectM("drink", 2);
			allSum();
				i14 += 1;
				p6list.add("" + dm + "             " + dp + "원      1개");
			if(i14 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(19)) { // 환타
			db = new DBDao();
			data = db.SelectM("drink", 3);
			allSum();
				i15 += 1;
				p6list.add("" + dm + "                        " + dp + "원      1개");
			if(i15 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == jb.get(20)) { // 아메리카노
			db = new DBDao();
			data = db.SelectM("drink", 4);
			allSum();
				i16 += 1;
				p6list.add("" + dm + "             " + dp + "원      1개");
			if(i16 > 1) {
				db.UpdateAmount();
			}else {
				db.InsertInto();	
			}sumText();
		}
		if (e.getSource() == p6b2) { // 선택 취소 이벤트
			try {
				DoNotDelete();
			} catch (Exception e1) {
			}
		} else if (e.getSource() == p6b3) { // 전체 취소 이벤트
			try {
				db = new DBDao();
				DeleteAll();
				db.DeleteAll();
				sumText();
				p6list.removeAll();
				p6b2.setEnabled(false);
				p6b3.setEnabled(false);
			} catch (Exception e1) {
			}
		}

	}
}