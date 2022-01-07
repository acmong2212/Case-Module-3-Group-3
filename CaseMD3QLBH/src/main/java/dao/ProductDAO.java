package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    ConnectMySQL connectMySQL = new ConnectMySQL();
    Connection connection = connectMySQL.getConnection();

    public List<Product> getAll() {
        String getAllSql = "SELECT * FROM casemd3.product join category on product.id = category.idCategory";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("idProduct");
                String name = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String img = resultSet.getString("img");
                String nameCategory = resultSet.getString("nameCategory");
                productList.add(new Product(id, name, price, description, img, nameCategory));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findByName(String findName) {
        String find = "select * from product\n" +
                "join category on product.id = category.idCategory\n" +
                "where nameProduct like '%"+findName+"%'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            ResultSet resultSet = preparedStatement.executeQuery(find);
            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("idProduct");
                String name = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String img = resultSet.getString("img");
                String nameCategory = resultSet.getString("nameCategory");
                productList.add(new Product(id, name, price, description, img, nameCategory));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void edit(Product product) {
        String editSQL = "UPDATE casemd3.product SET `nameProduct` = ?, `price` = ?, `description` = ?, `img` = ?, `id` = ? WHERE (`idProduct` = ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(editSQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImg());
            preparedStatement.setInt(5, product.getIdCategory());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String deleteSQL = "DELETE from casemd3.product where idProduct=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Product product) {
        String saveSQL = "INSERT INTO casemd3.product (nameProduct, price, description, img, id) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveSQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImg());
            preparedStatement.setInt(5, product.getIdCategory());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Product> getAllByCategory(int id) {
        String getAllSql = "SELECT * FROM casemd3.product where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                String name = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String img = resultSet.getString("img");
                int idCategory = Integer.parseInt(resultSet.getString("id"));
                productList.add(new Product(idProduct, name, price, description, img, idCategory));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
