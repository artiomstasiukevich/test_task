import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyCSVReader {

    static ArrayList<Transaction> ReadTransactions(String name) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(name));

        ArrayList<Transaction> emps = new ArrayList<Transaction>();

        List<String[]> records = reader.readAll();

        Iterator<String[]> iterator = records.iterator();
        iterator.next();
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", Locale.ENGLISH);
        while (iterator.hasNext()) {
            String[] record = iterator.next();
            Transaction emp = new Transaction();
            emp.setId(record[0]);
            try {
                emp.setDate(df.parse(record[1]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            emp.setValue(Double.parseDouble(record[2]));
            emp.setMerchant(record[3]);
            emp.setType(record[4]);
            if(record.length == 6) {
                emp.setId_to_reverse(record[5]);
            } else {
                emp.setId_to_reverse("");
            }
            emps.add(emp);
        }
        System.out.println(emps);
        reader.close();
        return emps;
    }

}
