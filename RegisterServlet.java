
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

/**
 * Servlet implementation class ResgisterServlet
 */
@WebServlet("/ResgisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fName =request.getParameter("fName");
		String lName =request.getParameter("lName");
		String UserName =request.getParameter("UserName");
		String Password =request.getParameter("Password");
		String DOB =request.getParameter("DOB");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_qr" ,"root" ,"vik@s");
			PreparedStatement ps =con.prepareStatement("INSERT INTO usersdetails values(?,?,?,?,?)");
			ps.setString(1, UserName);
			ps.setString(2, fName);
			ps.setString(3, lName);
			ps.setString(4, Password);
			ps.setString(5, DOB);
			int i=ps.executeUpdate();
			if(i>0) {
				out.print("Succesfully Registered");
			}
			else {
				out.print("Some Error Occured , try again later");
			}
			
			
			
			ps.close();
			con.close();
		}
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.print("exception server error");
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("exception sql error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
