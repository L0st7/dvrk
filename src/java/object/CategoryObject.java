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
public class CategoryObject {
    private int category_id;
    private String category_name;
    private String category_image;
    private String category_notes;

    public CategoryObject() {
    }

    public CategoryObject(int category_id, String category_name, String category_image, String category_notes) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image = category_image;
        this.category_notes = category_notes;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_notes() {
        return category_notes;
    }

    public void setCategory_notes(String category_notes) {
        this.category_notes = category_notes;
    }
    
}
