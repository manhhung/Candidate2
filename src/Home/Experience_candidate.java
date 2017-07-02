/**
 * 
 */
package Home;

import java.util.Date;
import java.util.Scanner;

/**
 * @author vomanhhung
 * @DAY 2-7-2017
 */
public class Experience_candidate extends Candidate {

	// so nam kinh nghiem
	private int expInYear;

	// ky nang chuyen mon
	private String proSkill;

	/**
	 * @return the expInYear
	 */
	public int getExpInYear() {
		return expInYear;
	}

	/**
	 * @param expInYear
	 *            the expInYear to set
	 */
	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	/**
	 * @return the proSkill
	 */
	public String getProSkill() {
		return proSkill;
	}

	/**
	 * @param proSkill
	 *            the proSkill to set
	 */
	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param address
	 * @param phone
	 * @param email
	 * @param candidate_type
	 * @param expInYear
	 * @param proSkill
	 */
	public Experience_candidate(String firstName, String lastName, int birthDate, String address, int phone,
			String email, int candidate_type, int expInYear, String proSkill) {
		super(firstName, lastName, birthDate, address, phone, email, candidate_type);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	public Experience_candidate() {

	}

	public void Show() {
		super.Show();
		System.out.println("Exp In Year: " + expInYear + "\nPro Skill: " + proSkill);
	}

	public void enterExCandidate() {
		Scanner sc = new Scanner(System.in);

		try {
			// Call from Candidate
			enterCandidate();
			setCandidate_type(0);

			// Enter expInYear
			System.out.print("Enter Exp In Year: ");
			int expInYear = Integer.parseInt(sc.nextLine());
			setExpInYear(expInYear);

			// Enter proskill
			System.out.print("Enter Pro Skill: ");
			String proSkill = sc.nextLine();
			setProSkill(proSkill);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Have a problem in processing in EX!");
		}

	}

}
