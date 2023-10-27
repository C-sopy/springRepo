package myjava.net;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyClient extends JFrame {

	private JButton btn1;
	private JTextField text;
	private JTextArea context;
	private JScrollPane span;
	private JPanel p1, p2;
	private InputStream input;
	private OutputStream output;
	private PrintWriter pw;
	private BufferedReader br;

	private Socket sk;

	public MyClient() throws HeadlessException {

		try {
			sk = new Socket("localhost", 9080);
			pw = new PrintWriter(sk.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(sk.getInputStream()));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("MonChat");

		p1 = new JPanel();
		context = new JTextArea();
		context.setColumns(20);
		context.setRows(5);
		span = new JScrollPane(context);
		p1.add(context);
		add(p1); // jframe�� add

		p2 = new JPanel();
		btn1 = new JButton("Click !");
		text = new JTextField(10);
		p2.add(text);
		p2.add(btn1);
		add(p2, BorderLayout.SOUTH);

		setBounds(100, 100, 300, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â�ݱ�
		setVisible(true); // â show

		// event
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String textv = text.getText().trim();
//				System.out.println("text: " + textv);
				//context.append(textv + "\n");
				// ������ ����� �������� ��Ʈ���� ������.
				StringBuffer sb = new StringBuffer();
		        String msg = text.getText().trim();
		        //talk/all/nickName/say
		        sb.append("talk/all/").append("sop");
		        sb.append("/").append(msg);
		        System.out.println("���� :" + sb);
		        //������ �����͸� ����
		        pw.println(sb.toString());
			}
		});
		
		//response
		new Thread(new Runnable() { // thread�� ������� ���� ���� : �ڹٴ� ���ϻ���̱� ����(���� �̹� jframe����� �޴� ����)
			private BufferedReader br = null ;
			@Override
			public void run() {
				try {
					br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
					while(true) {
						String msg = br.readLine();
						context.append(msg+"\n");
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		// ===============================

	}

	public static void main(String[] args) {
		new MyClient();

	}
}
