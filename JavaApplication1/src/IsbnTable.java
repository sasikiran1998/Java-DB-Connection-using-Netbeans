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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

class Isbn implements ActionListener
{
	public JFrame frmIsbnDatabase;
	public JTextField auth_txt,isbn_txt;
	public JButton add,update,clear,add_auth;
	public JLabel error;
	public String sqlquery,id,isbn;
	public Connection conn;
	Isbn()
	{
		frmIsbnDatabase = new JFrame();
		frmIsbnDatabase.setTitle("ISBN Database");
		frmIsbnDatabase.getContentPane().setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		frmIsbnDatabase.getContentPane().setBackground(Color.WHITE);
		frmIsbnDatabase.setBounds(100, 100, 328, 283);
		frmIsbnDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsbnDatabase.getContentPane().setLayout(null);
		
		JLabel isbntable = new JLabel("ISBN Database");
		isbntable.setFont(new Font("Times New Roman", Font.BOLD, 24));
		isbntable.setBounds(80, 11, 172, 39);
		frmIsbnDatabase.getContentPane().add(isbntable);
		
		JLabel authid_lbl = new JLabel("Author Id");
		authid_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		authid_lbl.setBounds(10, 61, 89, 24);
		frmIsbnDatabase.getContentPane().add(authid_lbl);
		
		JLabel isbn_lbl = new JLabel("ISBN");
		isbn_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		isbn_lbl.setBounds(10, 98, 46, 24);
		frmIsbnDatabase.getContentPane().add(isbn_lbl);
		
		auth_txt = new JTextField();
		auth_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		auth_txt.setBounds(116, 61, 188, 24);
		frmIsbnDatabase.getContentPane().add(auth_txt);
		auth_txt.setColumns(10);
		
		isbn_txt = new JTextField();
		isbn_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		isbn_txt.setBounds(116, 98, 188, 24);
		frmIsbnDatabase.getContentPane().add(isbn_txt);
		isbn_txt.setColumns(10);
		
		add = new JButton("Add");
		add.setBackground(Color.WHITE);
		add.setFont(new Font("Algerian", Font.PLAIN, 17));
		add.setBounds(10, 141, 142, 28);
		add.addActionListener(this);
		frmIsbnDatabase.getContentPane().add(add);
		
		update = new JButton("Update");
		update.setBackground(Color.WHITE);
		update.setFont(new Font("Algerian", Font.PLAIN, 17));
		update.setBounds(162, 141, 142, 28);
		update.addActionListener(this);
		frmIsbnDatabase.getContentPane().add(update);
		
		add_auth = new JButton("Add Author");
		add_auth.setBackground(Color.WHITE);
		add_auth.setFont(new Font("Algerian", Font.PLAIN, 17));
		add_auth.setBounds(10, 180, 142, 28);
		add_auth.addActionListener(this);
		frmIsbnDatabase.getContentPane().add(add_auth);
		
		clear = new JButton("Clear");
		clear.setBackground(Color.WHITE);
		clear.setFont(new Font("Algerian", Font.PLAIN, 17));
		clear.setBounds(162, 180, 142, 28);
		clear.addActionListener(this);
		frmIsbnDatabase.getContentPane().add(clear);
		
		error = new JLabel("Welcome");
		error.setForeground(Color.RED);
		error.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		error.setBounds(116, 212, 94, 24);
		frmIsbnDatabase.getContentPane().add(error);
		frmIsbnDatabase.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		id = auth_txt.getText().toUpperCase();
		isbn = isbn_txt.getText().toUpperCase();
		if(e.getSource().equals(add))
		{
			conn = Connections.getConnections();
			try
            {
                Statement st = conn.createStatement();
                st.execute("INSERT INTO ISBN (AUTHOR_ID,ISBN) VALUES ('" + id + "','" + isbn + "')");
                error.setText("Add Successfully");
                auth_txt.setText(null);
                isbn_txt.setText(null);
            }
            catch(Exception ex)
            {
                error.setText("Not Inserted");
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
                st.execute("UPDATE ISBN SET ISBN='" + isbn +"' WHERE AUTHOR_ID='" + id + "'");
                error.setText("Update Successfully");
                auth_txt.setText(null);
                isbn_txt.setText(null);
            }
            catch(Exception ex)
            {
                error.setText("Not Updated");
            }    
		}
		else if(e.getSource().equals(clear))
		{
			auth_txt.setText(null);
			isbn_txt.setText(null);
			error.setText("Welcome");
		}
		else if(e.getSource().equals(add_auth))
		{
			new Auth();
		}
			
	}
}
public class IsbnTable 
{
	public static void main(String[] args) 
	{
		//Isbn window = new Isbn();
	}
}

