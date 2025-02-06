package howmuch.com.common;

public class RandomCreateManager {
	public static String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#', '$', '%', '^'};

        String newPassword = "";

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            newPassword += charSet[idx];
        }

        return newPassword;
    }
    
    public static String getValidateCode() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        String code = "";

        for (int i = 0; i < 6; i++) {
            int idx = (int) (charSet.length * Math.random());
            code += charSet[idx];
        }

        return code;
    }
}
