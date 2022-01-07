package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    UserDAO userDAO = new UserDAO();
    List<User> userList = userDAO.getAll();

    public List<User> fillAll(){
        return userList;
    }

    public void create(User user){
        userDAO.create(user);
        userList = userDAO.getAll();
    }

    public void delete(int id){
        userDAO.delete(id);
        userList = userDAO.getAll();

    }

    public int findById(int id){
        for (int i = 0; i < userList.size(); i++) {
            if (id == userList.get(i).getId()){
                return i;
            }
        }
        return -1;
    }

    public void edit(User user){
        userDAO.edit(user);
        userList = userDAO.getAll();
    }

    public User checkLogin(String userName, String pass ){
        return userDAO.checkLogin(userName,pass);
    }

    public User checkUserName(String userName){
        return userDAO.checkUserName(userName);
    }
}
