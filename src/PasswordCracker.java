import java.util.ArrayList;
import java.util.List;

public class PasswordCracker {
    static String passwordCracker(String[] pass, String attempt) {
        int length = attempt.length();
        if(pass.length == 0 || length == 0) { return "WRONG PASSWORD"; }

        //Init everything to -1 for "Not Found"
        List<Integer> passwordAttempts = new ArrayList<Integer>(length);
        passwordAttempts.add(0);
        for(int i = 1; i < length; i++) {
            passwordAttempts.add(-1);
        }

        for(int i = 0; i < length; i++) {
            if(passwordAttempts.get(i) == -1) { continue; }

            for(int j = 0; j < pass.length; j++) {
                String currentPass = pass[j];
                int currentEnd = i + currentPass.length();
                if (currentEnd < length &&
                        passwordAttempts.get(currentEnd) == -1) {
                    if(attempt.substring(i, currentEnd).equals(currentPass)) {
                        passwordAttempts.set(currentEnd, j);
                    }
                } else if (currentEnd == length && attempt.substring(i, currentEnd).equals(currentPass)) {
                    return printTraceback(pass, passwordAttempts, currentPass);
                }
            }
        }

        return "WRONG PASSWORD";
    }

    static String printTraceback(String[] pass, List<Integer> passwordAttempts, String finalPass) {
        int currentTrace = passwordAttempts.size();
        StringBuilder builder = new StringBuilder();
        builder.append(" " + finalPass);
        currentTrace -= finalPass.length();
        while (currentTrace != 0) {
            String currentPassword = pass[passwordAttempts.get(currentTrace)];
            builder.insert(0, currentPassword);
            builder.insert(0, " ");

            currentTrace -= currentPassword.length();
        };

        builder.delete(0, 1);

        return builder.toString();
    }

    public static void main(String[] args) {
        String[] pass = new String[]{"ab", "abcd", "cd"};
        String attempt = "abcd";

        System.out.println(passwordCracker(pass, attempt));
    }
}
