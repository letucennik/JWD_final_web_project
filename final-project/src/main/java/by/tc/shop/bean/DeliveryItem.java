package by.tc.shop.bean;

import java.io.Serializable;

public class DeliveryItem implements Serializable {
    private long id;
    private Delivery delivery;
    private Product product;
    private int numberOfProducts;

    public DeliveryItem() {
    }

    public DeliveryItem(long id, Delivery delivery, Product product, int numberOfProducts) {
        this.id = id;
        this.delivery = delivery;
        this.product = product;
        this.numberOfProducts = numberOfProducts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        DeliveryItem that = (DeliveryItem) o;

        if (id != that.id) return false;
        if (numberOfProducts != that.numberOfProducts) return false;
        if (delivery != null ? !delivery.equals(that.delivery) : that.delivery != null) return false;
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + numberOfProducts;
        return result;
    }

    @Override
    public String toString() {
        return "DeliveryItem{" +
                "id=" + id +
                ", delivery=" + delivery +
                ", product=" + product +
                ", numberOfProducts=" + numberOfProducts +
                '}';
    }
}
