package lurdak.try3.Model;

/**
 * Created by Lurdak on 6/7/2018.
 */

public class Company {
    private String id;
    private String name;
    private  String phoneNumber;

    public Company(String id) {
        this.id = id;
    }

    public Company() {
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
