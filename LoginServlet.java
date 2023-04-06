
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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String Username =request.getParameter("UserId");
		String Password =request.getParameter("Password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_qr" ,"root" ,"vik@s");
			Statement st =conn.createStatement();
			String query = "SELECT * from usersdetails WHERE UserName ='"+Username+"' and Password='"+Password+"'";
			ResultSet rs=  st.executeQuery(query);
			if(rs.next()) {
				out.print("<h1>Succesfully Login<h1>");
			}
			else {
				out.print("<h1>Some Error Occured , try again later<h1>");
			}
			
			rs.close();
			st.close();
			conn.close();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
