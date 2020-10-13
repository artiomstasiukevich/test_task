import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TransactionTest {

    @Test
    public void CreatingTest() throws ParseException {
        DateFormat df = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss", Locale.ENGLISH);
        Transaction test1 = new Transaction("1145673a56",
                df.parse("11/10/2020 12:10:03"),
                12.34,
                "Adobe",
                "pay",
                "123456");
        Assert.assertEquals(test1.getId(), "1145673a56");
        Assert.assertEquals(test1.getDate(), df.parse("11/10/2020 12:10:03"));
        Assert.assertEquals(test1.getValue(), 12.34, 1e-13);
        Assert.assertEquals(test1.getMerchant(), "Adobe");
        Assert.assertEquals(test1.getType(), "pay");
        Assert.assertEquals(test1.getId_to_reverse(), "123456");
    }

}