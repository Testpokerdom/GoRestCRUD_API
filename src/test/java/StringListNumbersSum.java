import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StringListNumbersSum {
    static double firstValue;
    static String digitalValueFromList;
    static double sumFirstAndNext;


    public static void main(String[] args) throws IOException {
        File file = new File("numbers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((digitalValueFromList = reader.readLine()) != null){

            if (digitalValueFromList.isEmpty() || digitalValueFromList.equals(null) || digitalValueFromList.contains("#")){
                continue;
            }

            firstValue = Double.parseDouble(digitalValueFromList);
            sumFirstAndNext += firstValue;
            System.out.println(sumFirstAndNext);

        }
    }
}
