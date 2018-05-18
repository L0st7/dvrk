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
public class ProductObject {
    private int product_id;
    private String product_name;
    private String product_image;
    private String product_image2;
    private String product_image3;
    private String product_image4;
    private String product_image5;
    private int product_price;
    private int product_visited;
    private int product_total;
    private String product_intro;
    private String product_size;
    private int product_category_id;

    public ProductObject() {
    }

    public ProductObject(int product_id, String product_name, String product_image, String product_image2, String product_image3, String product_image4, String product_image5, int product_price, int product_visited, int product_total, String product_intro, String product_size, int product_category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_image2 = product_image2;
        this.product_image3 = product_image3;
        this.product_image4 = product_image4;
        this.product_image5 = product_image5;
        this.product_price = product_price;
        this.product_visited = product_visited;
        this.product_total = product_total;
        this.product_intro = product_intro;
        this.product_size = product_size;
        this.product_category_id = product_category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_image2() {
        return product_image2;
    }

    public void setProduct_image2(String product_image2) {
        this.product_image2 = product_image2;
    }

    public String getProduct_image3() {
        return product_image3;
    }

    public void setProduct_image3(String product_image3) {
        this.product_image3 = product_image3;
    }

    public String getProduct_image4() {
        return product_image4;
    }

    public void setProduct_image4(String product_image4) {
        this.product_image4 = product_image4;
    }

    public String getProduct_image5() {
        return product_image5;
    }

    public void setProduct_image5(String product_image5) {
        this.product_image5 = product_image5;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_visited() {
        return product_visited;
    }

    public void setProduct_visited(int product_visited) {
        this.product_visited = product_visited;
    }

    public int getProduct_total() {
        return product_total;
    }

    public void setProduct_total(int product_total) {
        this.product_total = product_total;
    }

    public String getProduct_intro() {
        return product_intro;
    }

    public void setProduct_intro(String product_intro) {
        this.product_intro = product_intro;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    @Override
    public String toString() {
        return "ProductObject{" + "product_id=" + product_id + ", product_name=" + product_name + ", product_image=" + product_image + ", product_image2=" + product_image2 + ", product_image3=" + product_image3 + ", product_image4=" + product_image4 + ", product_image5=" + product_image5 + ", product_price=" + product_price + ", product_visited=" + product_visited + ", product_total=" + product_total + ", product_intro=" + product_intro + ", product_size=" + product_size + ", product_category_id=" + product_category_id + '}';
    }

    
    
}
