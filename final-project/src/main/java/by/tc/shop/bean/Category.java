package by.tc.shop.bean;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private String name;
    private String nameRu;

    public Category() {

    }

    public Category(long id) {
        this.id = id;
    }

    public Category(long id, String name, String nameRu) {
        this.id = id;
        this.name = name;
        this.nameRu = nameRu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameRu() {
        return nameRu;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameRu='" + nameRu + '\'' +
                '}';
    }
}
