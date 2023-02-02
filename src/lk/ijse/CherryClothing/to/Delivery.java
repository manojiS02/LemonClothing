package lk.ijse.CherryClothing.to;

public class Delivery {
    private String id;
    private String location;



    public Delivery() {
    }

    public Delivery(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Customer{" + "id='" + id + '\'' + ", location='" + location + '}';
    }
}


