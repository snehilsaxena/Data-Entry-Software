import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.io.*;

class Design2 implements Serializable{

	JPanel panel, buttonPanel ;
	JFrame frame ;
	JTable table, table2 ;
	JScrollPane scroll, scroll2;
	JLabel name,acc_No,mob_No,salary;
	JTextField nametf,acc_Notf,mob_Notf,salarytf;
	JButton modify,add,delete,first,last,next,previous, confirm, cancel;
	DefaultTableModel model, model2 ;
	Container cont ;
	static int rowSelection ;
	int z = 5 ;
	boolean ad=false, del=false, mod=false, stream=false;

	public void draw()
	{

		frame = new JFrame();
		frame.setLayout(null);

		cont = frame.getContentPane();
		cont.setBackground(new Color(10,125,120));

		/////////// Table starts
				String columnNames[] = {"Name","Acc. No.","Salary","Pin No."};

				String dataValues[][] = {

										};

				model = new DefaultTableModel(dataValues,columnNames);

				table = new JTable(model);//new DefaultTableModel(new Object[][]{},new Object[]{"Name","Acc. No.","Salary","Mob No."}));
				table.setRowHeight(30);
				table.setFont(new Font("Times New Roman",Font.PLAIN,20));
				//table.setEditable(false);
				scroll = new JScrollPane(table);
				scroll.setBounds(520,50,440,400);
				cont.add(scroll);


				//table.setModel(model);

		/////////Table ends




		/////////labels and text starts

		Font font = new Font("Arial",0,30);

		name = new JLabel("Name : ");
		name.setFont(font);
		cont.add(name);
		name.setBounds(5,45,200,50);

		nametf = new JTextField("");
		nametf.setFont(font);
		cont.add(nametf);
		nametf.setBounds(160,50,320,40);
		nametf.setEditable(false);

		acc_No = new JLabel("Acc. No. : ");
		acc_No.setFont(font);
		cont.add(acc_No);
		acc_No.setBounds(5,155,200,50);

		acc_Notf = new JTextField("");
		acc_Notf.setFont(font);
		cont.add(acc_Notf);
		acc_Notf.setBounds(160,160,320,40);
		acc_Notf.setEditable(false);

		mob_No = new JLabel("Salary");
		mob_No.setFont(font);
		cont.add(mob_No);
		mob_No.setBounds(5,265,200,50);

		mob_Notf = new JTextField("");
		mob_Notf.setFont(font);
		cont.add(mob_Notf);
		mob_Notf.setBounds(160,270,320,40);
		mob_Notf.setEditable(false);

		salary = new JLabel("Pin No. : ");
		salary.setFont(font);
		cont.add(salary);
		salary.setBounds(5,375,200,50);

		salarytf = new JTextField("");
		salarytf.setFont(font);
		cont.add(salarytf);
		salarytf.setBounds(160,380,320,40);
		salarytf.setEditable(false);


		/////////labels and text ends

		//////////button starts

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		modify = new JButton("MODIFY");
		buttonPanel.add(modify);

		add = new JButton("ADD");
		buttonPanel.add(add);

		delete = new JButton("DELETE");
		buttonPanel.add(delete);

		first = new JButton("FIRST");
		buttonPanel.add(first);

		last = new JButton("LAST");
		buttonPanel.add(last);

		next = new JButton("NEXT");
		buttonPanel.add(next);

		previous = new JButton("PREVIOUS");
		buttonPanel.add(previous);

		buttonPanel.setBackground(new Color(10,125,120));
		buttonPanel.setBounds(10,500,980,70);


		confirm = new JButton("CONFIRM");
		confirm.setBounds(120,450,90,25);
		cont.add(confirm);
		confirm.setVisible(false);

		cancel = new JButton("CANCEL");
		cancel.setBounds(260,450,90,25);
		cont.add(cancel);
		cancel.setVisible(false);


		cont.add(buttonPanel);

		//////////button ends

		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void update()
	{
		int a = Integer.parseInt(acc_Notf.getText());
		int b = Integer.parseInt(mob_Notf.getText());
		int c = Integer.parseInt(salarytf.getText());

		this.model.addRow(new Object[]{nametf.getText(), a, b,c});
	}

	public void firstCall()
	{
		rowSelection = 0 ;
		this.table.setRowSelectionInterval(0,0);
	}

	public void nextCall()
	{
		if(rowSelection != table.getRowCount()-1)
		{
			rowSelection++ ;
			this.table.setRowSelectionInterval(rowSelection,rowSelection);
		}
	}

	public void previousCall()
	{
		if(rowSelection != 0)
		{
			rowSelection-- ;
			this.table.setRowSelectionInterval(rowSelection,rowSelection);
		}
	}

	public void lastCall()
	{
		table.setRowSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
		rowSelection = table.getRowCount()-1 ;
	}

	public void call()
	{
		nametf.setEditable(false);
		acc_Notf.setEditable(false);
		mob_Notf.setEditable(false);
		salarytf.setEditable(false);

		nametf.setText(model.getValueAt(rowSelection,0).toString());
		acc_Notf.setText(model.getValueAt(rowSelection,1).toString());
		mob_Notf.setText(model.getValueAt(rowSelection,2).toString());
		salarytf.setText(model.getValueAt(rowSelection,3).toString());

		confirm.setVisible(false);
		cancel.setVisible(false);
	}

	public static void main(String str[])
	{
		new Design2();
	}

}