import com.opencsv.exceptions.CsvException;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class Programm {
    private String csv_path;
    private ArrayList<Transaction> data = new ArrayList<>();
    private double TotalValue = 0;
    private double AverageValue = 0;
    private int TotalAmount = 0;

    public Programm(String csv_path) {
        this.csv_path = csv_path;
    }

    public void InitAnalysis(Date begin, Date end,
                             String merchant) throws IOException, CsvException {
        data = MyCSVReader.ReadTransactions(csv_path);
        CountNum(begin, end, merchant);
        TotalValue(begin, end, merchant);
        CountAverageValue();
    }

    public double getTotalValue() {
        return TotalValue;
    }

    public double getAverageValue() {
        return AverageValue;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public Programm(String csv_path, ArrayList<Transaction> data) {
        this.csv_path = csv_path;
        this.data = data;
    }

    private void TotalValue(Date begin, Date end, String merchant) {
        TotalValue = 0;
        for (Transaction elem : data) {
            if (elem.getDate().after(begin) &&
                    elem.getDate().before(end) &&
                    elem.getMerchant().equals(merchant)) {
                if (!elem.IsReverse()) {
//                TotalValue -= elem.getValue();
//            } else {
                    TotalValue += elem.getValue();
                }
            }
            if (elem.IsReverse() &&
                    FindMercant(merchant,
                            elem.getId_to_reverse(),
                            begin, end)) {
                TotalValue -= elem.getValue();
            }
        }
    }

    private void CountNum(Date begin, Date end, String merchant) {
        TotalAmount = 0;
        for (Transaction elem : data) {
            if (elem.getDate().after(begin) &&
                    elem.getDate().before(end) &&
                    elem.getMerchant().equals(merchant))
                if (!elem.IsReverse()) {
                    TotalAmount += 1;
                }
            if (elem.IsReverse() &&
                    FindMercant(merchant,
                            elem.getId_to_reverse(),
                            begin, end)) {
                TotalAmount -= 1;
            }
        }
    }

    private void CountAverageValue() {
        if (TotalAmount == 0 ||
                Double.compare(TotalValue, 0) == 0) {
            AverageValue = 0;
            return;
        }
        AverageValue = TotalValue / TotalAmount;
    }

    @Override
    public String toString() {
        return "total transactions:" + TotalAmount + "\n" +
                "average value:" + String.format("%.2f", AverageValue);
    }

    private boolean FindMercant(String merchant, String id_to_reverse, Date begin, Date end) {
        for (Transaction elem : data) {
            if (elem.getId().equals(id_to_reverse) &&
                    elem.getMerchant().equals(merchant) &&
                    elem.getDate().before(end) &&
                    elem.getDate().after(begin)) {
                return true;
            }
        }
        return false;
    }
}
