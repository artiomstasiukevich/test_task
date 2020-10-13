import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private String id = "";
    private Date date = new Date();
    private double value = 0;
    private String merchant = "";
    private String type = "";
    private String id_to_reverse = "";

    public Transaction() {
    }

    public Transaction(String id, Date date, double value, String merchant, String type, String id_to_reverse) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.merchant = merchant;
        this.type = type;
        this.id_to_reverse = id_to_reverse;
    }

    public Transaction(String id, Date date, double value, String merchant, String type) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.merchant = merchant;
        this.type = type;
        this.id_to_reverse = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_to_reverse() {
        return id_to_reverse;
    }

    public void setId_to_reverse(String id_to_reverse) {
        this.id_to_reverse = id_to_reverse;
    }

    public boolean IsReverse() {
        return !id_to_reverse.equals("");
    }
}
