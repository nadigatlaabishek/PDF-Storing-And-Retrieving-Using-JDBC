package PDFInsertion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.Blob;

public class InsertPDF 
{
	public static void main(String[] args) throws ClassNotFoundException,  FileNotFoundException
	{
		String url = "jdbc:mysql://localhost:3308?user=root&password=Nadi@033";
		String query = "insert into librarymanagementsystem.electricalandelectronicsengineeringbooks values(?,?,?,?,?,?)";
		try 
		{
			Connection connect = DriverManager.getConnection(url);
			System.out.println("Connection Established.");
			PreparedStatement ps =connect.prepareStatement(query);
			Scanner scan = new Scanner (System.in);
			System.out.println("Enter Book ID : ");
			int bookId = scan.nextInt();
			ps.setInt(1,bookId);
			scan.nextLine();
			System.out.println("Enter the Name : ");
			String bookName = scan.nextLine();
			ps.setString(2,bookName);
			System.out.println("Enter the Author Name : ");
			String authorName = scan.nextLine();
			scan.nextLine();
			ps.setString(3,authorName);
			System.out.println("Enter Price  : ");
			int bookPrice = scan.nextInt();
			ps.setInt(4,bookPrice);
			System.out.println("Enter Pages : ");
			int bookPages = scan.nextInt();
			ps.setInt(5,bookPages);

			FileInputStream inputStream = new FileInputStream("D:\\Library Project Books\\electricalBooks\\Power system dynamics stability and control (Jan Machowski, Janusz Bialek, Dr Jim Bumby) (Z-Library).pdf");
			System.out.println("Connection Established::-- "+connect.getClass().getName());
			Blob blob = new Blob(inputStream.readAllBytes (), null);
			ps.setBlob(6, blob);
			ps.executeUpdate();
			System.out.println("Registration Successfull.");
			connect.close();
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
	}
}
