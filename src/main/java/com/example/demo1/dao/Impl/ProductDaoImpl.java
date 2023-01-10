package com.example.demo1.dao.Impl;


import com.example.demo1.Product;
import com.example.demo1.dao.ProductDao;

import com.example.demo1.dao.db.connectionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void save(Product p) {
    }

    @Override
    public List<Product> findAll() {
        connectionDB con = new connectionDB();
        Statement st;
        List<Product> productList = new ArrayList<>();
        try{
            st = con.getConnection().createStatement();
            String query = "SELECT * FROM product;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Product p = new Product(1,rs.getString("name"),rs.getString("price"));
                productList.add(p);
            }
            con.getConnection().close();
            return productList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return productList;
        }
    }

    @Override
    public void update(Product p) {

        connectionDB con = new connectionDB();
        Statement st;
        int id = p.getId();
        String name = p.getName();
        String price = p.getPrice();
        try{
            st = con.getConnection().createStatement();
            String query = "SELECT * FROM product where id like '"+id+"';";
            ResultSet rs = st.executeQuery(query);
            if(!rs.next()) {
                String req = "INSERT into product(id,name,price) values('"+id+"','"+name+"','"+price+"');";
                st.executeUpdate(req);
            }
            else {
                String req = "UPDATE product SET price ='"+price+"' , updatedDate ='"+DateFormat.getDate()+"' where id like '"+id+"';";
                st.executeUpdate(req);
            }
            con.getConnection().close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();

        }
    }

}

