import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class PatientsEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
       
		super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("New Patient"));
        toClient.println("<form action='PatientsUpdate' method='GET'>");

		toClient.println("<table border='1'>");

		String idStr = req.getParameter("dni");

        PatientsData patients = PatientsData.getPatients(connection, idStr);
        toClient.println("<tr><td>Dni</td>");
        toClient.println("<td><input name='IdPatient' value='" + patients.IdPatient + "'></td></tr>");
        toClient.println("<tr><td>Name</td>");
        toClient.println("<td><input name='Name' value='" + patients.Name + "'></td></tr>");
        toClient.println("<tr><td>Last Name</td>");
        toClient.println("<td><input name='LastName' value='" + patients.LastName + "'></td>");
        toClient.println("<tr><td>Address</td>");
        toClient.println("<td><input name='Address' value='" + patients.Address + "'></td>");
        toClient.println("</tr>");
		toClient.println("<tr><td>Phone Number</td>");
        toClient.println("<td><input name='Phone' value='" + patients.Phone + "'></td>");
        toClient.println("</tr>");
		toClient.println("<tr><td>Admission Date</td>");
        toClient.println("<td><input name='Admission' value='" + patients.Admission + "'></td>");
        toClient.println("</tr>");
		toClient.println("<tr><td>Departure Date</td>");
        toClient.println("<td><input name='Departure' value='" + patients.Departure + "'></td>");
        toClient.println("</tr>");
        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
        toClient.println(Utils.footer("New Patient"));
        toClient.close();
    }
}
