package Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Receipt extends JFrame implements ActionListener {
	private static final int SCREEN_WIDTH = 320;
	private static final int SCREEN_HEIGHT = 450;
	static Font lstfn = new Font("고딕",Font.BOLD, 17);
	Data data = new Data();
	static JFrame rec;
	private JPanel p1, p2;
	private JLabel l1, l2, il;
	static int i;
	private Random random;
	static double randou;
	static String line;
	static Scanner sc;
	static Timer texit;
	static TimerTask ttask;
	static JButton exit;
	static List relist;
	static JLabel lprice;
	static Image irece;
	
	public Receipt() {
		rec = new JFrame("영수증");
		rec.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		rec.setUndecorated(true); //메뉴바 없애기
		rec.setDefaultCloseOperation(HIDE_ON_CLOSE);
		rec.setLocation(750, 300);
		rec.setResizable(false);
		
		texit = new Timer();
		ttask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				rec.setVisible(false);
			}
		};
		texit.schedule(ttask, 4500); //4.5초 뒤에 자동종료
		Panel1();
		rec.setVisible(true);
	}
	public void Panel1() {
		irece = new ImageIcon(Start.class.getResource("../images/영수증.png")).getImage();
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.white);
		p1.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		l1 = new JLabel("<html><font size = 7><b> 영수증 <b/></font></html>");
		l1.setBounds(95, 0, 200, 50);
		
		il = new JLabel(new ImageIcon(irece));
		il.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		random = new Random();
		randou = Math.random();

		l2 = new JLabel("<html><font size=6><b> 주문 번호 : " + i + "</b></font></html>");
		l2.setBounds(70, 50, 200, 40);
		
		relist = new List();
		relist.setBounds(10, 115, 300, 150);
		relist.setBackground(Color.white);
		relist.setFont(lstfn);
		for(int i = 0; i < Counter.Menu.p6list.getItemCount(); i++) {
			String str = Counter.Menu.p6list.getItem(i);
			System.out.println(str);
			relist.add(str);
		}
		ArrayList<String> arr = new ArrayList<String>();
		for(int j = 0; j<relist.getItemCount();j++) {
			arr.add(relist.getItem(j));
		}
		System.out.println(arr.toString().trim());
		lprice = new JLabel("<html><font size = 6><b>" + Integer.toString(Counter.Menu.p6l1s2) +"원 <b/></font></html>");
		lprice.setBounds(200, 260, 100, 50);
		
		exit = new JButton("<html><font size = 4><b> 확인  <b/></font></html>");
		exit.setBounds(120, 400, 70, 40);
		exit.addActionListener(this);

		p1.add(l1);
		p1.add(l2);
		p1.add(relist);
		p1.add(lprice);
		p1.add(exit);
		p1.add(il);
		rec.add(p1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exit) {
			rec.setVisible(false);
		}
		
	}
}
