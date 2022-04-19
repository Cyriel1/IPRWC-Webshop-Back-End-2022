package nl.hsleiden.webshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    @Size(max = 65)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 65)
    private String lastName;

    @Column(name = "address")
    @Size(max = 120)
    private String address;

    @Column(name = "zip")
    @Size(max = 6)
    private String zip;

    @Column(name = "city")
    @Size(max = 50)
    private String city;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(((\\\\+31|0|0031)6){1}[1-9]{1}[0-9]{7})$")
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
