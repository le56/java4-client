package Controller;

import java.io.IOException;

import View.LoginView;
import View.MainView;
import View.RegisterView;

public class Client {

	public static LoginView loginView;
	public static MainView mainView;
	public static RegisterView registerView;
	public static SocketHandle socketHandle;
	
	public enum View {
		LOGIN, MAINVIEW, REGISTER
	}

	public static void closeView(View viewName) {
		
		if (viewName != null) {
			switch (viewName) {
			case LOGIN:
				loginView.dispose();
				break;
			case MAINVIEW:
				mainView.dispose();
				break;
			case REGISTER:
				mainView.dispose();
				break;
			}
		}

	}
	
	public static void sendAccount(String account, String password) {
		String s1 = account + "," +password;
		String s2 = EnCryption.enCryption(s1);
		try {
			socketHandle.getDaOutputStream().writeUTF("request-login,"+s2);
			socketHandle.getDaOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void openView(View viewName) {
		if (viewName != null) {
			switch (viewName) {
			case LOGIN:
				loginView = new LoginView();
				loginView.setVisible(true);
				break;
			case MAINVIEW:
				mainView = new MainView();
				mainView.setVisible(true);
				break;
			case REGISTER:
				registerView = new RegisterView();
				registerView.setVisible(true);
				break;
			}
		}
	}
	public void initView() {
		loginView = new LoginView();
		loginView.setVisible(true);
		
		socketHandle = new SocketHandle();
		socketHandle.run();
	}
	public static void main(String[] args) {
		new Client().initView();
	}
}
