
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException, CsvException, ParseException {
        Programm logic = new Programm(args[0]);
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", Locale.ENGLISH);
        logic.InitAnalysis(df.parse(args[1]), df.parse(args[2]), args[3]);
        PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
        writer.println(logic.toString());
        writer.close();
    }
}
