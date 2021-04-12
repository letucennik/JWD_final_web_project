package by.tc.shop.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable {
    public static final int STATUS_IN_CART = 1;
    public static final int STATUS_IN_PROCESS = 2;

    private long id;
    private long userID;
    private Timestamp confirmationDate;
    private Timestamp readyDate;
    private int status;

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(long id, long userID, Timestamp confirmationDate, Timestamp readyDate, int status) {
        this.id = id;
        this.userID = userID;
        this.confirmationDate = confirmationDate;
        this.readyDate = readyDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getUserID() {
        return userID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getConfirmationDate() {
        return confirmationDate;
    }

    public Timestamp getReadyDate() {
        return readyDate;
    }

    public void setConfirmationDate(Timestamp confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public void setReadyDate(Timestamp readyDate) {
        this.readyDate = readyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userID != order.userID) return false;
        if (status != order.status) return false;
        if (confirmationDate != null ? !confirmationDate.equals(order.confirmationDate) : order.confirmationDate != null)
            return false;
        return readyDate != null ? readyDate.equals(order.readyDate) : order.readyDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (confirmationDate != null ? confirmationDate.hashCode() : 0);
        result = 31 * result + (readyDate != null ? readyDate.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", confirmationDate=" + confirmationDate +
                ", readyDate=" + readyDate +
                ", status=" + status +
                '}';
    }

    public static Builder getBuilderInstance() {
        return new Order().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            Order.this.setId(id);
            return this;
        }

        public Builder setUserId(long userId) {
            Order.this.setUserID(userId);
            return this;
        }

        public Builder setConfirmationDate(Timestamp confirmationDate) {
            Order.this.setConfirmationDate(confirmationDate);
            return this;
        }

        public Builder setReadyDate(Timestamp readyDate) {
            Order.this.setReadyDate(readyDate);
            return this;
        }

        public Builder setStatus(int status) {
            Order.this.setStatus(status);
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
