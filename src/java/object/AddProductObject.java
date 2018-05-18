/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author nguye
 */
public class AddProductObject {
    private String product_name;
    private int product_price;
    private int product_count;
    private String product_img;
    private String product_size;

    public AddProductObject() {
    }

    public AddProductObject(String product_name, int product_price, int product_count, String product_img, String product_size) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_count = product_count;
        this.product_img = product_img;
        this.product_size = product_size;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    @Override
    public String toString() {
        return "AddProductObject{" + "product_name=" + product_name + ", product_price=" + product_price + ", product_count=" + product_count + ", product_img=" + product_img + ", product_size=" + product_size + '}';
    }
    
}
