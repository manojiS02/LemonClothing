package lk.ijse.CherryClothing.dto;

public class CustomDTO {
    //customer
    private String id;
    private String name;
    private String address;
    private String contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact(){return contact;}

    public void setContact(String contact){this.contact = contact;}
}
