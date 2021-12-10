/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;





/**
 *
 * @author admin
 */
public class Item {

    public Item(){
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getListProduct() {
        return list;
    }

    public void setListProduct(List<Product> list) {
        this.list = list;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
    private int id;
    private List<Product> list;
    private int soluong;
    
    
}
