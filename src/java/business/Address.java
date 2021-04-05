/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Osama Kheireddine
 */
public class Address {

    private int id;
    private int userId;
    private String addrLine1;
    private String addrLine2;
    private String town;
    private String county;
    private String country;

    /**
     * This constructor will create an instance of an address which also has a
     * user's id, this will be added to the db
     *
     * @param id
     * @param userId
     * @param addrLine1
     * @param addrLine2
     * @param town
     * @param county
     * @param country
     */
    public Address(int id, int userId, String addrLine1, String addrLine2, String town, String county, String country) {
        this.id = id;
        this.userId = userId;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.town = town;
        this.county = county;
        this.country = country;
    }

    public Address(int userId, String line1, String line2, String town, String county, String country) {
        this.userId = userId;
        this.addrLine1 = line1;
        this.addrLine2 = line2;
        this.town = town;
        this.county = county;
        this.country = country;
    }

    //getters and setters and to string 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //to sttring 
    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", userId=" + userId + ", addrLine1=" + addrLine1 + ", addrLine2=" + addrLine2 + ", town=" + town + ", county=" + county + ", country=" + country + '}';
    }

}
