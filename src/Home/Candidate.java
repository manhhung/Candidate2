/**
 * 
 */
package Home;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author vomanhhung day 2-7-2017
 *
 */
public abstract class Candidate {
	int year = Calendar.getInstance().get(Calendar.YEAR);

	private String firstName;
	private String lastName;
	private int birthDate;
	private String address;
	private int phone;
	private String email;
	private int candidate_type;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public int getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the candidate_type
	 */
	public int getCandidate_type() {
		return candidate_type;
	}

	/**
	 * @param candidate_type
	 *            the candidate_type to set
	 */
	public void setCandidate_type(int candidate_type) {
		this.candidate_type = candidate_type;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param address
	 * @param phone
	 * @param email
	 * @param candidate_type
	 */
	public Candidate(String firstName, String lastName, int birthDate, String address, int phone, String email,
			int candidate_type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.candidate_type = candidate_type;
	}

	/**
	 * 
	 */
	public Candidate() {

	}

	/*
	 * 
	 * HIEN THI DU LIEU
	 * 
	 */

	void Show() {
		System.out.println(
				"First Name: " + firstName + "\nLast Name; " + lastName + "\nBirth Date: " + birthDate + "\nAddress: "
						+ address + "\nPhone: " + phone + "\nEmail: " + email + "\nCandidate_type: " + candidate_type);
	}

	// Nhap thong tin Candidate chung
	public void enterCandidate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Scanner sc = new Scanner(System.in);

		try {
			// Enter firstName
			System.out.print("Enter First Name: ");
			String firstName = sc.nextLine();
			setFirstName(firstName);

			// Enter lastName
			System.out.print("Enter Last Name: ");
			String lastName = sc.nextLine();
			setLastName(lastName);

			// Enter birthDate
			do {
				try {
					System.out.print("Enter Birth Date: ");
					int birthDate = Integer.parseInt(sc.nextLine());
					BirthDateUtil.checkBirthDate(birthDate);
					setBirthDate(birthDate);
				} catch (TooOldException e) {
					System.out.println(e.getMessage());
				} catch (TooYoungException e) {
					System.out.println(e.getMessage());
				}
			} while (birthDate < 1990 || birthDate > year);

			// Enter address
			System.out.print("Enter Address: ");
			String address = sc.nextLine();
			setAddress(address);

			// Enter phone
			do {
				try{
					System.out.print("Enter Phone: ");
					int phone = Integer.parseInt(sc.nextLine());
					setPhone(phone);
					
				}catch (Exception e){
					System.out.println("Please try again!");
				}
				
			} while (phone < 0 || Integer.toString(phone).length() < 7);

			// Enter email
			do {
				System.out.print("Enter Email: ");
				String email = sc.nextLine();
				setEmail(email);
			} while (validateEmailAddress(email) == false);

		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.err.println("Have a problem in processing!");

		}
	}

	// Xac thuc email
	private Pattern regexPattern;
	private Matcher regMatcher;

	public boolean validateEmailAddress(String emailAddress) {

		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
		regMatcher = regexPattern.matcher(emailAddress);
		if (regMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
