package Counter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cash extends Network implements ActionListener {
	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEGITH = 477;
	DBDao db;
	Data data = new Data();
	static JFrame cash;
	private JButton ftpaper, tpaper, fpaper, opaper, last, back;
	private JLabel menu;
	static JLabel tax;
	private JPanel p1, p2, p3, p4;

	static List clist;
	static String passdata,inset;
	static int sum,count,tel,checkno,amount,ordernum;
	Font lstfn = new Font("고딕",Font.BOLD, 15);
	Image pt1,pt2;
	JLabel prb1,prb2;
	static FileOutputStream out = null;
	static byte[] by;
	List list;
	JLabel zz;
	static Image iftpaper = new ImageIcon(Start.class.getResource("../images/오만원.jpg")).getImage();
	static Image itpaper = new ImageIcon(Start.class.getResource("../images/만원.jpg")).getImage();
	static Image ifpaper = new ImageIcon(Start.class.getResource("../images/오천원.jpg")).getImage();
	static Image iopaper =  new ImageIcon(Start.class.getResource("../images/천원.jpg")).getImage();
	static Image iback2 = new ImageIcon(Start.class.getResource("../images/취소2.png")).getImage();
	
	ArrayList<JButton> jb = new ArrayList<JButton>();
	
	void CreateButton(int num, ImageIcon name, int x, int y, int wid, int hei, JPanel p) {
		jb.add(num,new JButton(name));
		jb.get(num).setBounds(x, y, wid, hei);
		jb.get(num).addActionListener(this);
		jb.get(num).setBorderPainted(false);
		p.add(jb.get(num));
	}
	public Cash() {
		Seat.orderNum();
		System.out.println(ordernum);
		db = new DBDao();
		db.Maxno();
		cash = new JFrame();
		cash.setSize(SCREEN_WIDTH, SCREEN_HEGITH);
		cash.setTitle("결제하기");
		cash.setResizable(false);
		cash.setLocation(650, 250);
		cash.setLayout(null);
		P1();
		P2();
		P3();
		cash.setVisible(true);
		super.network();
	}
	public void P1() { //주문 메뉴 출력 리스트
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 0, SCREEN_WIDTH, 200);
		clist = new List();
		clist.setBounds(0, 0, SCREEN_WIDTH, 200);
		clist.setFont(lstfn);
		for(int i = 0; i < Counter.Menu.p6list.getItemCount(); i++) {
			String str = Counter.Menu.p6list.getItem(i);
			System.out.println(str);
			clist.add(str);	
		}
		p1.add(clist);
		cash.add(p1);
	}
	public void P2() { // 금액 출력 패널
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(0, 200, SCREEN_WIDTH, 50);
		this.sum = Menu.sum;
		tax = new JLabel("<html><font size=6><b> 결제 금액 : " + sum + "원 </b></font></html>");
		data.setTaxprice(sum);
		tax.setBounds(0, 0, SCREEN_WIDTH, 50);
		tax.setBackground(Color.white);
		p2.add(tax);
		cash.add(p2);
	}
	public void P3() { // 지폐 이미지 패널
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setBounds(0, 250, 500, 200);
		
		CreateButton(0, new ImageIcon(iftpaper), 0, 0, 250, 75, p3);
		CreateButton(1, new ImageIcon(itpaper), 250, 0, 250, 75, p3);
		CreateButton(2, new ImageIcon(ifpaper), 0, 75, 250, 75, p3);
		CreateButton(3, new ImageIcon(iopaper), 250, 75, 250, 75, p3);
		CreateButton(4, new ImageIcon(iback2), 0, 150, 250, 50, p3);
		jb.get(4).setBackground(Color.lightGray);
		
		last = new JButton("결제");
		last.setBounds(250, 150, 250, 50);
		last.setEnabled(false);
		last.addActionListener(this);
		last.setFont(lstfn);
		
		p3.add(last);
		cash.add(p3);
	}
	public void PassCheck() {  //값 증가
		db = new DBDao();
		ArrayList a = db.DataSel();
		for(int i =0; i< a.size();i++){
			Data d = (Data) a.get(i);
			passdata = d.getMenu();
			amount = d.getAmount();
			db.InsertKt(checkno+1, ordernum, passdata, amount);
		}
	}
	public void Back() throws IOException {
		System.out.println(count);
		if(Seat.count <= 0) {
			Seat.count = 0;
		}else {
			Seat.count--;
		}
			inset = "";
			by = inset.getBytes();
			out = new FileOutputStream("btn" + tel + ".txt");
			out.write(by);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb.get(4)) {
//			db = new DBDao(); 
//			db.DeleteAll();
			try {
				Back();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			cash.setVisible(false);
		}
		if(e.getSource()==last) { //결제 이벤트
			db = new DBDao();
			PassCheck();
			clist.clear();
			Receipt.i = Cash.ordernum;
			Menu.DeleteAll();
			this.sum = Menu.sum;
			tax.setText("<html><font size=8><b> 결제 금액 : " + sum + "원 </b></font></html>");
			db.DeleteAll();
			cash.setVisible(false);
			Counter.Menu.menu.setVisible(false);
			Counter.Start.first.setVisible(true);
			super.send_message("cash/완성");
			Receipt rec = new Receipt();
		}
		// 돈 계산 이벤트
		if(e.getSource() == jb.get(0)) {
			data.setTaxprice(data.getTaxprice() - 50000);
			tax.setText("<html><font size=6><b> 결제 금액 : " + Integer.toString(data.getTaxprice()) + "원 </b></font></html>");
		}
		if(e.getSource() == jb.get(1)) {
			data.setTaxprice(data.getTaxprice() - 10000);
			tax.setText("<html><font size=6><b> 결제 금액 : " + Integer.toString(data.getTaxprice()) + "원 </b></font></html>");
		}
		if(e.getSource() == jb.get(2)) {
			data.setTaxprice(data.getTaxprice() - 5000);
			tax.setText("<html><font size=6><b> 결제 금액 : " + Integer.toString(data.getTaxprice()) + "원 </b></font></html>");
		}
		if(e.getSource() == jb.get(3)) {
			data.setTaxprice(data.getTaxprice() - 1000);
			tax.setText("<html><font size=6><b> 결제 금액 : " + Integer.toString(data.getTaxprice()) + "원 </b></font></html>");
		}
		if(data.getTaxprice() <= 0) { //버튼 활성화 비활성화 이벤트
			jb.get(0).setEnabled(false);
			jb.get(1).setEnabled(false);
			jb.get(2).setEnabled(false);
			jb.get(3).setEnabled(false);
			last.setEnabled(true);
			tax.setText("<html><font size=6><b> 거스름돈 : " + Integer.toString(Math.abs(data.getTaxprice())) +"원 </b></font></html>");
		}
	}
}