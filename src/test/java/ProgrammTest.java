import com.opencsv.exceptions.CsvException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//This test is valid if you comment reading CSV file in constructor of Programm
public class ProgrammTest {

    @Test
    public void LogicTest() throws ParseException, IOException, CsvException {
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", Locale.ENGLISH);
        Transaction test1 = new Transaction("1145673a56",
                df.parse("11/10/2020 12:10:03"),
                12.34,
                "Adobe",
                "pay",
                "123456");

        Transaction test2 = new Transaction("1145673a56",
                df.parse("11/10/2021 12:10:03"),
                12.34,
                "Adobe",
                "pay",
                "");

        ArrayList<Transaction> testlist1 = new ArrayList<>();
        testlist1.add(test1);
        testlist1.add(test2);
        Programm treans = new Programm("777", testlist1);
        treans.InitAnalysis(df.parse("11/10/2020 12:09:03"),
                df.parse("11/10/2020 12:11:03"), "Adobe");

        Assert.assertEquals(treans.getTotalAmount(), 0);
        Assert.assertEquals(treans.getAverageValue(), 0, 1e-13);
        Assert.assertEquals(treans.getTotalValue(), 0, 1e-13);

        treans.InitAnalysis(df.parse("11/10/2019 12:09:03"),
                df.parse("11/10/2022 12:11:03"), "Adobe");
        Assert.assertEquals(treans.getTotalAmount(), 1);
        Assert.assertEquals(treans.getAverageValue(), 12.34, 1e-13);
        Assert.assertEquals(treans.getTotalValue(), 12.34, 1e-13);

        testlist1.clear();
        testlist1.add(new Transaction("WLMFRDGD",
                df.parse("20/08/2018 12:45:33"),
                59.99,
                "Kwik-E-Mart",
                "PAYMENT"));
        testlist1.add(new Transaction("YGXKOEIA",
                df.parse("20/08/2018 12:46:17"),
                10.95,
                "Kwik-E-Mart", "PAYMENT"));
        testlist1.add(new Transaction("LFVCTEYM",
                df.parse("20/08/2018 12:50:02"),
                5.00, "MacLaren", "PAYMENT"));
        testlist1.add(new Transaction("SUOVOISP",
                df.parse("20/08/2018 13:12:22"),
                5.00,
                "Kwik-E-Mart", "PAYMENT"));
        testlist1.add(new Transaction("AKNBVHMN",
                df.parse("20/08/2018 13:14:11"),
                10.95,
                "Kwik-E-Mart", "REVERSAL", "YGXKOEIA"));
        testlist1.add(new Transaction("JYAPKZFZ",
                df.parse("20/08/2018 14:07:10"),
                99.50, "MacLaren", "PAYMENT"));

        treans.InitAnalysis(df.parse("20/08/2018 12:00:00"),
                df.parse("20/08/2018 13:00:00"),
                "Kwik-E-Mart");
        Assert.assertEquals(treans.getTotalAmount(), 1);
        Assert.assertEquals(treans.getAverageValue(), 59.99, 1e-13);



    }
}