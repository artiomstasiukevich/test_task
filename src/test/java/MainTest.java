import com.opencsv.exceptions.CsvException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class MainTest {
    @Test
    public void GeneralTest() throws ParseException, IOException, CsvException {
        Main.main(new String[] {"src/main/java/csvfile.csv",
                "20/08/2018 12:00:00",
                "20/08/2018 13:00:00",
                "Kwik-E-Mart"});
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("result.txt"));
            Assert.assertEquals(reader.readLine(), "total transactions:1");
            Assert.assertEquals(reader.readLine(), "average value:59.99");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

    }
}