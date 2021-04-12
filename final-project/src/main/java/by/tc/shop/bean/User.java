package by.tc.shop.bean;

import java.io.Serializable;

public class User implements Serializable {

    public static final int ROLE_CLIENT = 1;
    public static final int ROLE_ADMIN = 2;

    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_BANNED = 2;

    private long id;
    private String username;
    private String password;
    private String email;
    private int role;
    private int status;
    private UserDetails userDetails;

    public User() {
        super();
    }

    public User(long id, String username, String password, String email, int role, UserDetails userDetails) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userDetails = userDetails;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public long getId() {
        return id;
    }

    public int getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
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

        User user = (User) o;

        if (user.id != this.id) {
            return false;
        }

        if (role != user.role) {
            return false;
        }
        if (username == null) {
            if (user.username != null) {
                return false;
            }
        } else if (!username.equals(user.username)) {
            return false;
        }
        if (password == null) {
            if (user.password != null) {
                return false;
            }
        } else if (!password.equals(user.password)) {
            return false;
        }
        if (email == null) {
            if (user.email != null) {
                return false;
            }
        } else if (!email.equals(user.email)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + (userDetails != null ? userDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", userDetails=" + userDetails +
                '}';
    }

    public static Builder getBuilderInstance() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            User.this.setId(id);
            return this;
        }

        public Builder setUsername(String username) {
            User.this.setUsername(username);
            return this;
        }

        public Builder setEmail(String email) {
            User.this.setEmail(email);
            return this;
        }

        public Builder setPassword(String password) {
            User.this.setPassword(password);
            return this;
        }

        public Builder setRole(int role) {
            User.this.setRole(role);
            return this;
        }

        public Builder setUserDetails(UserDetails userDetails) {
            User.this.setUserDetails(userDetails);
            return this;
        }

        public Builder setStatus(int status) {
            User.this.setStatus(status);
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
