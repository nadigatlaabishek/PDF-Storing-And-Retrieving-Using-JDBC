package PDFInsertion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrievePDF 
 {
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		String url = "jdbc:mysql://localhost:3308?user=root&password=Nadi@033";
		String query = "select * from librarymanagementsystem.informationtechnologybooks where bookId=11";
		try 
		{
			Connection connect = DriverManager.getConnection(url);
			Statement statement2 = connect.createStatement();
			ResultSet rs = statement2.executeQuery(query);
			boolean status = rs.next();
			if (status) 
			{
				System.out.println("Record is Present.");
				int bookId = rs.getInt("bookId");
				String name = rs.getString("name");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				int pages = rs.getInt("pages");
				System.out.println("Book Id : "+bookId);
				System.out.println("Book Name : "+name);
				System.out.println("Author : "+author);
				System.out.println("Price : "+price);
				System.out.println("Pages : "+pages);
				FileOutputStream fileOutputStream = new FileOutputStream("E:\\LA\\ghfgv.pdf");
				if (fileOutputStream != null) 
				{
					fileOutputStream.write(rs.getBytes("bookPDF"));
					System.out.println("Success");
				}
			}
			else
			{
				System.out.println("No Records Found.");
				connect.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
 }
