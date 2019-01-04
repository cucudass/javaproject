package Kitchen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Kitchen extends Network implements ActionListener{

	static final int Width = 1920;
	static final int Height = 1080;
	static int ornum,amountdata,no,count;
	private JFrame jframe;
	Font font = new Font("고딕", Font.BOLD, 18);
	private JPanel jp;
	private JLabel print1,print2,print3,print4,print5,print6,print7,print8; // 주문 상품
	private JButton finish; //제작 완료버튼
	private static JTextField ordernum; // 제작 완료 상품 주문번호 입력
	static KitDB kdb = new KitDB();
	private static String menudata,passdata;
	static JList ls1;
	static ArrayList<JLabel> printlist = new ArrayList<JLabel>();
	static ArrayList<JLabel> printlist1 = new ArrayList<JLabel>();
	static ArrayList<JLabel> printlist2 = new ArrayList<JLabel>();
	static ArrayList<JList> orderlist = new ArrayList<JList>();
	static ArrayList<JList> orderlist1 = new ArrayList<JList>();
	static DefaultListModel model,model1;
	ArrayList<JPanel> listpanel = new ArrayList<JPanel>();
	public Kitchen() {
		System.out.println("login");
		super.network();
		Kitchen_init();
		super.Reload();
	}
	private void CreateLabel(int num,String name,int x,int y,int wid,int hei) {
		printlist.add(num,new JLabel(name));
		printlist.get(num).setBounds(x, y, wid, hei);
		printlist.get(num).setOpaque(true);
		printlist.get(num).setFont(font);
		printlist.get(num).setBackground(Color.white);
		printlist.get(num).setHorizontalAlignment(JLabel.CENTER);;
		listpanel.get(num).add(printlist.get(num));
	}
	private void CreateLabel1(int num,String name,int x,int y,int wid,int hei) {
		printlist1.add(num,new JLabel(name));
		printlist1.get(num).setBounds(x, y, wid, hei);
		printlist1.get(num).setOpaque(true);
		printlist1.get(num).setFont(font);
		printlist1.get(num).setBackground(Color.black);
		printlist1.get(num).setHorizontalAlignment(JLabel.CENTER);;
		listpanel.get(num).add(printlist1.get(num));
	}
	private void CreateLabel2(int num,String name,int x,int y,int wid,int hei) {
		printlist2.add(num,new JLabel(name));
		printlist2.get(num).setBounds(x, y, wid, hei);
		printlist2.get(num).setOpaque(true);
		printlist2.get(num).setFont(font);
		printlist2.get(num).setBackground(Color.cyan);
		printlist2.get(num).setHorizontalAlignment(JLabel.CENTER);;
		listpanel.get(num).add(printlist2.get(num));
	}
	private void CreatePanel(int num,int x,int y,int wid,int hei) {
		listpanel.add(num,new JPanel());
		listpanel.get(num).setLayout(null);
		listpanel.get(num).setBorder(new TitledBorder(new LineBorder(Color.BLACK,1)));
		listpanel.get(num).setBackground(Color.white);
		listpanel.get(num).setBounds(x, y, wid, hei);
		jframe.add(listpanel.get(num));
	}
	private void CreateList(int num,int x,int y,int wid,int hei) {
		orderlist.add(num,new JList(new DefaultListModel()));
		orderlist.get(num).setBounds(x,y,wid,hei);
		orderlist.get(num).setOpaque(true);
		orderlist.get(num).setBorder(new TitledBorder(new LineBorder(Color.BLACK,1)));
		listpanel.get(num).add(orderlist.get(num));
	}
	private void CreateList1(int num,int x,int y,int wid,int hei) {
		orderlist1.add(num,new JList(new DefaultListModel()));
		orderlist1.get(num).setBounds(x,y,wid,hei);
		orderlist1.get(num).setOpaque(true);
		orderlist1.get(num).setBorder(new TitledBorder(new LineBorder(Color.BLACK,1)));
		listpanel.get(num).add(orderlist1.get(num));
	}
	private void Kitchen_init() {
		jframe = new JFrame();	
		jframe.setSize(Width,Height);
		jframe.setLocation(0, 0);
		jframe.setLayout(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreatePanel(0,0,0,480,500);
		CreatePanel(1,480,0,480,500);
		CreatePanel(2,960,0,480,500);
		CreatePanel(3,1440,0,480,500);
		CreatePanel(4,0,500,480,500);
		CreatePanel(5,480,500,480,500);
		CreatePanel(6,960,500,480,500);
		CreatePanel(7,1440,500,480,500);
		CreateLabel(0,"주문번호 : 0",80,50,300,50);
		CreateLabel(1,"주문번호 : 0",80,50,300,50);
		CreateLabel(2,"주문번호 : 0",80,50,300,50);
		CreateLabel(3,"주문번호 : 0",80,50,300,50);
		CreateLabel(4,"주문번호 : 0",80,50,300,50);
		CreateLabel(5,"주문번호 : 0",80,50,300,50);
		CreateLabel(6,"주문번호 : 0",80,50,300,50);
		CreateLabel(7,"주문번호 : 0",80,50,300,50);
		CreateLabel1(0,"메뉴",80,100,220,50);
		CreateLabel1(1,"메뉴",80,100,220,50);
		CreateLabel1(2,"메뉴",80,100,220,50);
		CreateLabel1(3,"메뉴",80,100,220,50);
		CreateLabel1(4,"메뉴",80,100,220,50);
		CreateLabel1(5,"메뉴",80,100,220,50);
		CreateLabel1(6,"메뉴",80,100,220,50);
		CreateLabel1(7,"메뉴",80,100,220,50);
		CreateLabel2(0,"갯수",300,100,100,50);
		CreateLabel2(1,"갯수",300,100,100,50);
		CreateLabel2(2,"갯수",300,100,100,50);
		CreateLabel2(3,"갯수",300,100,100,50);
		CreateLabel2(4,"갯수",300,100,100,50);
		CreateLabel2(5,"갯수",300,100,100,50);
		CreateLabel2(6,"갯수",300,100,100,50);
		CreateLabel2(7,"갯수",300,100,100,50);
		CreateList(0,80,150,220,250);
		CreateList(1,80,150,220,250);
		CreateList(2,80,150,220,250);
		CreateList(3,80,150,220,250);
		CreateList(4,80,150,220,250);
		CreateList(5,80,150,220,250);
		CreateList(6,80,150,220,250);
		CreateList(7,80,150,220,250);
		CreateList1(0,300,150,100,250);
		CreateList1(1,300,150,100,250);
		CreateList1(2,300,150,100,250);
		CreateList1(3,300,150,100,250);
		CreateList1(4,300,150,100,250);
		CreateList1(5,300,150,100,250);
		CreateList1(6,300,150,100,250);
		CreateList1(7,300,150,100,250);
		ordernum = new JTextField(10);
		ordernum.setBounds(860, 1000, 100, 50);
		ordernum.addActionListener(this);
		jframe.add(ordernum);
		finish = new JButton("제작완료");
		finish.setBounds(960,1000,120,50);
		finish.addActionListener(this);
		jframe.add(finish);
		jframe.setVisible(true);
	}
	static void Panel(int num) {
		ornum = 0;
		orderlist.get(num).removeAll();
		model = (DefaultListModel) orderlist.get(num).getModel();
		model1 = (DefaultListModel) orderlist1.get(num).getModel();
		if(model.isEmpty() == false) {
		for(int i=0;i<model.size();i++) {
			model.remove(i);
		}
		Panel(num);
		}else if(model1.isEmpty() == false) {
			for(int i=0;i<model1.size();i++) {
				model1.remove(i);
			}
		Panel(num);
		}
		else {
			kdb.SelNum(num+1);
			printlist.get(num).setText("주문번호 : "+ ornum);
			ArrayList arr = kdb.Selmenu(num+1);
			ArrayList arr1 = kdb.SelAmount(num+1);
			for(int i=0;i<arr.size();i++) {
				Data d = (Data) arr.get(i);
				menudata = d.getMenu();
				model.addElement(menudata);
			}
			for(int i =0;i<arr1.size();i++) {
				Data d = (Data) arr1.get(i);
				amountdata = d.getAmount();
				model1.addElement(Integer.toString(amountdata));
		}
	}
}
	static void Stock(int n){
		ArrayList a = kdb.Join(n);
		String str = "감자튀김";
		for(int i = 0; i<a.size(); i++) {
			Data d = (Data) a.get(i);
			passdata = d.getMenu();
			kdb.Check(passdata,n);
			kdb.InsertAmount(passdata);
			if(passdata.equals("불고기버거세트")) {
					passdata = "불고기";
					kdb.UpdateCur(str);
			}else if(passdata.equals("새우버거세트")) {
				passdata = "새우버거";
				kdb.UpdateCur(str);
			}else if(passdata.equals("치즈버거세트")) {
				passdata = "치즈버거";
				kdb.UpdateCur(str);
			}else if(passdata.equals("치킨버거세트")) {
				passdata = "치킨버거";
				kdb.UpdateCur(str);
			}else if(passdata.equals("콜라(Set)")) {
				passdata = "콜라";
			}else if(passdata.equals("콜라(SetL)")) {
				passdata = "콜라";
			}else if(passdata.equals("불고기버거")) {
				passdata = "불고기";
			}else if(passdata.equals("사이다(Set)")) {
				passdata = "사이다";
			}else if(passdata.equals("사이다(SetL)")) {
				passdata = "사이다";
			}else if(passdata.equals("환타(Set)")) {
				passdata = "환타";
			}else if(passdata.equals("환타(SetL)")) {
				passdata = "환타";
			}else if(passdata.equals("커피(Set)")) {
				passdata = "커피";
			}else if(passdata.equals("커피(SetL)")) {
				passdata = "커피";
			}
			kdb.UpdateCur(passdata);
			kdb.UpdateSetting("orders");
		}	
	}
	public static void main(String[] args) {
		new Kitchen();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == finish || e.getSource() == ordernum) {
			int nu = Integer.parseInt(ordernum.getText());
			if(nu == 0){}else{
			kdb.SelNo(nu);
			Stock(nu);
			kdb.Delete(nu);
			kdb.InsertFin(nu);
			kdb.ReSetNum(no);
			super.send_message("fin/Finish");
			ordernum.setText("");
			super.Reload();
			}
		}
	}
}

