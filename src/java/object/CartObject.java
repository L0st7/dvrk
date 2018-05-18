/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class CartObject {
    private ArrayList<AddProductObject> items;
    public CartObject(){
        items = new ArrayList<AddProductObject>();
    }
    public void addCartProduct(AddProductObject product) {
        if (items.contains(product)) {
            AddProductObject apo = items.get(items.indexOf(product));
            apo.setProduct_count(apo.getProduct_count()+product.getProduct_count());
        }else{
            items.add(product);
        }
    }
    
    public AddProductObject getProduct(int i){
        if(i<0||i>items.size()-1){
            return null;
        }
        return items.get(i);
    }
    public ArrayList<AddProductObject> getCart(){
        return items;
    }
    
    public boolean delProduct(String productName){
        AddProductObject apo = new AddProductObject(productName, 0, 0, "", "");
        if(!items.contains(apo)){
            return false;
        }
        items.remove(apo);
        return true;
    }
    
    public int getCountProduct(){
        return items.size();
    }
    public int getCount(){
        int count =0;
        for(AddProductObject apo: items){
            count += apo.getProduct_count();
        }
        return count;
    }
    public double priceTotal(){
        double price =0;
        for(AddProductObject apo : items){
            price += apo.getProduct_price()*apo.getProduct_count();
        }
        return price;
    }
}
