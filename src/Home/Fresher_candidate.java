/**
 * 
 */
package Home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author vomanhhung
 * @day 2-7-2017
 */
public class Fresher_candidate extends Candidate {

	// thoi gian tot nghiep
	private String graduation_Date;

	// xep loai tot nghiep
	private String graduation_Rank;

	// truong tot nghiep
	private String education;

	/**
	 * @return the graduation_Date
	 */
	public String getGraduation_Date() {
		return graduation_Date;
	}

	/**
	 * @param graduation_Date
	 *            the graduation_Date to set
	 */
	public void setGraduation_Date(String graduation_Date) {
		this.graduation_Date = graduation_Date;
	}

	/**
	 * @return the graduation_Rank
	 */
	public String getGraduation_Rank() {
		return graduation_Rank;
	}

	/**
	 * @param graduation_Rank
	 *            the graduation_Rank to set
	 */
	public void setGraduation_Rank(String graduation_Rank) {
		this.graduation_Rank = graduation_Rank;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param address
	 * @param phone
	 * @param email
	 * @param candidate_type
	 * @param graduation_Date
	 * @param graduation_Rank
	 * @param education
	 */
	public Fresher_candidate(String firstName, String lastName, int birthDate, String address, int phone,
			String email, int candidate_type, String graduation_Date, String graduation_Rank, String education) {
		super(firstName, lastName, birthDate, address, phone, email, candidate_type);
		this.graduation_Date = graduation_Date;
		this.graduation_Rank = graduation_Rank;
		this.education = education;
	}

	public Fresher_candidate() {

	}

	public void Show() {
		super.Show();
		System.out.println("Graduation Date: " + graduation_Date + "\nGraduation Rank: " + graduation_Rank
				+ "\nEducation: " + education);
	}

	// Enter fresher candidate
	public void enterFrCandidate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Scanner sc = new Scanner(System.in);

		try {
			// Call from candidate
			enterCandidate();
			setCandidate_type(1);

			// Enter graduation date
			System.out.print("Enter Graduation Date: ");
			String graduation_Date = sc.nextLine();
			setGraduation_Date(graduation_Date);

			// Enter graduation rank
			System.out.println("Enter Graduation Rank: ");
			System.out.println("1. Excellence!");
			System.out.println("2. Good");
			System.out.println("3. Fair");
			System.out.println("4. Poor");
			int Rank = Integer.parseInt(sc.nextLine());
			if(Rank == 1){
				setGraduation_Rank("Excellence");
			}else if (Rank == 2){
				setGraduation_Rank("Good");
			}else if (Rank == 3){
				setGraduation_Rank("Fair");
			}else if (Rank == 4){
				setGraduation_Rank("Poor");
			}
			

			// Enter education
			System.out.print("Enter Education: ");
			String education = sc.nextLine();
			setEducation(education);
		} catch (Exception e) {
			System.err.println("Have a problem in processing!");
		}

	}

}
