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
public class User {

    //create fields for the user
    private int id;
    private String profilePicture;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String addrLine1;
    private String addrLine2;
    private String town;
    private String county;
    private String country;
    private String gender;
    private String username;
    private String password;
    private String addressId;
    private String sellerRating;
    private String status;

    public User() {
    }

    public User(String username, String status, String passowrd, String fName, String lName, String email, String addressId, String phone, String sellerRating, String gender, String profilePicture) {

        this.username = username;
        this.password = passowrd;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.addressId = addressId;
        this.phone = phone;
        this.sellerRating = sellerRating;
        this.gender = gender;
        this.profilePicture = profilePicture;
        this.status = status;

    }

    public User(String profilePicture, String status, String fName, String lName, String email, String phone, String addrLine1, String addrLine2, String town, String county, String country, String gender, String username, String password) {
        this.status = status;
        this.profilePicture = profilePicture;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.town = town;
        this.county = county;
        this.country = country;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String fName, String lName, String email, String addressId, String phone, String sellerRating, String gender, String profile) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.addressId = addressId;
        this.phone = phone;
        this.sellerRating = sellerRating;
        this.gender = gender;
        this.profilePicture = profile;
        
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public String getTown() {
        return town;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", profilePicture=" + profilePicture + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", phone=" + phone + ", addrLine1=" + addrLine1 + ", addrLine2=" + addrLine2 + ", town=" + town + ", county=" + county + ", country=" + country + ", gender=" + gender + ", username=" + username + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
