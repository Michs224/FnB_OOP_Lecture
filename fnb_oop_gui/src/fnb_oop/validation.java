package fnb_oop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
	public boolean passwordValidation(String pass) {
		int passLength = pass.length();
		Pattern specialPattern = Pattern.compile("[^a-zA-Z0-9]");
		Pattern upperCasePattern = Pattern.compile("[A-Z]");
		Pattern numberPattern = Pattern.compile("[0-9]");
		Matcher isContainSpecial = specialPattern.matcher(pass);
		Matcher isContainUpperCase = upperCasePattern.matcher(pass);
		Matcher isContainNumber = numberPattern.matcher(pass);
		//System.out.println("called");
		
		if(passLength < 8 || passLength > 16)
			return true;
		else if(!isContainSpecial.find())
			return true;
		else if(!isContainUpperCase.find())
			return true;
		else if(!isContainNumber.find())
			return true;
		else
			return false;
	}
}
