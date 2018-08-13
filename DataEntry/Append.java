import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class Append extends ObjectOutputStream
{
	public Append(OutputStream out)throws Exception
	{
		super(out);
	}

	//@override
	protected void writeStreamHeader()throws IOException
	{
		reset();
	}
}