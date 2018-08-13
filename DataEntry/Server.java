import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

public class Server
{
	ServerSocket ss;
	Socket s;
	ObjectInputStream dis;
	ArrayList v = new ArrayList();

	public Server()
	{

		try
		{
			ss = new ServerSocket(18);

			while(true)
			{
				System.out.println("Server Running");

				s = ss.accept();
				v.add(s);

				//System.out.println("Client Connected");
				Runnable r = new MyThread(s,v);
				Thread t = new Thread(r);
				t.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public static void main(String s[])
	{
		new Server();
	}

}


////////////

class MyThread implements Runnable
{
	Socket s;
	ArrayList v;

	MyThread(Socket s,ArrayList v)
	{
		this.s = s;
		this.v = v;
	}

	public void run()
	{
		try
		{
			ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

			String str;
			do
			{
				Account z = (Account)dis.readObject();
				System.out.println(z);
				z.show();

				Design2 d2 = (Design2)dis.readObject();
				System.out.println(d2);
				System.out.println(d2.z);

				d2.update();

			}while(true);
		}
		catch(Exception e){}
	}


}