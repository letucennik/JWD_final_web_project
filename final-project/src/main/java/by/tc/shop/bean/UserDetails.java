package by.tc.shop.bean;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String city;
    private String phone;
    private String address;
    private long userID;

    public UserDetails() {

    }

    public UserDetails(long id, long userID, String firstName, String lastName, String city, String phone, String address) {
        this.id = id;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.phone = phone;
        this.address = address;
    }


    public long getId() {
        return id;
    }

    public long getUserID() {
        return userID;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        UserDetails userDetails = (UserDetails) o;

        if (userDetails.id != this.id) {
            return false;
        }

        if (userDetails.userID != this.userID) {
            return false;
        }
        if (this.firstName == null) {
            if (userDetails.firstName != null) {
                return false;
            }
        } else if (!this.firstName.equals(userDetails.firstName)) {
            return false;
        }
        if (this.lastName == null) {
            if (userDetails.lastName != null) {
                return false;
            }
        } else if (!this.lastName.equals(userDetails.lastName)) {
            return false;
        }
        if (this.address == null) {
            if (userDetails.address != null) {
                return false;
            }
        } else if (!this.address.equals(userDetails.address)) {
            return false;
        }
        if (this.city == null) {
            if (userDetails.city != null) {
                return false;
            }
        } else if (!this.city.equals(userDetails.city)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userID=" + userID +
                '}';
    }

    public static Builder getBuilderInstance() {
        return new UserDetails().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setID(long id) {
            UserDetails.this.setId(id);
            return this;
        }

        public Builder setFirstName(String firstName) {
            UserDetails.this.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName) {
            UserDetails.this.setLastName(lastName);
            return this;
        }

        public Builder setCity(String city) {
            UserDetails.this.setCity(city);
            return this;
        }

        public Builder setPhone(String phone) {
            UserDetails.this.setPhone(phone);
            return this;
        }

        public Builder setAddress(String address) {
            UserDetails.this.setAddress(address);
            return this;
        }

        public Builder setUserId(long userId) {
            UserDetails.this.setUserID(userId);
            return this;
        }

        public UserDetails build() {
            return UserDetails.this;
        }
    }
}
