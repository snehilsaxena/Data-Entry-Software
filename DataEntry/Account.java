import java.io.*;

class Account implements Serializable
{
	String name, accNo ;
	int mobNo, salary ;
	boolean send ;

	Account(String name, String accNo, int salary, int mobNo)
	{
		this.name = name ;
		this.accNo = accNo ;
		this.salary = salary ;
		this.mobNo = mobNo ;
	}

	void show()
	{
		System.out.println(name + " " + accNo + " " + salary + " " + mobNo);
	}
}