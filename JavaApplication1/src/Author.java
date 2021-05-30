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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Auth_Pro implements ActionListener
{
	public JFrame frame;
	public static Connection conn;
	public JTextField txtQuery;
	public JButton btnNewButton,Clear;
	public DefaultTableModel model;
	public JTable table;
	public JComboBox<String> comboBox,QueryBox;
	public JScrollPane js;
	public String querytype[] = { "All Authors","Authors Book","Book By Author","Query" };
	public static void connection()
	{
	    	String url = "jdbc:postgresql://localhost:5432/testdb";
			String username = "postgres";
			String password = "psql";
	        try 
	        {
	            conn= (Connection) DriverManager.getConnection(url, username, password);
	            System.out.println("Connected Successfully!");
	        } 
	        catch (SQLException e) 
	        {
	            System.out.println("Connection Failed! Check output console");
	        }
	}
	Auth_Pro()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setSize(1125, 290);
		frame.setResizable(false);
		frame.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int framewidth = frame.getSize().width;
		int frameheigth = frame.getSize().height;
		int framelocationX = (dim.width - framewidth) / 2;
		int framelocationY = (dim.height - frameheigth) / 2;
		frame.setLocation(framelocationX, framelocationY);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("Query Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Query Application");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_1.setBounds(500, 24, 146, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Choose Query");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 75, 129, 44);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<String>(querytype);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		comboBox.setBounds(180, 88, 207, 22);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				combobox_ItemStateChanged(evt);
				}
			});
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel1 = new JLabel("Choose Author/Book");
		lblNewLabel1.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblNewLabel1.setBounds(10, 130, 170, 22);
		frame.getContentPane().add(lblNewLabel1);
		
		QueryBox = new JComboBox<String>();
		QueryBox.setBackground(Color.WHITE);
		QueryBox.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		QueryBox.setBounds(180, 130, 207, 22);
		QueryBox.setEnabled(false);
		frame.getContentPane().add(QueryBox);
		
		JLabel lblNewLabel2 = new JLabel("Enter Query");
		lblNewLabel2.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		lblNewLabel2.setBounds(10, 170, 170, 22);
		frame.getContentPane().add(lblNewLabel2);
		
		txtQuery = new JTextField();
		txtQuery.setBackground(Color.WHITE);
		txtQuery.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		txtQuery.setBounds(180,170,207,22);
		txtQuery.setEnabled(false);
		frame.getContentPane().add(txtQuery);
		
		btnNewButton = new JButton("Fetch");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(10, 210,180, 23);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
		
		Clear = new JButton("Clear");
		Clear.setBackground(Color.WHITE);
		Clear.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Clear.setBounds(205, 210, 180, 23);
		Clear.addActionListener(this);
		frame.getContentPane().add(Clear);

		table = new JTable();
		js = new JScrollPane();
		js.setBounds(400, 88, 700, 150);
		table.setModel(new javax.swing.table.DefaultTableModel( new Object [][] {},new String [] {"ID","NAME"}));
		js.setViewportView(table);
		frame.add(js);
	}
	private void combobox_ItemStateChanged(ItemEvent evt) 
	{
		model = new DefaultTableModel();
        table.setModel(model);
        model.addColumn("Id");
        model.addColumn("Name");
        Integer data = comboBox.getSelectedIndex();
       
        if(data.equals(0))
        {
            QueryBox.setEnabled(false);
            btnNewButton.setEnabled(true);
            txtQuery.setEnabled(false);
        }
        else if(data.equals(1) || data.equals(2))
        {
        	QueryBox.setEnabled(true);
        	btnNewButton.setEnabled(true);
        	txtQuery.setEnabled(false);
            try
            {
                Statement st = conn.createStatement();
                ResultSet rs;
                if(data.equals(1))
                {
                    rs = st.executeQuery("SELECT author_name FROM author_details");
                }
                else
                {
                    rs = st.executeQuery("SELECT DISTINCT name FROM book_details");
                }
                QueryBox.removeAllItems();
                while(rs.next())
                {
                   QueryBox.addItem(rs.getString(1));
                }
            }
            catch(Exception e)
            {
                System.out.print("Didn't Retrive The Records From Author Table");
            }
        }
        else
        {
            QueryBox.setEnabled(false);
            btnNewButton.setEnabled(true);
            txtQuery.setEnabled(true);
        }
    }
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (arg0.getSource().equals(btnNewButton))
		{
			Integer data = comboBox.getSelectedIndex();
			if(data.equals(0))
			{
				model = new DefaultTableModel();
				table.setModel(model);
				model.addColumn("Id");
				model.addColumn("Name");
				try
	            {
	                Statement st = conn.createStatement();
	                ResultSet rs= st.executeQuery("SELECT * FROM AUTHOR_DETAILS");
	                QueryBox.removeAllItems();
	          
	                while(rs.next())
	                {
	                   model.addRow(new Object[]{rs.getString("author_id"), rs.getString("author_name")});
	                }
	            }
	            catch(Exception e)
	            {
	                System.out.print("Didn't Retrive The Records From  Author Table");
	            }        
			}
			else if(data.equals(1))
			{
				String author_name = QueryBox.getSelectedItem().toString();
	             try
	             {
	                PreparedStatement st =conn.prepareStatement("select b.id, b.name, b.year, b.isbn, a.author_name from book_details b, author_details a where a.author_name=? and b.author_id=a.author_id");
	                st.setString(1,author_name);
	                ResultSet rs= st.executeQuery();
	                model = new DefaultTableModel();
	                table.setModel(model);
	                model.addColumn("ID");
	                model.addColumn("Book Name");
	                model.addColumn("Year");
	                model.addColumn("ISBN");
	                model.addColumn("Author Name");
	                
	                while(rs.next())
	                {
	                   model.addRow(new Object[]{
	                        rs.getString("id"), 
	                        rs.getString("name"),
	                        rs.getString("year"),
	                        rs.getString("isbn"),
	                        rs.getString("author_name"),
	                   });
	                }
	             }
	             catch(Exception e)
	             {
	                System.out.print("Didn't Retrive The Records From Book And Author Table");
	            } 
			}
			else if(data.equals(2))
			{
	             String book_name = QueryBox.getSelectedItem().toString();
	             try
	             {
	                PreparedStatement st =conn.prepareStatement("select a.author_id, a.author_name from book_details b, author_details a where b.name=? and b.author_id=a.author_id");
	                st.setString(1,book_name);
	                ResultSet rs= st.executeQuery();
	                model= new DefaultTableModel();
	                table.setModel(model);
	                model.addColumn("Id");
	                model.addColumn("Author Name");
	                
	                while(rs.next())
	                {
	                   model.addRow(new Object[]{
	                        rs.getString("author_id"), 
	                        rs.getString("author_name"),
	                   });
	                }
	            }
	             catch(Exception e)
	             {
	                System.out.print("Didn't Retrive The Records From Book And Author Table");
	            } 
	        }
			else if(data.equals(3))
			{
				try
				{
		            String sql =txtQuery.getText();
		            Statement st = conn.createStatement();
		            ResultSet rs= st.executeQuery(sql);
		            ResultSetMetaData rsmd = rs.getMetaData();
		            int count = rsmd.getColumnCount();
		            model= new DefaultTableModel();
		            table.setModel(model);
		            String a;
		            Object data1[]=new Object[count];
		            
		            for(int x=0; x<count;x++){
		                a= rsmd.getColumnName(x+1);
		                model.addColumn(a);     
		            }
		            
		            while(rs.next()){
		                for(int x=0; x<count;x++){
		                    a= rsmd.getColumnName(x+1);
		                    data1[x]=rs.getString(a);
		                }
		                
		                model.addRow(data1);
		            }
		        
		       }catch(SQLException e){
		           System.out.print(e.toString());
		       }
			}
		}
		else if(arg0.getSource().equals(Clear))
		{
			comboBox.setSelectedIndex(0);
			QueryBox.removeAllItems();
			QueryBox.setEnabled(false);
			txtQuery.setText(null);
			txtQuery.setEnabled(false);
		}
	}
	
}
public class Author 
{
	public static void main(String[] args) 
	{
		Auth_Pro a = new Auth_Pro();
		Auth_Pro.connection();
		a.frame.setVisible(true);
	}
}


