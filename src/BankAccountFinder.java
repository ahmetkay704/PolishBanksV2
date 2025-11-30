import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class BankAccountFinder {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first 3 digits of bank account: ");
        String prefix = scanner.nextLine().trim();
        scanner.close();

        URL url = new URL("https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "Cp1250"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length >= 4 && parts[3].contains(prefix)) {
                System.out.println("Abbreviated bank number: " + prefix);
                System.out.println("Bank name: " + parts[1].trim());
                System.out.println("\nYour account is in: " + parts[1].trim());
                reader.close();
                return;
            }
        }

        reader.close();
        System.out.println("Bank not found");
    }
}