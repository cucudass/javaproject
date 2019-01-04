package Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame{
	// Server Frame
	private JPanel contentPane;
	static JTextArea textArea = new JTextArea();

	// Network Source
	private ServerSocket serverSocket;
	private Socket socket;
	private int port = 3307;
	UserInfo userInfo;
	static Vector vector_user = new Vector();
	
	// ETC Source
	private StringTokenizer stringTokenizer;
	
	private void server_start(){
		System.out.println("start server 포트 : "+port);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트", "알림", JOptionPane.ERROR_MESSAGE);
		}
		if(serverSocket != null){ // socket ok!
			connection();
		}
			
	}
	public class Socket_thread implements Runnable{
		public void run() {
			while(true){
				try { //wait client
					textArea.append("사용자 접속 대기중\n");
					socket = serverSocket.accept();
					userInfo = new UserInfo(socket);
					userInfo.start();
				} catch (IOException e) {
					break;
				}
			}
		}
	}
	private void connection(){
		Thread thread = new Thread(new Socket_thread());
		thread.start();
	}
	private void init(){ // Server GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 378, 403);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		this.setVisible(true);
	}
	public Server(){
		init();	//GUI
		server_start();
	}
	public static void main(String[] args) {
		new Server();
	}
}
