
package charity.model;


public class Organization {
    private int id;
    private String name;
    private String email;
    private String hotline;
    private String address;

    public Organization() {
    }

    public Organization(int id, String name, String email, String hotline, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hotline = hotline;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
