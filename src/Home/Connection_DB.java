package Home;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.List;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

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

	// xoa nhan vien + có thể áp dụng cho cập nhật
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

	// Xuat file
	public void writeFile() {
		try {
			// true : nếu file chưa tồn tại thì tạo file nếu đã tồn tại thì ghi
			// nối tiếp vào file
			// false: nếu file chưa tồn tại thì tạo file nếu đã tồn tại thì ghi
			// đè lên file cũ
			FileOutputStream fos = new FileOutputStream("cadidate01.txt", false);

			PrintWriter pw = new PrintWriter(fos);

			Connection conn = getConnect();
			Statement stm = conn.createStatement();
			String sqlconn = "SELECT * FROM Candidate";
			ResultSet rs = stm.executeQuery(sqlconn);

			while (rs.next()) {
				String firstName = rs.getString(1);
				pw.println(firstName);
				String lastName = rs.getString(2);
				pw.println(lastName);
				int birthDate = rs.getInt(3);
				pw.println(birthDate);
				String address = rs.getString(4);
				pw.println(address);
				int phone = rs.getInt(5);
				pw.println(phone);
				String email = rs.getString(6);
				pw.println(email);
				int candidate_type = rs.getInt(7);
				pw.println(candidate_type);
				int expInYear = rs.getInt(8);
				pw.println(expInYear);
				String proSkill = rs.getString(9);
				pw.println(proSkill);
				String graduation_Date = rs.getString(10);
				pw.println(graduation_Date);
				String graduation_Rank = rs.getString(11);
				pw.println(graduation_Rank);
				String education = rs.getString(12);
				pw.println(education);
				String majors = rs.getString(13);
				pw.println(majors);
				int semester = rs.getInt(14);
				pw.println(semester);
				String university_Name = rs.getString(15);
				pw.println(university_Name);
				pw.println("--");
			}
			pw.close();
			System.out.println("Writer succesfuly!");
		} catch (Exception e) {
			System.err.println("Have error!");
		}

	}

	// Doc file
	public void readFile() {
		try {
			InputStream in = new FileInputStream("cadidate01.txt");
			Reader reader = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {

				// Make sure the line is not null, not empty, and contains 3
				// comma char
				if (line != null && !line.equals("")) {
					String tmp[] = line.split(",");
					String firstName = tmp[0];
					String lastName = tmp[1];
					String birthDate = tmp[2];
					String address = tmp[3];
					
					String phone = tmp[4];
							
					
					String email = tmp[5];
					
					String candidate_type = tmp[6];
									
					String expInYear = tmp[7];

					String proSkill = tmp[8];
					String graduation_date = tmp[9];
					String graduation_rank = tmp[10];
					String education = tmp[11];
					String majors = tmp[12];
					
					String semester = tmp[13];

					
					String university_name = tmp[14];

					// do the sql query
					String sql = "insert into Candidate values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					try {
						pstmt = getConnect().prepareStatement(sql);
						pstmt.setString(1, firstName);
						pstmt.setString(2, lastName);
						pstmt.setString(3, birthDate);
						pstmt.setString(4, address);
						pstmt.setString(5, phone);
						pstmt.setString(6, email);
						pstmt.setString(7, candidate_type);
						pstmt.setString(8, expInYear);
						pstmt.setString(9, proSkill);
						pstmt.setString(10, graduation_date);
						pstmt.setString(11, graduation_rank);
						pstmt.setString(12, education);
						pstmt.setString(13, majors);
						pstmt.setString(14, semester);
						pstmt.setString(15, university_name);

						pstmt.executeUpdate();
						System.out.println("Add candidate DB sucessfully!");
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					// manage where the line doesn't fit the pattern
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
