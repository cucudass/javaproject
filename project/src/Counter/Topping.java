package Counter;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Topping implements ActionListener {
	static final int SCREEN_WIDTH = 720;//763
	static final int SCREEN_HEGITH = 960;//190
	static DBDao db;
	static Data data;
	static Menu menu;
	static JFrame toppingselec;
	static JPanel p1;
	static Image bi, ci, hi, ei, exiti;
	static String topm;
	static int topp;
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
	public Topping() {
		toppingselec = new JFrame();
		toppingselec.setSize(SCREEN_WIDTH, SCREEN_HEGITH);
		toppingselec.setUndecorated(true); //메뉴바 없애기
		toppingselec.setLayout(null);
		toppingselec.setBackground(new Color(0, 0, 0, 122));
		toppingselec.setLocation(550, 50);
		toppingselec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel1();
		toppingselec.setVisible(true);
	}
	public void Panel1() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.white);
		p1.setBounds(0, 300, 763, 190);
		
		exiti = new ImageIcon(Start.class.getResource("../images/종료.png")).getImage();
		bi = new ImageIcon(Start.class.getResource("../images/베이컨.png")).getImage();
		ci = new ImageIcon(Start.class.getResource("../images/치즈.png")).getImage();
		hi = new ImageIcon(Start.class.getResource("../images/햄.png")).getImage();
		ei = new ImageIcon(Start.class.getResource("../images/계란.png")).getImage();		
		
		CreateButton(0, new ImageIcon(exiti), 0, 0, 150, 150, p1); //취소
		CreateButton(1, new ImageIcon(bi), 150, 0, 150, 150, p1); //베이컨
		CreateButton(2, new ImageIcon(ci), 300, 0, 150, 150, p1); //치즈
		CreateButton(3, new ImageIcon(hi), 450, 0, 150, 150, p1); //햄
		CreateButton(4, new ImageIcon(ei), 600, 0, 150, 150, p1); //계란
	
		CreateLabel(0, "닫기", 60, 160, 150, 30, p1);
		CreateLabel(1, "베이컨 +500원", 185, 160, 150, 30, p1);
		CreateLabel(2, "치즈 +500원", 335, 160, 150, 30, p1);
		CreateLabel(3, "햄 +500원", 485, 160, 150, 30, p1);
		CreateLabel(4, "계란 +500원", 635, 160, 150, 30, p1);
		
		toppingselec.add(p1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb.get(0)) {
			Menu.p6list.add("추가 없음");
			toppingselec.setVisible(false);
		}
		if(e.getSource() == jb.get(1)) {//베이컨 추가
			db = new DBDao();
			db.SelectT("toppingselec", 1);
			Menu.p6list.add("베이컨토핑 추가 +500원");
			Menu.sum += topp;
			Menu.sumText();
			Menu.tp1 += 1;
			if(Menu.tp1 >1) {
				db.UpdateTopping();
			}else {
			db.Addtopping();//sale에 베이컨 추가
			}
			toppingselec.setVisible(false);
		}
		if(e.getSource() == jb.get(2)) {//치즈 추가
			db = new DBDao();
			db.SelectT("toppingselec", 2);
			Menu.p6list.add("치즈토핑 추가 +500원");
			Menu.sum += topp;
			Menu.sumText();
			Menu.tp2 += 1;
			if(Menu.tp2 >1) {
				db.UpdateTopping();
			}else {
			db.Addtopping();//sale에 치즈 추가
			}
			toppingselec.setVisible(false);
		}
		if(e.getSource() == jb.get(3)) {//햄 추가
			db = new DBDao();
			db.SelectT("toppingselec", 3);
			Menu.p6list.add("햄토핑 추가 +500원");
			Menu.sum += topp;
			Menu.sumText();
			Menu.tp3 +=1;
			if(Menu.tp3 > 1) {
				db.UpdateTopping();
			}else {
			db.Addtopping();//sale에 햄 추가
			}
			toppingselec.setVisible(false);
		}
		if(e.getSource() == jb.get(4)) {//계란 추가
			db = new DBDao();
			db.SelectT("toppingselec", 4);
			Menu.p6list.add("계란토핑 추가 +500원");
			Menu.sum += topp;
			Menu.sumText();
			Menu.tp4 += 1;
			if(Menu.tp4 > 1) {
				db.UpdateTopping();
			}else {
			db.Addtopping();//sale에 계란 추가
			}
			toppingselec.setVisible(false);
		}
	}
}
