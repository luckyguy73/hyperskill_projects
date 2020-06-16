import encryptdecrypt.Main;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;

public class EncryptDecryptTest extends StageTest<String> {
    public EncryptDecryptTest() {
        super(Main.class);
    }

    @Override
    public List<TestCase<String>> generate() {
        return List.of(
            new TestCase<String>().setAttach("dv ulfmw z givzhfiv!")
        );
    }

    @Override
    public CheckResult check(String reply, String clue) {
        reply = reply.trim();
        clue = clue.trim();
        boolean isCorrect = reply.trim().equals(clue.trim());
        if (isCorrect) {
            return CheckResult.correct();
        }
        if (reply.length() != clue.length()) {
            return CheckResult.wrong(
                "You should output a line with length " +
                clue.length() + ". " + "You output a " +
                "line with length " + reply.length()
            );
        }
        for (int i = 0; i < clue.length(); i++) {
            if (reply.charAt(i) != clue.charAt(i)) {
                return CheckResult.wrong(
                    "Your " + (i+1) + "-th character '" + reply.charAt(i) + "'" +
                    " is incorrect. The right one is '" + clue.charAt(i) + "'"
                );
            }
        }
        return CheckResult.correct();
    }
}
