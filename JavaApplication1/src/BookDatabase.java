/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sasi
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;

class Book implements ActionListener
{
	public JFrame frmBookTable;
	public JTextField title_txt;
	public JTextField isbn_txt;
	public JTextField cpy_txt;
	public JButton add,update,add_isbn,clear;
	public JLabel errorlbl;
	public String title,isbn,cpright;
	public Connection conn;
	Book()
	{
		frmBookTable = new JFrame();
		frmBookTable.setTitle("Book Table");
		frmBookTable.setBackground(Color.WHITE);
		frmBookTable.getContentPane().setBackground(Color.WHITE);
		frmBookTable.setBounds(100, 100, 330, 285);
		frmBookTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBookTable.getContentPane().setLayout(null);
		
		JLabel bookdatabase = new JLabel("Book Database");
		bookdatabase.setFont(new Font("Times New Roman", Font.BOLD, 25));
		bookdatabase.setBounds(78, 11, 168, 24);
		frmBookTable.getContentPane().add(bookdatabase);
		
		JLabel title = new JLabel("Title");
		title.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		title.setBounds(10, 49, 68, 24);
		frmBookTable.getContentPane().add(title);
		
		JLabel isbn = new JLabel("ISBN");
		isbn.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		isbn.setBounds(10, 80, 52, 24);
		frmBookTable.getContentPane().add(isbn);
		
		JLabel copyright = new JLabel("Copyright");
		copyright.setFont(new Font("Bookman Old Style", Font.BOLD, 17));
		copyright.setBounds(10, 111, 104, 24);
		frmBookTable.getContentPane().add(copyright);
		
		title_txt = new JTextField();
		title_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		title_txt.setBounds(137, 49, 168, 24);
		frmBookTable.getContentPane().add(title_txt);
		title_txt.setColumns(10);
		
		isbn_txt = new JTextField();
		isbn_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		isbn_txt.setBounds(137, 80, 168, 24);
		frmBookTable.getContentPane().add(isbn_txt);
		isbn_txt.setColumns(10);
		
		cpy_txt = new JTextField();
		cpy_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		cpy_txt.setBounds(137, 111, 168, 24);
		frmBookTable.getContentPane().add(cpy_txt);
		cpy_txt.setColumns(10);
		
		add = new JButton("Add");
		add.addActionListener(this);
		add.setBackground(Color.WHITE);
		add.setFont(new Font("Algerian", Font.PLAIN, 14));
		add.setBounds(10, 153, 132, 23);
		frmBookTable.getContentPane().add(add);
		
		update = new JButton("Update");
		update.addActionListener(this);
		update.setBackground(Color.WHITE);
		update.setFont(new Font("Algerian", Font.PLAIN, 14));
		update.setBounds(153, 153, 152, 23);
		frmBookTable.getContentPane().add(update);
		
		add_isbn = new JButton("Add ISBN");
		add_isbn.addActionListener(this);
		add_isbn.setBackground(Color.WHITE);
		add_isbn.setFont(new Font("Algerian", Font.PLAIN, 14));
		add_isbn.setBounds(10, 187, 132, 23);
		frmBookTable.getContentPane().add(add_isbn);
		
		clear = new JButton("Clear");
		clear.addActionListener(this);
		clear.setBackground(Color.WHITE);
		clear.setFont(new Font("Algerian", Font.PLAIN, 14));
		clear.setBounds(153, 187, 152, 23);
		frmBookTable.getContentPane().add(clear);
		
		errorlbl = new JLabel("Welcome");
		errorlbl.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		errorlbl.setForeground(Color.RED);
		errorlbl.setBounds(120, 213, 114, 33);
		frmBookTable.getContentPane().add(errorlbl);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		title = title_txt.getText().toUpperCase();
		isbn = isbn_txt.getText().toUpperCase();
		cpright = cpy_txt.getText().toUpperCase();
		if(e.getSource().equals(add))
		{
			conn = Connections.getConnections();
			if(conn != null)
			{
				System.out.print("Connected");
			}
			try
            {
                Statement st = conn.createStatement();
                st.execute("INSERT INTO BOOK (TITLE,ISBN,COPYRIGHT) VALUES ('" + title + "','" + isbn + "','" + cpright + "')");
                errorlbl.setText("Add Successfully");
                title_txt.setText(null);
                isbn_txt.setText(null);
                cpy_txt.setText(null);
            }
            catch(Exception ex)
            {
                errorlbl.setText("Not Inserted");
            }    
		}
		else if(e.getSource().equals(update))
		{
			conn = Connections.getConnections();
			if(conn != null)
			{
				System.out.print("Connected");
			}
			try
            {
                Statement st = conn.createStatement();
                st.execute("UPDATE BOOK SET TITLE ='" + title +"' AND COPYRIGHT='"+ cpright +"' WHERE ISBN='" + isbn + "'");
                errorlbl.setText("Update Successfully");
                title_txt.setText(null);
                isbn_txt.setText(null);
                cpy_txt.setText(null);
            }
            catch(Exception ex)
            {
                errorlbl.setText("Not Updated");
            }    
		}
		else if(e.getSource().equals(clear))
		{
			title_txt.setText(null);
			isbn_txt.setText(null);
			cpy_txt.setText(null);
			errorlbl.setText("Welcome");
		}
		else if(e.getSource().equals(add_isbn))
		{
			new Isbn();
		}
	}
}
public class BookDatabase 
{
	public static void main(String[] args) 
	{
		Book window = new Book();
		window.frmBookTable.setVisible(true);		
	}
}


