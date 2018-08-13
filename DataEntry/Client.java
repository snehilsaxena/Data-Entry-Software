import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

public class Client extends MouseAdapter implements ActionListener
{
	Socket s;
	Design2 d2 ;
	boolean sent = false ;

	FileOutputStream fw;
	FileInputStream fr;
	ObjectOutputStream fow;
	ObjectInputStream fir;
	Append afow;
	File ff;
	boolean connect = false;

	public Client()
	{

			try
			{
				s = new Socket("localhost",18);
				connect = true;
			}
			catch(Exception e)
			{
				System.out.println(e);

			}


			if(connect)
			{

				d2 = new Design2();
				d2.draw();

				d2.modify.addActionListener(this);
				d2.add.addActionListener(this);
				d2.delete.addActionListener(this);
				d2.first.addActionListener(this);
				d2.last.addActionListener(this);
				d2.previous.addActionListener(this);
				d2.next.addActionListener(this);
				d2.confirm.addActionListener(this);
				d2.cancel.addActionListener(this);

				d2.table.addMouseListener(this);

				ff = new File(".//DataEntry.txt");

				try
				{
					fw = new FileOutputStream(".//DataEntry.txt",true);
					fr = new FileInputStream(".//DataEntry.txt");
					fir = new ObjectInputStream(fr);
				}
				catch(Exception e){}

				int nn = 0,i=0;
				do
				{
					try
					{
						Account tbt = (Account)fir.readObject();
						//System.out.println("kkkkkkkkkk");
						//System.out.println(i);
						d2.model.addRow(new Object[]{tbt.name,tbt.accNo,tbt.salary,tbt.mobNo});
						//System.out.println("sssssssssskkkkkkkkkk");
						i++;
					}
					catch(Exception e)
					{
						nn = 1;
						System.out.println(i);
					}
				}while(nn != 1);
			}



	}

	public static void main(String s[])
	{
		new Client();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== d2.confirm)
		{
			if(d2.ad)
			{
				int b = Integer.parseInt(d2.mob_Notf.getText());
				int c = Integer.parseInt(d2.salarytf.getText());

				Account ac = new Account(d2.nametf.getText(),d2.acc_Notf.getText(),Integer.parseInt(d2.salarytf.getText()),Integer.parseInt(d2.mob_Notf.getText()));

				int dupflag, sf;
				sf = 0;
				dupflag = 0;

				for(int i=0; i<d2.table.getRowCount(); i++)
				{
					if(d2.nametf.getText().trim().toLowerCase().equals(d2.model.getValueAt(i,0).toString().trim().toLowerCase()))
					{
						dupflag = 1;
						d2.table.setRowSelectionInterval(i,i);
						d2.rowSelection = i;
					}
				}

				if(dupflag == 1)
				{
					new JOptionPane("Record Already Exists",JOptionPane.INFORMATION_MESSAGE).createDialog("Error").setVisible(true);
				}

				else if(dupflag == 0)
				{
					if(!d2.stream)
					{
						try
						{
							//s = new Socket("localhost",18);
							//System.out.println("ssssss");
							fow = new ObjectOutputStream(new FileOutputStream(".//DataEntry.txt",true));
							fow.writeObject(ac);
							fow.close();
						}
						catch(Exception exc){System.out.println("Error in writing");}

						d2.update();
					}
					else
					{
						try
						{
							//s = new Socket("localhost",18);
							//System.out.println("zzzzzzz");
							afow = new Append(new FileOutputStream(".//DataEntry.txt",true));
							afow.writeObject(ac);
							afow.close();
						}
						catch(Exception exc){System.out.println("Error in writing");}

						d2.update();
					}
				}

				//d2.model.addRow(new Object[]{d2.nametf.getText(),d2.acc_Notf.getText(),Integer.parseInt(d2.mob_Notf.getText()),Integer.parseInt(d2.salarytf.getText())});

				/*try
				{
					s = new Socket("localhost",18);

					fow = new ObjectOutputStream(s.getOutputStream());

					fow.writeObject(e1);

					d2.update();
					fow.writeObject(d2);

					fow.close();
				}
				catch(Exception e1)
				{
					System.out.println(e1);
				}*/

				d2.ad = false;

			}

			if(d2.del)
			{
				int i = 0, k;
				String delo = null;
				k = d2.table.getRowCount();

				Account akup[] = new Account[k-1];
				Account eb = null;
				delo = d2.model.getValueAt(d2.table.getSelectedRow(),0).toString();
				int fs;
				fs = 0;

				try
				{
					fir = new ObjectInputStream(new FileInputStream(".//DataEntry.txt"));
				}
				catch(Exception ec)
				{
					System.out.println("IP Error");
				}

				do
				{
					try
					{
						eb = (Account)fir.readObject();

						if(!(eb.name.equals(delo)))
							akup[i++] = eb;
					}
					catch(Exception ed)
					{
						fs = 1;
					}
				}while(fs == 0);

				try
				{
					FileOutputStream fos = new FileOutputStream(".//DataEntry.txt");
					fos.close();

					fow = new ObjectOutputStream(new FileOutputStream(".//DataEntry.txt",true));
					afow = new Append(new FileOutputStream(".//DataEntry.txt",true));

					fow.writeObject(akup[0]);

					for(i=1; i<k-1; i++)
						afow.writeObject(akup[i]);
				}
				catch(Exception ee)
				{
					System.out.println("Error in Writing Object");
				}


				d2.model.removeRow(d2.table.getSelectedRow());
				d2.del = false;

				try
				{
					if(k == 1)
						new FileOutputStream(".//DataEntry.txt").close();
				}
				catch(Exception ef)
				{
				}
			}

			if(d2.mod)
			{
				int i,k,fs;

				k = d2.table.getRowCount();
				Account bkup[] = new Account[k];
				Account eb = null;
				String mstr;
				mstr = d2.model.getValueAt(d2.table.getSelectedRow(),0).toString();

				try
				{
					fir = new ObjectInputStream(new FileInputStream(".//DataEntry.txt"));
				}
				catch(Exception eg)
				{
					System.out.println("IP Error");
				}

				i = 0; fs = 0;

				do
				{
					try
					{
						eb = (Account)fir.readObject();

						if(!(eb.name.equals(mstr)))
							bkup[i++] = eb;
						else
						{
							eb.name = d2.nametf.getText();
							eb.accNo = d2.acc_No.getText();
							eb.salary = (int)new Float(d2.salary.getText()).floatValue();
							eb.mobNo = (int)new Float(d2.mob_No.getText()).floatValue();
							bkup[i++] = eb;
						}
					}
					catch(Exception ei)
					{
						fs = 1;
					}
				}while(fs == 0);

				try
				{
					FileOutputStream fos = new FileOutputStream(".//DataEntry.txt");
					fos.close();

					fow = new ObjectOutputStream(new FileOutputStream(".//DataEntry.txt",true));
					afow = new Append(new FileOutputStream(".//DataEntry.txt",true));

					fow.writeObject(bkup[0]);

					for(i=1; i<k-1; i++)
						afow.writeObject(bkup[i]);
				}
				catch(Exception ej)
				{
					System.out.println("Error in Writing Object");
				}


				d2.model.setValueAt(d2.nametf.getText(),d2.table.getSelectedRow(),0);
				d2.model.setValueAt(d2.acc_Notf.getText(),d2.table.getSelectedRow(),1);
				d2.model.setValueAt(d2.mob_Notf.getText(),d2.table.getSelectedRow(),2);
				d2.model.setValueAt(d2.salarytf.getText(),d2.table.getSelectedRow(),3);


			}

			d2.nametf.setText("");
			d2.acc_Notf.setText("");
			d2.mob_Notf.setText("");
			d2.salarytf.setText("");

			d2.nametf.setEditable(false);
			d2.acc_Notf.setEditable(false);
			d2.mob_Notf.setEditable(false);
			d2.salarytf.setEditable(false);

			d2.confirm.setVisible(false);
			d2.cancel.setVisible(false);
		}

		else if(e.getSource()== d2.cancel)
		{
			d2.cancel.setVisible(false);
			d2.confirm.setVisible(false);

			d2.nametf.setEditable(false);
			d2.acc_Notf.setEditable(false);
			d2.mob_Notf.setEditable(false);
			d2.salarytf.setEditable(false);
		}

		else if(e.getSource()== d2.modify)
		{
			d2.nametf.setEditable(true);
			d2.acc_Notf.setEditable(true);
			d2.mob_Notf.setEditable(true);
			d2.salarytf.setEditable(true);

			d2.confirm.setVisible(true);
			d2.cancel.setVisible(true);

			d2.nametf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),0).toString());
			d2.acc_Notf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),1).toString());
			d2.mob_Notf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),2).toString());
			d2.salarytf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),3).toString());

			d2.ad = false;
			d2.del = false;
			d2.mod = true;
		}

		else if(e.getSource()== d2.add)
		{
			d2.nametf.setEditable(true);
			d2.acc_Notf.setEditable(true);
			d2.mob_Notf.setEditable(true);
			d2.salarytf.setEditable(true);

			d2.confirm.setVisible(true);
			d2.cancel.setVisible(true);

			d2.ad = true;
			d2.del = false;
			d2.mod = false;

			try
			{
				fir = new ObjectInputStream(new FileInputStream(".//DataEntry.txt"));
				Account ac = (Account)fir.readObject();
				d2.stream = true;
			}
			catch(Exception ea)
			{
				d2.stream = false;
			}
		}

		else if(e.getSource()== d2.delete)
		{
			d2.ad = false;
			d2.del = true;
			d2.mod = false;

			d2.confirm.setVisible(true);
			d2.cancel.setVisible(true);
		}

		else if(e.getSource()== d2.first)
		{
			d2.firstCall();
		}

		else if(e.getSource()== d2.last)
		{
			d2.lastCall();
		}

		else if(e.getSource()== d2.previous)
		{
			d2.previousCall();
		}

		else if(e.getSource()== d2.next)
		{
			d2.nextCall();
		}

		if(e.getSource()==d2.first||e.getSource()==d2.last||e.getSource()==d2.previous||e.getSource()==d2.next)
		{
			d2.call();
		}


	}

	public void mouseClicked(MouseEvent ec)
	{
		d2.nametf.setEditable(false);
		d2.acc_Notf.setEditable(false);
		d2.mob_Notf.setEditable(false);
		d2.salarytf.setEditable(false);

		d2.nametf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),0).toString());
		d2.acc_Notf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),1).toString());
		d2.mob_Notf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),2).toString());
		d2.salarytf.setText(d2.model.getValueAt(d2.table.getSelectedRow(),3).toString());

		d2.rowSelection = d2.table.getSelectedRow();
	}


}