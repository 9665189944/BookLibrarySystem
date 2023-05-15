package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdataLink")
public class UpdateBook extends HttpServlet {
	
	

	Connection con=null;
	
	@Override
	public void init() throws ServletException {
	      try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8", "root", "Akhil@01999");

	     } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int BookId=Integer.parseInt(req.getParameter("bookID"));
		String name=req.getParameter("bookname");
		double price=Double.parseDouble(req.getParameter("bookprice"));
		String author=req.getParameter("author");
		
		PreparedStatement pstmt=null;
		String query="update book_data set BookName=?,BookPrice=?,author=? where book_ID=? ";
		
		
		try {
			pstmt=con.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setString(3, author);
			pstmt.setInt(4,BookId);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+"Update Record Sucessfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
