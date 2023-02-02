package lk.ijse.CherryClothing.to;

public class Payment {
    private String id;
    private String date;
    private String time;
    private String method;


    public Payment() {
    }

    public Payment(String id, String date, String time, String method) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.method = method;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String address) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        return "Payment{" + "id='" + id + '\'' + ", date='" + date + '\'' + ", time='" + time + '\'' + ", method=" + method + '}';
    }
}
