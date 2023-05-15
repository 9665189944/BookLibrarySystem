package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/diplayLink")
public class DisplayBook extends HttpServlet {
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
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Statement stmt=null;
			ResultSet rs=null;
			PrintWriter pw=resp.getWriter();
			
			String query="select * from book_data";
			try {
				stmt=con.createStatement();
				rs=stmt.executeQuery(query);
				pw.print("<table border='2'>");
				pw.print("<tr>");
				pw.print("<th>BOOK ID</th>");
				pw.print("<th>BOOK NAME</th>");
				pw.print("<th>BOOK Price</th>");
				pw.print("<th>BOOK Author</th>");
				pw.print("</tr>");
				
				while(rs.next())
				{
					pw.print("<tr>");
					pw.print("<td>"+rs.getInt(1)+"</td>");
					pw.print("<td>"+rs.getString(2)+"</td>");
					pw.print("<td>"+rs.getDouble(3)+"</td>");
					pw.print("<td>"+rs.getString(4)+"</td>");
					pw.print("</tr>");
				}
				pw.print("</table>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
