package Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Seat extends JFrame implements ActionListener {
	static final int SCREEN_WIDTH = 720;
	static final int SCREEN_HEGIHT = 960;
	static Image ieatseat, inoseat;
	static DBDao db;
	static JButton takeout, back;
	JTextArea ta;
	
	int result;
	Font lafn = new Font("굴림", Font.BOLD, 30);
	Font btfn = new Font("고딕", Font.BOLD, 15);
	JFrame seat;
	JPanel p1, p2;
	JLabel useseat, aa, bb;
	int a = 9, b = 0;
	static int count;
	static Scanner sc;
	static Timer texit;
	static TimerTask ttask;
	static File file;
	int byteRead;
	Image stig;

	static FileInputStream in = null;
	static String inset, btx1, btx2, btx3, btx4, btx5, btx6, btx7, btx8, btx9;
	static FileOutputStream out = null;
	static byte[] by;
	static byte[] buffer = new byte[100];

	DateFormat dateFormat = new SimpleDateFormat("yy-mm-dd");
	Date date = new Date();

	DateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
	Date date2 = new Date();
	
	ArrayList<JButton> jb = new ArrayList<JButton>();
	void CreateButton(int num, ImageIcon name, int x, int y, int wid, int hei, JPanel p) {
		jb.add(num,new JButton(name));
		jb.get(num).setBounds(x, y, wid, hei);
		jb.get(num).addActionListener(this);
		jb.get(num).setBorderPainted(false);
		jb.get(num).setBackground(Color.white);
		p.add(jb.get(num));
	}
	public Seat() throws IOException {
		if(count <= 0){
			count = 0;
		}
		seat = new JFrame();
		seat.setTitle("Seat");
		seat.setResizable(false);
		seat.setUndecorated(true);
		seat.setBackground(new Color(0, 0, 0, 122));
		seat.setSize(SCREEN_WIDTH, SCREEN_HEGIHT);
		seat.setLocation(550, 50);
		
		Panel1();
		btnText(jb.get(0), btx1, "btn1");
		btnText(jb.get(1), btx2, "btn2");
		btnText(jb.get(2), btx3, "btn3");
		btnText(jb.get(3), btx4, "btn4");
		btnText(jb.get(4), btx5, "btn5");
		btnText(jb.get(5), btx6, "btn6");
		btnText(jb.get(6), btx7, "btn7");
		btnText(jb.get(7), btx8, "btn8");
		btnText(jb.get(8), btx9, "btn9");
		seat.setUndecorated(true); //메뉴바 없애기
		seat.setLayout(null);
		seat.setVisible(true);

	}

	void btnText(JButton btn, String btx, String text) throws IOException { // 버튼 텍스트
		in = new FileInputStream("src/order/"+text + ".txt");
		while ((byteRead = in.read(buffer)) >= 0) {
			btx = new String(buffer);
		}
		if (btx == null) {
			btn.setEnabled(true);
		} else {
			btn.setText(btx);
			btn.setIcon(new ImageIcon(ieatseat));
			btn.setEnabled(false);
		}
	}

	public void Panel1() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(110, 250, 500, 550);

		inoseat = new ImageIcon(Start.class.getResource("../images/빈좌석.png")).getImage();
		ieatseat = new ImageIcon(Start.class.getResource("../images/식사.png")).getImage();
		
		CreateButton(0, new ImageIcon(inoseat), 40, 100, 120, 100, p1);
		CreateButton(1, new ImageIcon(inoseat), 185, 100, 120, 100, p1);
		CreateButton(2, new ImageIcon(inoseat), 330, 100, 120, 100, p1);
		CreateButton(3, new ImageIcon(inoseat), 40, 220, 120, 100, p1);
		CreateButton(4, new ImageIcon(inoseat), 185, 220, 120, 100, p1);
		CreateButton(5, new ImageIcon(inoseat), 330, 220, 120, 100, p1);
		CreateButton(6, new ImageIcon(inoseat), 40, 340, 120, 100, p1);
		CreateButton(7, new ImageIcon(inoseat), 185, 340, 120, 100, p1);
		CreateButton(8, new ImageIcon(inoseat), 330, 340, 120, 100, p1);
			
		b = a - count;
		
		useseat = new JLabel();
		useseat.setBounds(35, 10, 500, 80);
		useseat.setText("<html><font color=red>전 체 좌 석    " + a + "   /  사 용 가 능    " + b + " ");
		useseat.setFont(lafn);

		takeout = new JButton("Take Out");
		takeout.setBounds(190, 460, 120, 50);
		takeout.addActionListener(this);
		takeout.setFont(btfn);
		p1.add(takeout);

		back = new JButton("b a c k");
		back.setBounds(190, 460, 120, 50);
		back.addActionListener(this);
		back.setFont(btfn);
		p1.add(back);
		
		ta = new JTextArea();
		ta.setBounds(35, 90, 430, 360);
		ta.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK); 
		ta.setBorder(border);
		ta.setEditable(false);

		p1.setBackground(Color.WHITE);
		p1.add(ta);
		p1.add(useseat);
		p1.setVisible(true);
		seat.add(p1);
	}
	
	public static void orderNum() {
		try { // 파일 한 줄씩 읽기
			File file = new File("src/order/orders.txt");
			sc = new Scanner(file);
			while (sc.hasNextInt() == true) { // 파일에 int 형이 있는지 확인
				Cash.ordernum = sc.nextInt();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println(e);
		}
		if (Cash.ordernum == 999) {
			Cash.ordernum = 0;
		}
		if (Cash.ordernum == 0) {
			Cash.ordernum = (int) (Receipt.randou * 99) + 1; // 범위 0<=i<99 그러므로 +1
			try { // 파일에 쓰기
				File file = new File("src/order/orders.txt");
				BufferedWriter write = new BufferedWriter(new FileWriter(file));
				if (file.isFile() && file.canWrite()) {
					write.write(Integer.toString(Cash.ordernum));
					write.close();
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			Cash.ordernum += 1;
			try { // 파일에 쓰기
				File file = new File("src/order/orders.txt");
				BufferedWriter write = new BufferedWriter(new FileWriter(file));
				if (file.isFile() && file.canWrite()) {
					write.write(Integer.toString(Cash.ordernum));
					write.close();
				}
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	void btnClick(JButton btn, String text,int i) {
		try {
			if (btn == takeout) {
				result = JOptionPane.showConfirmDialog(null, "테이크아웃 하시겠습니까?", "알림", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					new Cash();
					seat.setVisible(false);
					count = 0;
				}
			}else {
			result = JOptionPane.showConfirmDialog(null, "이 좌석으로 선택하시겠습니까?", "알림", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				count++;
				btn.setIcon(new ImageIcon(ieatseat));
				btn.setText("<html>" + Receipt.i + "<br>" + dateFormat.format(date) + "</html>");
				inset = "<html>" + Receipt.i + "<br>" + dateFormat.format(date) + "&nbsp;" + dateFormat2.format(date2)
						+ "</html>";
				by = inset.getBytes();
				out = new FileOutputStream("src/order/"+text + ".txt");
				out.write(by);
				btn.setEnabled(false);
				tttt2(btn, text);
				Cash.tel = i;
				new Cash();
				seat.setVisible(false);
			}
		}
	} 	 catch (IOException e1) {

		}
	}
	
	static void tttt2(JButton btn, String text) {
		texit = new Timer();
		ttask = new TimerTask() {
			@Override
			public void run() {
//				btn.setIcon(ic);
				btn.setEnabled(true);
				try {
					inset = "";
					by = inset.getBytes();
					out = new FileOutputStream(text + ".txt");
					out.write(by);
					count--;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		texit.schedule(ttask, 10000); // 10초 뒤에
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Start.ck == 2) {
			if (e.getSource() == jb.get(0)) {
				btnClick(jb.get(0), "btn1",1);
			} else if (e.getSource() == jb.get(1)) {
				btnClick(jb.get(1), "btn2",2);
			} else if (e.getSource() == jb.get(2)) {
				btnClick(jb.get(2), "btn3",3);
			} else if (e.getSource() == jb.get(3)) {
				btnClick(jb.get(3), "btn4",4);
			} else if (e.getSource() == jb.get(4)) {
				btnClick(jb.get(4), "btn5",5);
			} else if (e.getSource() == jb.get(5)) {
				btnClick(jb.get(5), "btn6",6);
			} else if (e.getSource() == jb.get(6)) {
				btnClick(jb.get(6), "btn7",7);
			} else if (e.getSource() == jb.get(7)) {
				btnClick(jb.get(7), "btn8",8);
			} else if (e.getSource() == jb.get(8)) {
				btnClick(jb.get(8), "btn9",9);
			}

			if (e.getSource() == takeout) {
				btnClick(takeout, "cancel", 10);
			}
		} else if (Start.ck != 2) {
			if (e.getSource() == back) {
				seat.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "여기선 안됩니다.", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
