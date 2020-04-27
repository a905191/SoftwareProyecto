import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class PatientsList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String IdPatient = req.getParameter("dni");

        toClient.println(Utils.header("Patients"));
		
		toClient.println("<center>");
        toClient.println("<table border='1'>");
        Vector<PatientsData> patientsList;
        if (IdPatient != null) {
            patientsList = PatientsData.getDifferentPatientsList(connection, IdPatient );
        } else {
            patientsList = PatientsData.getPatientsList(connection);
        }
		toClient.println("<th> Dni </th>");
		toClient.println("<th> Name </th>");
		toClient.println("<th> Last Name </th>");
		toClient.println("<th> Address </th>");
		toClient.println("<th> Phone Number </th>");
		toClient.println("<th> Admission Date </th>");
		toClient.println("<th> Departure Date </th>");
		toClient.println("<th> Update </th>");
		toClient.println("<th> Delete </th>");
		toClient.println("<th> Show </th>");
        for(int i=0; i< patientsList.size(); i++){
                PatientsData patients = patientsList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + patients.IdPatient + " </td>");
                toClient.println("<td>" + patients.Name + " </td>");
                toClient.println("<td>" + patients.LastName + " </td>");
                toClient.println("<td>" + patients.Address + " </td>");
				toClient.println("<td>" + patients.Phone + " </td>");
				toClient.println("<td>" + patients.Admission + " </td>");
				toClient.println("<td>" + patients.Departure + " </td>");
                toClient.println("<td><a href='PatientsEdit?id=" + patients.IdPatient + "'>Update Information</a></td>");
				toClient.println("<td><a href='PatientsDelete?id=" + patients.IdPatient + "'>Delete Patient</a></td>");
				toClient.println("<td><a href='ShowDiag?id=" + patients.IdPatient + "'>Show Diagnosis</a></td>");
                toClient.println("</tr>");
        }
		
        toClient.println("</table>");
		toClient.println("<br>");
		toClient.println("<div class='button'>");
		toClient.println("<a href='AddPatient'>Add Patient</a>");
		toClient.println("</div>");
		toClient.println("</center>");
		
        toClient.println(Utils.footer("Patients"));
        toClient.close();
    }
}