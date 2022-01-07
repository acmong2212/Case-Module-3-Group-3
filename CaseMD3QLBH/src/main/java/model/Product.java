package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private String img;
    private String nameCategory;
    private int idCategory;

    public Product() {
    }

    public Product(int id, String name, int price, String description, String img, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, int price, int quantity, String description, String img, String nameCategory, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.img = img;
        this.nameCategory = nameCategory;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, int price, String description, String img, String nameCategory, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.nameCategory = nameCategory;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, int price, String description, String img, String nameCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.nameCategory = nameCategory;
    }

    public Product(String name, int price, String description, String img, int idCategory) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.idCategory = idCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
