import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{
	
	
	// login window element declaration
	JPanel loginPanel = new JPanel();
	private static JLabel usernameLabel;
	private static JTextField usernameTextField;
	private static JLabel passwordLabel;
	private static JPasswordField passwordTextField;
	private static JButton loginButton;
	private static JButton openRegistrationWindow;
	private static JLabel loginStatusLabel;
	
	//login window 
	public GUI () {
		super("Login");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		try {
			
			File usernames = new File("C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\usernames.txt");
			File passwords = new File("C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\passwords.txt");
			
			// create username file
			if (usernames.createNewFile()) {
				System.out.println("File Created: " + usernames.getName());
				
			}else {
				System.out.println("Username File Already Created");
			}
			
			// create password file
			if (passwords.createNewFile()) {
				System.out.println("File Created: " + passwords.getName());
		
			}else {
				System.out.println("Password File Already Created");
			}
			
			
		}catch (IOException e) {
			System.out.println("An Error Occurred...");
			e.printStackTrace();
		}
		
		
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
				loginStatusLabel.setText("");
				BufferedReader usernameReader;
				BufferedReader passwordReader;
				try{	
					usernameReader = new BufferedReader(new FileReader(
							"C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\usernames.txt"));
					passwordReader = new BufferedReader(new FileReader(
							"C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\passwords.txt"));
					
					String usernameLine = usernameReader.readLine();
					String passwordLine = passwordReader.readLine();
					boolean usernameExists = false;
					boolean passwordExists = false;
					while(usernameLine != null) {
						if (usernameTextField.getText() == usernameLine) {
							usernameExists = true;
						}else {
							usernameLine = usernameReader.readLine();
						}
					}
					while (passwordLine != null) {
						if (passwordTextField.getText() == passwordLine) {
							passwordExists = true;
						}else {
							passwordLine = passwordReader.readLine();						}
					}
					if (usernameExists && passwordExists) {
						loginStatusLabel.setText("Login Successful");
						createApplicationWindow();
						dispose();
					}else {
						loginStatusLabel.setText("Login Failed, Try Again or Register an Account");
					}
				}catch (IOException e1) {
					e1.printStackTrace();
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
		loginStatusLabel.setBounds(10, 120, 300, 25);
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
		
		JDialog registrationWindow = new JDialog();
		JPanel registrationWindowPanel = new JPanel();
		JLabel newUsernameLabel;
		JTextField newUsernameTextField;
		JLabel newPasswordLabelOne;
		JPasswordField newPasswordTextFieldOne;
		JLabel newPasswordLabelTwo;
		JPasswordField newPasswordTextFieldTwo;
		JButton registerNewAccount;
		JLabel registrationStatusLabel;
		
		
		registrationWindow.setTitle("Register an Account");
		registrationWindow.setSize(400, 250);
		registrationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registrationWindow.setResizable(false);
		registrationWindow.setModal(true);
		registrationWindow.setAlwaysOnTop(true);
		registrationWindow.setModalityType(ModalityType.APPLICATION_MODAL);
		
		registrationWindowPanel.setLayout(null);
		registrationWindow.add(registrationWindowPanel);
		
		newUsernameLabel = new JLabel("Enter Username:");
		newUsernameLabel.setBounds(10, 10, 120, 25);
		registrationWindowPanel.add(newUsernameLabel);
		
		newUsernameTextField = new JTextField(20);
		newUsernameTextField.setBounds(170, 10, 165, 25);
		registrationWindowPanel.add(newUsernameTextField);
		
		newPasswordLabelOne = new JLabel("Enter Password:");
		newPasswordLabelOne.setBounds(10, 45, 120, 25);
		registrationWindowPanel.add(newPasswordLabelOne);
		
		newPasswordTextFieldOne = new JPasswordField();
		newPasswordTextFieldOne.setBounds(170, 45, 165, 25);
		registrationWindowPanel.add(newPasswordTextFieldOne);
		
		newPasswordLabelTwo = new JLabel("Enter Password Again:");
		newPasswordLabelTwo.setBounds(10, 80, 150, 25);
		registrationWindowPanel.add(newPasswordLabelTwo);
		
		newPasswordTextFieldTwo = new JPasswordField();
		newPasswordTextFieldTwo.setBounds(170, 80, 165, 25);
		registrationWindowPanel.add(newPasswordTextFieldTwo);
		
		registerNewAccount = new JButton("Register Account");
		registerNewAccount.setBounds(10, 130, 150, 25);
		registerNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		registrationWindowPanel.add(registerNewAccount);
		
		registrationStatusLabel = new JLabel("");
		registrationStatusLabel.setBounds(10, 170, 300, 25);
		registrationWindowPanel.add(registrationStatusLabel);
		
		registrationWindow.setVisible(true);
	}

}
