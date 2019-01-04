package elecboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LEDboard extends Network{

	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEGIHT = 500;
	static BoardDB db;
	JFrame jf;
	JPanel jp;
	static List ls1,ls2;
	JLabel lb1,lb2;
	Font lbfnt = new Font("돋움",Font.PLAIN,30);
	Font lsfnt = new Font("굴림",Font.BOLD,20);
	Color c = new Color(0xffffff);
	
	static Timer texit;
	static TimerTask ttask;
	
	public LEDboard() {
		super.network();
		jf = new JFrame();
		jf.setResizable(false);
		jf.setSize(SCREEN_WIDTH, SCREEN_HEGIHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(null);
		jf.setLocation(1270, 50);
		Panel();
		List();
		FinList();
		jf.setVisible(true);
	}
	
	void Panel() {
		jp = new JPanel();
		jp.setSize(500, 500);
		jp.setLayout(null);
		jp.setBackground(Color.black);
		lb1 = new JLabel();
		lb2 = new JLabel();
		lb1.setBounds(75, 20, 100, 40);
		lb2.setBounds(310, 20, 100, 40);
		
		lb1.setText("준비 중");
		lb2.setText("완료");
		lb1.setFont(lbfnt);
		lb2.setFont(lbfnt);
		lb1.setForeground(c);
		lb2.setForeground(c);
		
		lb1.setVerticalAlignment(SwingConstants.CENTER);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setVerticalAlignment(SwingConstants.CENTER);
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		ls1 = new List();
		ls2 = new List();
		ls1.setBounds(20, 80, 220, 370);
		ls2.setBounds(255, 80, 220, 370);
		ls1.setBackground(Color.black);
		ls2.setBackground(Color.black);
		ls1.setFocusable(false);
		ls2.setFocusable(false);
		ls1.setEnabled(false);
		ls2.setEnabled(false);
		ls1.setFont(lsfnt);
		ls2.setFont(lsfnt);
		
		jp.add(lb1);jp.add(lb2);jp.add(ls1);jp.add(ls2);
		jp.setVisible(true);
		jf.add(jp);
	}
	static void List() {
		ls1.removeAll();
		db = new BoardDB();
		int str;
		ArrayList a = db.Selnum();
		for(int i=0; i<a.size();i++) {
			Data d = (Data) a.get(i);
			str = d.getNo();
			ls1.add(Integer.toString(str));
		}
		for(int i=0;i<ls1.getItemCount();i++){
			if(ls1.getItem(i).equals("0")){
				ls1.remove(i);
			}
		}
	}
	static void FinList() {
		ls2.removeAll();
		db = new BoardDB();
		int str;
		ArrayList a = db.Finish();
		for(int i=0; i<a.size();i++) {
			Data d = (Data) a.get(i);
			str = d.getNo();
			ls2.add(Integer.toString(str));
		}
	}
	
	static void tttt() {
		texit = new Timer();
		ttask = new TimerTask() {
			@Override
			public void run() {
				List();
				FinList();
			}
		};
		texit.schedule(ttask, 1000,5000); //4.5초 뒤에 자동종료
		}
	public static void main(String[] args) {
		new LEDboard();
	}
}
