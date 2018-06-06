package lee.utils;

import java.util.Random;

/**
 * 生成唯一Id
 */
public class IDGenerator {
	
	/* Generating functions */
	public static String 	CHAR_LOWERCASE 	= 	"abcdefghijklmnopqrstuvwxyz";
	public static String 	CHAR_UPPERCASE 	= 	"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String 	CHAR_NUMBERS 	= 	"0123456789";
	public static String 	CHAR_SPECIAL 	= 	"~@#^()[]*$-+?_&=!%{}/";
	public static String 	CHAR_DEFAULT	=	CHAR_LOWERCASE + CHAR_UPPERCASE + CHAR_NUMBERS;
	private static Random 	random 			=	new Random();
	public static int		DEFAULT_LENGTH	=	15;
	 
	public static String gen() {
		return gen(DEFAULT_LENGTH);
	}
	
	public static String gen(int length) {
		return gen(CHAR_DEFAULT, length);
    }
	
	public static String gen(final String charString, int length) {
		if (length < 1) {return "";}
		int i = 0;
	    StringBuffer password = new StringBuffer("");
	    while(i < length) {
	    	int randomPosition = random.nextInt(charString.length());
//	    	System.out.print(randomPosition);
//	       	if(password.indexOf(charString.charAt(randomPosition) + "") < 0) {		//duplicate check
	       		password.append(charString.charAt(randomPosition));
	       		i++;
//	       	}
	    }
	    return password.toString();
	}
	
	public static String gen(int lowerCase, int upperCase, int numbers, int special) {
		StringBuffer password = new StringBuffer("");
		password.append(gen(CHAR_LOWERCASE,lowerCase));
		password.append(gen(CHAR_NUMBERS,numbers));
		password.append(gen(CHAR_UPPERCASE,upperCase));
		password.append(gen(CHAR_SPECIAL,special));
		return gen(password.toString(), password.length());//random generator String  by sequence password
	}
	
	public static void main(String[] args){
		for(int i = 0; i < 100 ;i ++){
			System.out.println(IDGenerator.gen(IDGenerator.CHAR_NUMBERS,20));
		}
	}
}
