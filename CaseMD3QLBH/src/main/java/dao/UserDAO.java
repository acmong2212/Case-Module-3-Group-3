package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    ConnectMySQL connectMySQL = new ConnectMySQL();
    Connection connection = connectMySQL.getConnection();

    public List<User> getAll(){
        String sqlGetAll = "SELECT * FROM casemd3.users";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();

            while (resultSet.next()){
                int id = resultSet.getInt("idUser");
                String userName = resultSet.getString("userName");
                String pass = resultSet.getString("pass");
                String emailUser = resultSet.getString("emailUser");
                String roll = resultSet.getString("roll");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                userList.add(new User(id,userName,pass,emailUser,roll,address,phoneNumber));
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void create(User user){
        String creatSQL = "INSERT INTO casemd3.users (userName, pass, emailUser , address, phoneNumber) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(creatSQL);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPass());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setString(5,user.getPhoneNumber());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String deleteSQL = "DELETE from casemd3.users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void edit (User user){
        String editSQL = "UPDATE casemd3.users SET userName = ?, pass = ?, email = ?, address = ?, phoneNumber = ? WHERE (id = ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(editSQL);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPass());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setString(5,user.getPhoneNumber());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User checkLogin(String userName, String pass ){
        String loginSQL = "SELECT * FROM casemd3.users where userName = ? and pass = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(loginSQL);
            preparedStatement.setString(1 , userName);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User checkUserName(String userName){
        String loginSQL = "SELECT * FROM casemd3.users where userName = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(loginSQL);
            preparedStatement.setString(1 , userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
