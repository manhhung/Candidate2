package Home;

public class BirthDateUtil {
	
	 // Method này làm nhiệm vụ kiểm tra năm sinh
	  // Nếu năm sinh nhỏ hơn 1900 method sẽ ném ra ngoại lệ TooOldException
	  // Nếu năm sinh lớn hơn 2017 method sẽ ném ra ngoại lệ TooYoungException
	public static void checkBirthDate(int birthDate) throws TooYoungException, TooOldException{
		if (birthDate < 1900){
			// Method này kết thúc tại đây khi được ném ra ngoại lệ
			throw new TooOldException("Birth Date "+birthDate+" too old!");
		} else if (birthDate > 2017){
			// Method này kết thúc tại đây khi được ném ra ngoại lệ
			throw new TooYoungException("Birth Date " +birthDate+ "too young");
		}
		System.out.println("BirthDate "+birthDate+" ok!");
	}

}
