package Counter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Drinkselec extends JFrame implements ActionListener {
	static final int SCREEN_WIDTH = 720;
	static final int SCREEN_HEGITH = 960; //415
	static DBDao db;
	static Data data;
	static Menu menu;
	static JFrame drinkselec;
	static JPanel p1;
	static ImageIcon ilb, irb, ilb1, irb1;
	static Image ci, si, fi, ai;
	static String drin;
	static int drip;
	int i1,i2,i3,i4,i5,i6,i7,i8;
	
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
	public Drinkselec() {
		drinkselec = new JFrame("음료 선택");
		drinkselec.setSize(SCREEN_WIDTH, SCREEN_HEGITH);
		drinkselec.setLayout(null);
		drinkselec.setUndecorated(true);
		drinkselec.setBackground(new Color(0, 0, 0, 122));
		drinkselec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drinkselec.setLocation(550,50);
		Panel1();
		drinkselec.setVisible(true);
	}
	public void Panel1() {
	p1 = new JPanel();
	p1.setLayout(null);
	p1.setBounds(0, 280, 720, 416);
	p1.setBackground(Color.white);
	
	ci = new ImageIcon(Start.class.getResource("../images/ds콜라.jpg")).getImage();
	si = new ImageIcon(Start.class.getResource("../images/ds스프라이트.jpg")).getImage();
	fi = new ImageIcon(Start.class.getResource("../images/ds환타.png")).getImage();
	ai = new ImageIcon(Start.class.getResource("../images/ds아메리카노.jpg")).getImage();
		
	CreateButton(0, new ImageIcon(ci), 0, 0, 180, 160, p1); //콜라
	CreateButton(1, new ImageIcon(ci), 180, 0, 180, 160, p1); //콜라L
	CreateButton(2, new ImageIcon(si), 360, 0, 180, 160, p1); //사이다
	CreateButton(3, new ImageIcon(si), 540, 0, 180, 160, p1); //사이다L
	CreateButton(4, new ImageIcon(fi), 0, 208, 180, 160, p1); //환타
	CreateButton(5, new ImageIcon(fi), 180, 208, 180, 160, p1); //환타L
	CreateButton(6, new ImageIcon(ai), 360, 208, 180, 160, p1); //커피
	CreateButton(7, new ImageIcon(ai), 540, 208, 180, 160, p1); //커피L
	
	CreateLabel(0, "콜라, 0원", 50, 160, 180, 48, p1);
	CreateLabel(0, "콜라 L, +300원", 230, 160, 180, 48, p1);
	CreateLabel(0, "스트라이트, 0원", 390, 160, 180, 48, p1);
	CreateLabel(0, "스프라이트 L, + 300원", 570, 160, 180, 48, p1);
	CreateLabel(0, "환타, 0원", 50, 368, 180, 48, p1);
	CreateLabel(0, "환타 L, +300원", 230, 368, 180, 48, p1);
	CreateLabel(0, "아메리카노, 0원", 390, 368, 180, 48, p1);
	CreateLabel(0, "아메리카노 L, +300원", 570, 368, 180, 48, p1);

	drinkselec.add(p1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb.get(0)) {
			db = new DBDao();
			db.SelectD("drinkchange", 1);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 콜라");
			Menu.dr1 += 1;
			if(Menu.dr1>1) {
			db.UpdateSizeup();
			}else {
			db.Sizeup(); //음료 sale에 입력
			}
		}
		if(e.getSource() == jb.get(1)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 2);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 콜라L 변경 +" + drip + "원");
			Menu.sum += drip;
			Menu.sumText();
			Menu.dr2 += 1;
			if(Menu.dr2>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(2)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 3);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 스프라이트 변경");
			Menu.dr3 += 1;
			if(Menu.dr3>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(3)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 4);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 스프라이트L 변경 +" + drip + "원");
			Menu.sum += drip;
			Menu.sumText();
			Menu.dr4 += 1;
			if(Menu.dr4>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(4)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 5);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 환타 변경");
			Menu.dr5 += 1;
			if(Menu.dr5>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(5)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 6);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 환타L 변경 +" + drip + "원");
			Menu.sum += drip;
			Menu.sumText();
			Menu.dr6 += 1;
			if(Menu.dr6>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(6)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 7);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 아메리카노 변경");
			Menu.dr7 += 1;
			if(Menu.dr7>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
		if(e.getSource() == jb.get(7)) {
			db = new DBDao();
			data = new Data();
			db.SelectD("drinkchange", 8);
			drinkselec.setVisible(false);
			Menu.p6list.add("음료 아메리카노L 변경 +" + drip + "원");
			Menu.sum += drip;
			Menu.sumText();
			Menu.dr8 += 1;
			if(Menu.dr8>1) {
				db.UpdateSizeup();
				}else {
				db.Sizeup(); //음료 sale에 입력
				}
		}
	}
}
