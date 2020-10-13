import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{
	
	// constants
	private final static int LENGTH_MIN = 6;
	private final static int LENGTH_MAX = 12;
	private final static String USERNAME_FILE = ("C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\usernames.txt");
	private final static String PASSWORD_FILE = ("C:\\Users\\220037\\git\\CatPicturesApp\\CatPicturesApp\\passwords.txt");
	
	
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
			
			File usernames = new File(USERNAME_FILE);
			File passwords = new File(PASSWORD_FILE);
			
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
			
			//temporary 
			BufferedWriter usernameWriter;
			BufferedWriter passwordWriter;
			// end of temporary
			
			//temporary
			usernameWriter = new BufferedWriter(new FileWriter(
					USERNAME_FILE, true));
			passwordWriter = new BufferedWriter(new FileWriter(
					PASSWORD_FILE, true));
			
			usernameWriter.write("Nathan");
			usernameWriter.write("\r\n"); // write new line
			
			passwordWriter.write("qwerty123");
			passwordWriter.write("\r\n"); // write new line
			
			usernameWriter.close();
			passwordWriter.close();
			
			// end of temporary
			
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
				
				boolean usernameExists = false;
				boolean passwordExists = false;
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(
							USERNAME_FILE));
					List<String> usernameList = new ArrayList<>();
					
					while (reader.readLine() != null) {
						usernameList.add(reader.readLine());
					}
					
					if (usernameList.contains(usernameTextField.getText())){
						usernameExists = true;
					}
					
					BufferedReader reader1 = new BufferedReader(new FileReader(
							PASSWORD_FILE));
					List<String> passwordList = new ArrayList<>();
					
					while (reader1.readLine() != null) {
						passwordList.add(reader1.readLine());
					}
					
					if (passwordList.contains(passwordTextField.getText())){
						passwordExists = true;
					}
					
					if (usernameExists == true && passwordExists == true) {
						loginStatusLabel.setText("Login Successful");
						createApplicationWindow();
						dispose();
					}else {
						loginStatusLabel.setText("Login Failed, Username or Password is Incorrect");
						usernameTextField.setText("");
						passwordTextField.setText("");
					}
					
				}catch (IOException e1){
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
	
	
	private static boolean isPasswordValid(String password) {
		/*Password must: 
		 *be between 6 and 12 characters
		 *contain at least 1 special character
		 *contain at least 1 number
		 *contain at least 1 uppercase letter */
		
		boolean validity = false;
		if (password.length() < LENGTH_MIN && password.length() > LENGTH_MAX) {
			validity = false;	
		}else {
			int numNums = 0;
			int numUppercase = 0;
			int numSpecial = 0;
			for (int i = 0; i < password.length(); i++) {
				char c = password.charAt(i);
				if (Character.isUpperCase(c)) numUppercase ++;
				else if (Character.isDigit(c)) numNums ++;
				else if (String.valueOf(c).matches("[^a-zA-Z0-9\\s]")) numSpecial ++;
			}
			
			if (numNums >= 1 && numUppercase >= 1 && numSpecial >= 1) {validity = true;}else {validity = false;}
			
		}
		
		return validity;	
	}
	
	private static boolean isUsernameTaken(String username) {
		boolean validity = false;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					USERNAME_FILE));
			
			String usernameLine = reader.readLine();
			
			}catch (IOException e) {
			e.printStackTrace();
		}
		return validity;
	}
	
	
	
	private static int numOfLinesInFile(String file) throws IOException{
		int lines = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					file));
			
			while(reader.readLine() != null) lines++;
			reader.close();
			
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		
		
		return lines;
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
