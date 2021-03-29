import java.util.HashSet;
import java.util.Set;

/**
 * Problem: 929
 * Source: https://leetcode.com/problems/unique-email-addresses/
 */
public class UniqueEmailAddresses {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for (int i = 0; i < emails.length; i++) {
            String parsedEmail = parseEmail(emails[i]);
            uniqueEmails.add(parsedEmail);
        }
        return uniqueEmails.size();
    }
    private String parseEmail(String email) {
        String[] emailParts = email.split("@");
        String parsedLocalName = emailParts[0].replaceAll("\\.", "");
        parsedLocalName = parsedLocalName.replaceAll("\\+.*", "");
        StringBuilder sb = new StringBuilder();
        sb.append(parsedLocalName).append("@").append(emailParts[1]);
        return sb.toString();
    }
}
