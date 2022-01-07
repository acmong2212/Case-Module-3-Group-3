package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    List<Product> productListCart = new ArrayList<>();

    public List<Product> getCart() {
        return productListCart;
    }

    public int findById(int id) {
        for (int i = 0; i < productListCart.size(); i++) {
            if (id == productListCart.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public void saveCart(Product product) {
        productListCart.add(product);
    }

    public int sumCart(){
        int sum = 0;
        for (int i = 0; i < productListCart.size(); i++) {
            sum += productListCart.get(i).getPrice();
        }
        return sum;
    }

}
