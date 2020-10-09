import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class GUI extends JFrame implements ActionListener{
	
	JPanel loginPanel = new JPanel();
	private static JLabel usernameLabel;
	private static JTextField usernameTextField;
	private static JLabel passwordLabel;
	private static JPasswordField passwordTextField;
	private static JButton loginButton;
	private static JButton openRegistrationWindow;
	private static JLabel loginStatusLabel;
	
	
	public GUI () {
		super("Login");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginPanel.setLayout(null);
		add(loginPanel);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 10, 80, 25);
		loginPanel.add(usernameLabel);
		
		usernameTextField = new JTextField(20);
		usernameTextField.setBounds(100, 10, 165, 25);
		loginPanel.add(usernameTextField);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 45, 80, 25);
		loginPanel.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(100, 45, 165, 25);
		loginPanel.add(passwordTextField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 100, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usernameTextField.getText().equals("Nathan") && passwordTextField.getText().equals("qwerty123")) {
					
					loginStatusLabel.setText("Login Successful");
					createApplicationWindow();
					dispose();
				}
				
			}
		});
		loginPanel.add(loginButton);
		
		openRegistrationWindow = new JButton("Register");
		openRegistrationWindow.setBounds(100, 100, 100, 25);
		openRegistrationWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				createRegistrationWindow();
			}
		});
		loginPanel.add(openRegistrationWindow);
		
		loginStatusLabel = new JLabel("");
		loginStatusLabel.setBounds(10, 120, 100, 25);
		loginPanel.add(loginStatusLabel);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		new GUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void createApplicationWindow(){
		
		JFrame applicationWindow = new JFrame();
		applicationWindow.setTitle("Cat Pictures App - A Place to View Cat Pictures!");
		applicationWindow.setSize(400, 400);
		applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		applicationWindow.setVisible(true);
	}
	
	public void createRegistrationWindow() {
		
		JFrame registrationWindow = new JFrame();
		registrationWindow.setTitle("Register an Account");
		registrationWindow.setSize(300, 200);
		registrationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		registrationWindow.setVisible(true);
	}

}
