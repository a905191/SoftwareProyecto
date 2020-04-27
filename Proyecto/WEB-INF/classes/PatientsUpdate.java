import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class PatientsUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        String idStr = req.getParameter("IdPatient");
        res.setContentType("text/html");
        
		PatientsData patients = new PatientsData(
					
                    req.getParameter("IdPatient"),
                    req.getParameter("Name"),
					req.getParameter("LastName"),
					req.getParameter("Address"),
                    Integer.parseInt(req.getParameter("Phone")),
                    req.getParameter("Admission"),
					req.getParameter("Departure")
                );
        int n = PatientsData.updatePatients(connection, patients);
        res.sendRedirect("PatientsList?id=" + idStr + "&a=" + Math.random());
    }
}