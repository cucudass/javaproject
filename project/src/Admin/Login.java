package Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	ManagerDB db;
	AdminData add;
	static JFrame log;
	
	JPanel p = new JPanel();
	JLabel Id, Pw = new JLabel();
	JButton logn, Cancel = new JButton();
	static JTextField Idtext = new JTextField();
	static JPasswordField  Pwtext = new JPasswordField();
	static int logdata;
	StockT st;Admin ad;
	public Login() {
		log = new JFrame();
		log.setTitle("관리자 로그인");
		log.setSize(320, 275);
		log.setLocation(700,350);
		log.setResizable(false);
		p.setLayout(null);
		logn = new JButton("로그인");
		logn.setBounds(40, 144, 102, 33);
		logn.addActionListener(this);
		Cancel = new JButton("취소");
		Cancel.setBounds(154, 144, 102, 33);
		Cancel.addActionListener(this);
		Id = new JLabel("ID");
		Id.setBounds(40, 48, 67, 25);
		Pw = new JLabel("Passwd");
		Pw.setBounds(40, 95, 67, 25);
		Idtext = new JTextField(15);
		Idtext.setBounds(131, 55, 126, 31);
		Pwtext = new JPasswordField(15);
		Pwtext.setBounds(131, 92, 126, 31);
		p.add(logn);
		p.add(Cancel);
		p.add(Id);
		p.add(Pw);
		p.add(Idtext);
		p.add(Pwtext);
		
		log.add(p);
		log.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancel) {
			log.setVisible(false);
		}
		if (e.getSource() == logn) {
			db = new ManagerDB();
			add = new AdminData();
			db.LoginCheck();
			System.out.println(logdata);
			if(logdata == 1) {
			st = new StockT();
			ad = new Admin();
			JOptionPane.showMessageDialog(null, "발주는 30%이하인 재고만 가능합니다.","알림!",JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.","로그인 실패!",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void main(String args[]) {
		new Login();
	}
}
