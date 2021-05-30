/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sasi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

class Auth implements ActionListener
{
	public JFrame frmAuthorDatabase;
	public JTextField id_txt;
	public JTextField nm_txt;
	public Connection conn;
	public JButton add,update,clear,exit;
	public JLabel error;
	public String id,name;
	Auth()
	{
		frmAuthorDatabase = new JFrame();
		frmAuthorDatabase.setTitle("Author Database");
		frmAuthorDatabase.getContentPane().setBackground(Color.WHITE);
		frmAuthorDatabase.setBounds(100, 100, 341, 283);
		frmAuthorDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuthorDatabase.getContentPane().setLayout(null);
		
		JLabel database_lbl = new JLabel("Author Database");
		database_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
		database_lbl.setBounds(74, 11, 202, 30);
		frmAuthorDatabase.getContentPane().add(database_lbl);
		
		JLabel auth_id = new JLabel("Author Id");
		auth_id.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		auth_id.setBounds(10, 60, 89, 24);
		frmAuthorDatabase.getContentPane().add(auth_id);
		
		JLabel authnm_lnl = new JLabel("Author Name");
		authnm_lnl.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		authnm_lnl.setBounds(10, 95, 103, 24);
		frmAuthorDatabase.getContentPane().add(authnm_lnl);
		
		id_txt = new JTextField();
		id_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		id_txt.setBounds(143, 60, 173, 24);
		frmAuthorDatabase.getContentPane().add(id_txt);
		id_txt.setColumns(10);
		
		nm_txt = new JTextField();
		nm_txt.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		nm_txt.setBounds(143, 95, 173, 24);
		frmAuthorDatabase.getContentPane().add(nm_txt);
		nm_txt.setColumns(10);
		
		add = new JButton("Add");
		add.addActionListener(this);
		add.setBackground(Color.WHITE);
		add.setFont(new Font("Algerian", Font.PLAIN, 17));
		add.setBounds(10, 130, 143, 30);
		frmAuthorDatabase.getContentPane().add(add);
		
		update = new JButton("Update");
		update.addActionListener(this);
		update.setBackground(Color.WHITE);
		update.setFont(new Font("Algerian", Font.PLAIN, 17));
		update.setBounds(173, 130, 143, 30);
		frmAuthorDatabase.getContentPane().add(update);
		
		clear = new JButton("Clear");
		clear.addActionListener(this);
		clear.setBackground(Color.WHITE);
		clear.setFont(new Font("Algerian", Font.PLAIN, 17));
		clear.setBounds(10, 171, 143, 30);
		frmAuthorDatabase.getContentPane().add(clear);
		
		exit = new JButton("Exit");
		exit.addActionListener(this);
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("Algerian", Font.PLAIN, 17));
		exit.setBounds(173, 171, 143, 30);
		frmAuthorDatabase.getContentPane().add(exit);
		
		error = new JLabel("Welcome");
		error.setForeground(Color.RED);
		error.setFont(new Font("Bookman Old Style", Font.PLAIN, 15));
		error.setBounds(124, 209, 110, 24);
		frmAuthorDatabase.getContentPane().add(error);
		frmAuthorDatabase.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		id = id_txt.getText().toUpperCase();
		name = nm_txt.getText().toUpperCase();
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
                st.execute("INSERT INTO AUTHOR_DETAILS (AUTHOR_ID,AUTHOR_NAME) VALUES ('" + id + "','" + name + "')");
                error.setText("Add Successfully");
                id_txt.setText(null);
                nm_txt.setText(null);
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
                st.execute("UPDATE AUTHOR_DETAILS SET AUTHOR_NAME='" + name +"' WHERE AUTHOR_ID='" + id + "'");
                error.setText("Update Successfully");
                id_txt.setText(null);
                nm_txt.setText(null);
            }
            catch(Exception ex)
            {
                error.setText("Not Updated");
            }    
		}
		else if(e.getSource().equals(clear))
		{
			id_txt.setText(null);
			nm_txt.setText(null);
			error.setText("Welcome");
		}
		else if(e.getSource().equals(exit))
		{
			frmAuthorDatabase.dispose();
		}
	}
}
public class Authors 
{
	public static void main(String[] args) 
	{
		//Auth window = new Auth();
	}
}


