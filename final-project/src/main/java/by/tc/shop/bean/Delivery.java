package by.tc.shop.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Delivery implements Serializable {
    private long id;
    private Timestamp date;

    public Delivery() {
    }

    public Delivery(long id, Timestamp date) {
        this.id = id;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Delivery delivery = (Delivery) o;

        if (id != delivery.id) return false;
        return date != null ? date.equals(delivery.date) : delivery.date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
