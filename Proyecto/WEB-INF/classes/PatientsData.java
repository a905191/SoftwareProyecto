import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientsData {
    String    IdPatient;
    String    Name;
    String    LastName;
    String    Address;
    int       Phone;
	String    Admission;
	String    Departure;
	
	
    
    PatientsData (String IdPatient, String Name, String LastName, String Address, int Phone, String Admission, String Departure) {
        this.IdPatient    = IdPatient;
        this.Name  = Name;
        this.LastName  = LastName;
        this.Address = Address;
        this.Phone = Phone;
		this.Admission = Admission;
		this.Departure = Departure;
    }
	
    public static Vector<PatientsData> getPatientsList(Connection connection) {
        Vector<PatientsData> vec = new Vector<PatientsData>();
        String sql = "Select IdPatient, Name, LastName, Address, Phone, Admission, Departure FROM Patients";
        System.out.println("getPatientsList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                PatientsData patients = new PatientsData(
                    result.getString("IdPatient"),
                    result.getString("Name"),
					result.getString("LastName"),
					result.getString("Address"),
                    Integer.parseInt(result.getString("Phone")),
                    result.getString("Admission"),
                    result.getString("Departure")
                );
                vec.addElement(patients);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPatientsList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
    public static Vector<PatientsData> getDifferentPatientsList(Connection connection, String dni) {
        Vector<PatientsData> vec = new Vector<PatientsData>();
        String sql = "Select IdPatient, Name, LastName, Address, Phone, Admission, Departure FROM Patients";
        sql += " WHERE IdPatients=?";
        System.out.println("getPatientsList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt (dni) );
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                PatientsData patients = new PatientsData(
                    result.getString("IdPatient"),
                    result.getString("Name"),
                    result.getString("LastName"),
					result.getString("Address"),
                    Integer.parseInt(result.getString("Phone")),
                    result.getString("Admission"),
                    result.getString("Departure")
                );
                vec.addElement(patients);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPatientsList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static PatientsData getPatients(Connection connection, String dni) {
        String sql = "Select IdPatient, Name, LastName, Address, Phone, Admission, Departure FROM Patients";
        sql += " WHERE IdPatient=?";
        System.out.println("getPatients: " + sql);
        PatientsData patients = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, dni);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                patients = new PatientsData(
                    result.getString("IdPatient"),
                    result.getString("Name"),
					result.getString("LastName"),
					result.getString("Address"),
                    Integer.parseInt(result.getString("Phone")),
                    result.getString("Admission"),
					result.getString("Departure")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPatients: " + sql + " Exception: " + e);
        }
        return patients;
    }
	
	public static int updatePatients(Connection connection, PatientsData patients) {
        String sql ="UPDATE Patients "
            + "SET Name = ?, LastName = ?, Address = ?, Phone = ?, Admission = ?, Departure = ?"
            + " WHERE IdPatient = ?";
        System.out.println("updatePatients: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1, patients.Name);
            stmtUpdate.setString(2, patients.LastName);
            stmtUpdate.setString(3, patients.Address);
			stmtUpdate.setInt(4, patients.Phone);
			stmtUpdate.setString(5, patients.Admission);
			stmtUpdate.setString(6, patients.Departure);
            stmtUpdate.setString(7, patients.IdPatient);
			
			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updatePatients: " + sql + " Exception: " + e);
        }
        return n;
    }
}