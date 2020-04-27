public class Utils {
    public static String header(String title) {
        StringBuilder str = new StringBuilder();
        str.append("<!DOCTYPE HTML>");
        str.append("<html>");
        str.append("<head><title>" + title + "</title>");
        str.append("<link rel='icon' href='favicon.ico' />");
        str.append("<link rel='stylesheet' href='estilo.css'>");
        str.append("</head>");
        str.append("<body>");
		str.append("<div class='menu'>");
		str.append("<a href='index.html'>Home</a>");
		str.append("<a href='PatientsList'>Patients</a>");
		str.append("<a href='RoomsInicio.html'>Patient Rooms</a>");
		str.append("<a href='Doctors-Departments.html'>Doctors and Departments</a>");
		str.append("<a href='Investigaciones.html'>Investigations</a>");
		str.append("<a href='DonationsInicio.html'>Donations</a>");
		str.append("</div>");
		str.append("<img src='https://www.uupfarm.org/images/Medical.png' alt='Logo' width='120' height='100' align='right'>");
        str.append("<H2 align=\"center\">" + title + "</H2>");
		return str.toString();
    }

    public static String footer(String title) {
        StringBuilder str = new StringBuilder();
        str.append("</body>");
        str.append("</html>");
        return str.toString();
    }
}