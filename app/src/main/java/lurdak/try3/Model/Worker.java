package lurdak.try3.Model;

import android.graphics.Bitmap;

import java.util.Date;

import lurdak.try3.Util.Constants;

/**
 * Created by Lurdak on 6/6/2018.
 */

public class Worker {
    private String name;
    private String id;
    private String Family;
    private Date birthday;
    private String phoneNumber;
    private Date startDAte;
    private String gender;
    private String idImgUrl;
    private String p101Url;
    private Bitmap faceImg;
    private String email;
    private double wage;
    private double transport;
    public Worker() {
    }

    public Worker(String id) {
        this.id = id;
    }

    public Worker(String name, String id, String family, Date birthday, String phoneNumber, Date startDAte, String gender, String idImgUrl, String p101Url) {
        this.name = name;
        this.id = id;
        Family = family;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.startDAte = startDAte;
        this.gender = gender;
        this.idImgUrl = idImgUrl;
        this.p101Url = p101Url;
    }

    public Worker(String name, String id, String family, Date birthday, String phoneNumber, Date startDAte, String gender, String idImgUrl, String p101Url, Bitmap faceImg) {
        if(name!=null && this.checkId(id)!="0" && family!=null && birthday.before(new Date()) && gender!=null && idImgUrl!=null && p101Url!=null && faceImg!=null) {
            this.name = name;
            this.id = id;
            Family = family;
            this.birthday = birthday;
            this.phoneNumber = phoneNumber;
            this.startDAte = startDAte;
            this.gender = gender;
            this.idImgUrl = idImgUrl;
            this.p101Url = p101Url;
            this.faceImg = faceImg;
        }
    }

    public Worker(String name, String id, String family, Date birthday, String phoneNumber, Date startDAte, String gender) {
        if (name != null && this.checkId(id) != "0" && family != null && birthday.before(new Date()) && gender != null) {
            {
                this.name = name;
                this.id = id;
                Family = family;
                this.birthday = birthday;
                this.phoneNumber = phoneNumber;
                this.startDAte = startDAte;
                this.gender = gender;
            }
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name!=null)
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

            this.id = id;

    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
     if (family!=null)
        Family = family;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getStartDAte() {
        return startDAte;
    }

    public void setStartDAte(Date startDAte) {
        this.startDAte = startDAte;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdImgUrl() {
        return idImgUrl;
    }

    public void setIdImgUrl(String idImgUrl) {
        this.idImgUrl = idImgUrl;
    }

    public String getP101Url() {
        return p101Url;
    }

    public void setP101Url(String p101Url) {
        this.p101Url = p101Url;
    }

    public Bitmap getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(Bitmap faceImg) {
        this.faceImg = faceImg;
    }

    public String getEmail() {
        return email;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String checkId(String id) {
        if (id.length() == Constants.ID_NUMBER_SIZE) {
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i)))
                    return "0";
            }
            return id;
        }
        return "0";
        //TODO
    }
}
