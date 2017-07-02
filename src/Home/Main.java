package Home;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Experience_candidate ex = new Experience_candidate();
		Fresher_candidate fr = new Fresher_candidate();
		Intern_candidate in = new Intern_candidate();
		Connection_DB db = new Connection_DB();
		while (true) {
			try {// chon
				System.out.println("\n==============");
				System.out.println("MAIN CANDIATE");
				System.out.println("Please choose!");
				System.out.println("1. Show Candidate!");
				System.out.println("2. Insert Candiate");
				System.out.println("3. Update Candiate");
				System.out.println("4. Delete Candiate");
				System.out.println("5. Sort Candiate");
				System.out.println("6. Find Candiate");
				System.out.println("7. Export to file");
				System.out.println("8. Read from file\n");
				int choose = Integer.parseInt(sc.nextLine());

				switch (choose) {
				// Show candidate
				case 1:
					System.out.println("Please choose!");
					System.out.println("1. Show all");
					System.out.println("2. Displayed by birth date");
					int showcandidate = Integer.parseInt(sc.nextLine());
					switch (showcandidate) {
					case 1:
						ArrayList<Candidate> listCD = db.showCandidate();
						for (int i = 0; i < listCD.size(); i++) {
							System.out.println("* List ID: " + i);
							listCD.get(i).Show();
							System.out.println("\n");
						}
						break;
					case 2:
						System.out.print("Enter birth date of candidate you want to show: ");
						int birthdateca = Integer.parseInt(sc.nextLine());
						ArrayList<Candidate> listCandidateofBirthday = db.getListCandidate(birthdateca);
						for (int i = 0; i < listCandidateofBirthday.size(); i++) {
							System.out.println("* List ID: " + i);
							listCandidateofBirthday.get(i).Show();
							System.out.println("\n");
						}
						break;

					}

					break;

				// Insert candidate
				case 2:
					try {
						System.out.println("Please choose candidate:");
						System.out.println("0. Experience cadidate");
						System.out.println("1. Fresher candidate:");
						System.out.println("2. Intern candidate:");
						int candidate_choose = Integer.parseInt(sc.nextLine());

						switch (candidate_choose) {

						// Experience
						case 0:
							ex.enterExCandidate();
							db.addCandidate(ex.getFirstName(), ex.getLastName(), ex.getBirthDate(), ex.getAddress(),
									ex.getPhone(), ex.getEmail(), ex.getCandidate_type(), ex.getExpInYear(),
									ex.getProSkill(), null, // graduation date
									null, // graduation rank
									null, // education
									null, // majors
									0, // semester
									null); // university
							break;

						// Fresher
						case 1:
							fr.enterFrCandidate();
							db.addCandidate(fr.getFirstName(), fr.getLastName(), fr.getBirthDate(), fr.getAddress(),
									fr.getPhone(), fr.getEmail(), fr.getCandidate_type(), 0, // expinyear
									null, // proskill
									fr.getGraduation_Date(), fr.getGraduation_Rank(), fr.getEducation(), null, // majors
									0, // semester
									null); // university
							break;

						// Intern
						case 2:
							in.enterInCandidate();
							db.addCandidate(in.getFirstName(), in.getLastName(), in.getBirthDate(), in.getAddress(),
									in.getPhone(), in.getEmail(), in.getCandidate_type(), 0, // expinyear
									null, // proskill
									null, // graduation date
									null, // graduation rank
									null, // education
									in.getMajors(), in.getSemester(), in.getUniversity_Name());
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				// Update candidate
				case 3:
					db.updateCandidate();
					db.showCandidate();
					break;

				// Delete candidate
				case 4:
					System.out.print("Enter Birth Date of Candidate you want to delete: ");
					int idnhanvien = Integer.parseInt(sc.nextLine());
					db.deleteCandidate(idnhanvien);
					break;

				// Sort candidate
				case 5:
					break;

				// Find candidate
				case 6:
					break;

				case 7:
					db.writeFile();
					break;
				case 8:
					db.readFile();
					break;

				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

}
