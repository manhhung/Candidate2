package Home;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Connection_DB {

	Connection sqlcon = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement callstmt = null;

	public Connection getConnect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			sqlcon = DriverManager.getConnection(
					"jdbc:sqlserver://ASUS-S400CA-VMH:1433;databaseName=CANDIDATE; username=sa; password=admin123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlcon;
	}

	public static void main(String[] args) {
		Connection_DB db = new Connection_DB();
		if (db.getConnect() != null) {
			System.out.println("ok");
		}
	}

	// Get danh sach candidate theo nam sinh
	public ArrayList<Candidate> getListCandidate(int inputBirthDate) {
		ArrayList<Candidate> listCandidate = new ArrayList<>();
		try {
			callstmt = getConnect().prepareCall("{call sp_listCandidateofBirthDate(?)}");
			callstmt.setInt(1, inputBirthDate);
			rs = callstmt.executeQuery();

			while (rs.next()) {
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				int birthDate = rs.getInt(3);
				String address = rs.getString(4);
				int phone = rs.getInt(5);
				String email = rs.getString(6);
				int candidate_type = rs.getInt(7);
				int expInYear = rs.getInt(8);
				String proSkill = rs.getString(9);
				String graduation_Date = rs.getString(10);
				String graduation_Rank = rs.getString(11);
				String education = rs.getString(12);
				String majors = rs.getString(13);
				int semester = rs.getInt(14);
				String university_Name = rs.getString(15);

				Candidate ca = null;
				if (candidate_type == 0) {
					ca = new Experience_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type,
							expInYear, proSkill);
				} else if (candidate_type == 1) {
					ca = new Fresher_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type,
							graduation_Date, graduation_Rank, education);
				} else if (candidate_type == 2) {
					ca = new Intern_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type,
							majors, semester, university_Name);
				}

				listCandidate.add(ca);

			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return listCandidate;
	}

	// get danh sach toan bo nhan vien
	public ArrayList<Candidate> showCandidate() throws SQLException {
		ArrayList<Candidate> listCandidate = new ArrayList<>();

		Connection conn = getConnect();
		Statement stm = conn.createStatement();
		String sqlconn = "SELECT * FROM Candidate";
		ResultSet rs = stm.executeQuery(sqlconn);

		while (rs.next()) {
			String firstName = rs.getString(1);
			String lastName = rs.getString(2);
			int birthDate = rs.getInt(3);
			String address = rs.getString(4);
			int phone = rs.getInt(5);
			String email = rs.getString(6);
			int candidate_type = rs.getInt(7);
			int expInYear = rs.getInt(8);
			String proSkill = rs.getString(9);
			String graduation_Date = rs.getString(10);
			String graduation_Rank = rs.getString(11);
			String education = rs.getString(12);
			String majors = rs.getString(13);
			int semester = rs.getInt(14);
			String university_Name = rs.getString(15);

			Candidate ca = null;
			if (candidate_type == 0) {
				ca = new Experience_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type,
						expInYear, proSkill);
			} else if (candidate_type == 1) {
				ca = new Fresher_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type,
						graduation_Date, graduation_Rank, education);
			} else if (candidate_type == 2) {
				ca = new Intern_candidate(firstName, lastName, birthDate, address, phone, email, candidate_type, majors,
						semester, university_Name);
			}

			listCandidate.add(ca);

		}
		return listCandidate;
	}

	// them moi mot candidate
	public void addCandidate(String firstName, String lastName, int birthDate, String address, int phone, String email,
			int candidate_type, int expInYear, String proSkill, String graduation_date, String graduation_rank,
			String education, String majors, int semester, String university_name) {

		String sql = "insert into Candidate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = getConnect().prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, birthDate);
			pstmt.setString(4, address);
			pstmt.setInt(5, phone);
			pstmt.setString(6, email);
			pstmt.setInt(7, candidate_type);
			pstmt.setInt(8, expInYear);
			pstmt.setString(9, proSkill);
			pstmt.setString(10, graduation_date);
			pstmt.setString(11, graduation_rank);
			pstmt.setString(12, education);
			pstmt.setString(13, majors);
			pstmt.setInt(14, semester);
			pstmt.setString(15, university_name);

			pstmt.executeUpdate();
			System.out.println("Add candidate sucessfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// cap nhat nhan vien
	public void updateCandidate() {
		try {
			stmt = getConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			getConnect().setAutoCommit(false);
			String sql = "UPDATE Candidate SET BirthDate = BirthDate + 2";
			stmt.addBatch(sql);
			stmt.executeBatch();

			getConnect().commit();
			getConnect().setAutoCommit(true);
			showCandidate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// xoa nhan vien
	public void deleteCandidate(int idnhanvien) {
		try {
			stmt = getConnect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * from Candidate");
			while (rs.next()) {
				if (rs.getInt(3) == idnhanvien) {
					rs.deleteRow();
					System.out.println("Deleted candidate have birth date:" + idnhanvien);
				}
			}
				rs.close();
				stmt.close();
				getConnect().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
