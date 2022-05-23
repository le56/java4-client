package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketHandle {

	private BufferedReader is;
	private BufferedWriter os;

	private Socket socket;

	public void run() {
		try {
			socket = new Socket("localhost", 1234);

			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String message;

			while (true) {
				message = is.readLine();
				if (message.equals("End")) {
					break;
				}
				String[] mesageSlip = message.split(",");
				// login, duong, 123
				// login-succses, 3, le khanh duong
				// login-false, id =null, name =null
				if (mesageSlip[0].equals("login-succses")) {
					System.out.println("Dang nhap thanh cong");
					Client.closeView(Client.View.LOGIN);
					Client.openView(Client.View.MAINVIEW);
				}
				if (mesageSlip[0].equals("login-false")) {
					System.out.println("Dang nhap that bai");
					Client.openView(Client.View.REGISTER);
				}

			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

}
