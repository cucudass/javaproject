package Counter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JFrame implements ActionListener {
	DBDao db = new DBDao();
	public static final int SCREEN_WIDTH = 720;
	public static final int SCREEN_HEIGHT = 960;
	static JFrame first;
	JPanel jp;
	JPanel jp1;
	JLabel jl1;
	static JButton orders, seat1;
	static Image imgads = new ImageIcon(Start.class.getResource("../images/hamburgerad.gif")).getImage();
	static Image imglogo = new ImageIcon(Start.class.getResource("../images/burgerlogo.jpg")).getImage();
	static Image imageorder = new ImageIcon(Start.class.getResource("../images/start.png")).getImage();
	static int ck;
	Seat seat;
	
	public Start() {
		first = new JFrame();
		// first.setUndecorated(true); //메뉴바 없애기
		first.setTitle("Main Mode");
		first.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		first.setResizable(false);
		first.setLocation(550, 50);
		first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		P1();
		P2();
		first.setVisible(true);
	}
	public void P1() { // 로고이미지
		jp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(imglogo, 0, 0, null);
			}
		};
		jp.setLayout(null);
		jp.setBounds(0, 0, SCREEN_WIDTH, 360);
		seat1 = new JButton("좌석확인");
		seat1.setBounds(600, 10, 100, 50);
		seat1.addActionListener(this);
		first.add(seat1);
		first.add(jp);
	}
	public void P2() { // gif 이미지 and 주문버튼
		jp1 = new JPanel();
		jp1.setLayout(null);
		jp1.setBounds(0, 360, SCREEN_WIDTH, 600);
		jl1 = new JLabel(new ImageIcon(imgads));
		jl1.setBounds(0, 360, SCREEN_WIDTH, 600);
		orders = new JButton(new ImageIcon(imageorder));
		orders.setBounds(270, 825, 150, 50);
		orders.addActionListener(this);
		jp1.add(orders);
		jp1.add(jl1);
		first.add(jp1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == orders) { //주문하기 이벤트
				Menu menu = new Menu();
				menu.menu.setVisible(true);
				db.CreateTable();
				first.setVisible(false);
		} else if (e.getSource() == seat1) {
			try {
				seat = new Seat();
			} catch (IOException  e1) {
				e1.printStackTrace();
			}
			Seat.takeout.setVisible(false);
			ck = 1;
		}
	}
	public static void main(String[] args) {
		new Start();
	}
}
