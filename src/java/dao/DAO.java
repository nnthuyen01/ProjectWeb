/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Item;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trinh
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List <Product> list = new ArrayList<Product>(); 
        String query = "select * from product";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getInt(5),
                rs.getDouble(6),
                rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public Product getProductByID(String id) {
        String query = "select * from product\n"
                + "where ProductID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                rs.getString(2),
                rs.getInt(5),
                rs.getDouble(6),
                rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }

   
    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<Product>();
        String query = "select * from product\n"
                + "where CategoryID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getInt(5),
                rs.getDouble(6),
                rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
        public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where ProductName like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,"%"+ txtSearch+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                rs.getString(2),
                rs.getInt(5),
                rs.getDouble(6),
                rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<Category>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select * from product\n"
                + "order by ProductId desc limit 1";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                rs.getString(2),
                rs.getInt(5),
                rs.getDouble(6),
                rs.getString(7));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Account login(String user, String pass){
        String query = "select * from account where Username = ? and Password = ?";
        try{
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2,pass);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
            }
        }catch (Exception e){
        }
        return null;
    }
    
    public Account checkAccountExist(String user){
        String query = "select * from account where Username = ?";
        try{
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
            }
        }catch (Exception e){
        }
        return null;
    }
    
    public void signup(String user,String pass, String email, String phone){
        String query="insert into account(Username, Password,Email , Phone) values\n" 
                +"(?,?,?,?)";
        try{
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,user);
            ps.setString(2,pass);
            ps.setString(3,email);
            ps.setString(4,phone);
            ps.executeUpdate();
        }catch(Exception e){
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product a = dao.getLast();
        //System.out.println(a);
        for (Product o : list) {
            System.out.println(o);
        }
    }

}
