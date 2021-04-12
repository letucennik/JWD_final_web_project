package by.tc.shop.bean;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String nameEn;
    private String nameRu;
    private String picturePath;
    private double price;
    private String descriptionEn;
    private String descriptionRu;
    private Category category;
    private int numberOfProducts;

    public Product() {
    }
    public Product(long id){
        this.id=id;
    }

    public Product(long id, String nameEn, String nameRu, String picturePath, String descriptionEn, String descriptionRu, double price,
                   Category category) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameRu = nameRu;
        this.picturePath = picturePath;
        this.price = price;
        this.descriptionEn = descriptionEn;
        this.descriptionRu = descriptionRu;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public double getPrice() {
        return price;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (nameEn != null ? !nameEn.equals(product.nameEn) : product.nameEn != null) return false;
        if (nameRu != null ? !nameRu.equals(product.nameRu) : product.nameRu != null) return false;
        if (picturePath != null ? !picturePath.equals(product.picturePath) : product.picturePath != null) return false;
        if (descriptionEn != null ? !descriptionEn.equals(product.descriptionEn) : product.descriptionEn != null)
            return false;
        if (descriptionRu != null ? !descriptionRu.equals(product.descriptionRu) : product.descriptionRu != null)
            return false;
        return category != null ? category.equals(product.category) : product.category == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (descriptionEn != null ? descriptionEn.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", price=" + price +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionRu='" + descriptionRu + '\'' +
                ", category=" + category +
                '}';
    }

    public static Builder getBuilderInstance() {
        return new Product().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            Product.this.setId(id);
            return this;
        }

        public Builder setNameEn(String name) {
            Product.this.setNameEn(name);
            return this;
        }

        public Builder setNameRu(String name) {
            Product.this.setNameRu(name);
            return this;
        }

        public Builder setCategory(Category category) {
            Product.this.setCategory(category);
            return this;
        }

        public Builder setPicturePath(String picturePath) {
            Product.this.setPicturePath(picturePath);
            return this;
        }

        public Builder setDescriptionEn(String description) {
            Product.this.setDescriptionEn(description);
            return this;
        }

        public Builder setDescriptionRu(String description) {
            Product.this.setDescriptionRu(description);
            return this;
        }

        public Builder setPrice(double price) {
            Product.this.setPrice(price);
            return this;
        }

        public Product build() {
            return Product.this;
        }

    }

}

