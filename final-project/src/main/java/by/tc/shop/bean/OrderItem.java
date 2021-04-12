package by.tc.shop.bean;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private long id;
    private Order order;
    private Product product;
    private int numberOfProducts;

    public OrderItem() {
    }

    public OrderItem(long id, Order order, Product product, int numberOfProducts) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.numberOfProducts = numberOfProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public Order getOrder() {
        return order;
    }


    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (numberOfProducts != orderItem.numberOfProducts) return false;
        if (order != null ? !order.equals(orderItem.order) : orderItem.order != null) return false;
        return product != null ? product.equals(orderItem.product) : orderItem.product == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + numberOfProducts;
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", numberOfProducts=" + numberOfProducts +
                '}';
    }

    public static Builder getBuilderInstance() {
        return new OrderItem().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            OrderItem.this.setId(id);
            return this;
        }

        public Builder setOrder(Order order) {
            OrderItem.this.setOrder(order);
            return this;
        }

        public Builder setProduct(Product product) {
            OrderItem.this.setProduct(product);
            return this;
        }

        public Builder setNumberOfProducts(int numberOfProducts) {
            OrderItem.this.setNumberOfProducts(numberOfProducts);
            return this;
        }

        public OrderItem build() {
            return OrderItem.this;
        }
    }
}
