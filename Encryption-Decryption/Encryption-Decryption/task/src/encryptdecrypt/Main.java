package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        new Encryption(args);
    }

    static class Encryption {

        @SuppressWarnings("unused")
        private String alg, mode, key, data, in, out;
        private boolean input = true, output = true;
        char[] cypher;

        public Encryption(String[] args) throws Exception {
            assignArgs(args);
            checkFields();
            setCypher();
            executeOperation(Integer.parseInt(key), mode.equals("enc"));
        }

        private void assignArgs(String[] args) throws NoSuchFieldException, IllegalAccessException {
            for (int i = 0; i < args.length - 1; i += 2)
                Encryption.class.getDeclaredField(args[i].replace("-", "")).set(this, args[i + 1]);
        }

        private void checkFields() {
            if (alg == null) alg = "shift";
            if (mode == null) mode = "enc";
            if (key == null) key = "0";
            if (in == null) input = false;
            if (out == null) output = false;
            if (data == null && !input) data = "";
        }

        private void setCypher() throws Exception {
            if (data == null) {
                File file = new File(in);
                try (Scanner scan = new Scanner(file)) {
                    while (scan.hasNextLine()) data = scan.nextLine();
                } catch (FileNotFoundException e) { throw new Exception("Error: file not found"); }
            }
            cypher = data.toCharArray();
        }

        private void executeOperation(int key, boolean encrypt) throws Exception {
            int sign = encrypt ? 1 : -1, offset = key;
            if (!encrypt) offset = 26 - (offset % 26);
            for (int i = 0; i < cypher.length; ++i)
                if (alg.equals("unicode")) cypher[i] = (char) (cypher[i] + key * sign);
                else if (Character.isAlphabetic(cypher[i]))
                    if (Character.isLowerCase(cypher[i])) cypher[i] = (char) (((cypher[i] - 'a' + offset) % 26) + 'a');
                    else cypher[i] = (char) (((cypher[i] - 'A' + offset) % 26) + 'A');
            processOutput();
        }

        private void processOutput() throws Exception {
            if (output) {
                File file = new File(out);
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println(cypher);
                } catch (FileNotFoundException e) { throw new Exception("Error: file not found"); }
            } else System.out.println(cypher);
        }

    }

}
