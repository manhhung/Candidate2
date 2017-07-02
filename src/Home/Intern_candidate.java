package Home;

import java.util.Date;
import java.util.Scanner;

public class Intern_candidate extends Candidate {

	// chuyen nganh dang hoc
	private String majors;

	// hoc ky dang hoc
	private int semester;

	// ten truong dang hoc
	private String university_Name;

	/**
	 * @return the majors
	 */
	public String getMajors() {
		return majors;
	}

	/**
	 * @param majors
	 *            the majors to set
	 */
	public void setMajors(String majors) {
		this.majors = majors;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester
	 *            the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * @return the university_Name
	 */
	public String getUniversity_Name() {
		return university_Name;
	}

	/**
	 * @param university_Name
	 *            the university_Name to set
	 */
	public void setUniversity_Name(String university_Name) {
		this.university_Name = university_Name;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param address
	 * @param phone
	 * @param email
	 * @param candidate_type
	 * @param majors
	 * @param semester
	 * @param university_Name
	 */
	public Intern_candidate(String firstName, String lastName, int birthDate, String address, int phone,
			String email, int candidate_type, String majors, int semester, String university_Name) {
		super(firstName, lastName, birthDate, address, phone, email, candidate_type);
		this.majors = majors;
		this.semester = semester;
		this.university_Name = university_Name;
	}

	public Intern_candidate() {

	}

	public void Show() {
		super.Show();
		System.out.println("Majors: " + majors + "\nSemester: " + semester + "\nUniversity_Name: " + university_Name);
	}

	public void enterInCandidate() {
		Scanner sc = new Scanner(System.in);

		try {
			// call from candidate
			enterCandidate();
			setCandidate_type(2);

			// Enter majors - chuyen nganh
			System.out.print("Enter Majors: ");
			String majors = sc.nextLine();
			setMajors(majors);

			// Enter semester - hoc ky
			System.out.print("Enter Semester: ");
			int semester = Integer.parseInt(sc.nextLine());
			setSemester(semester);

			// Enter University name
			System.out.print("Enter University name: ");
			String university_name = sc.nextLine();
			setUniversity_Name(university_name);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
