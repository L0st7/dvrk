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
public class FeedbackObject {
    private int feedback_id;
    private String feedback_title;
    private String feedback_content;
    private String feedback_fullname;
    private String feedback_email;
    private int feedback_product_id;

    public FeedbackObject() {
    }

    public FeedbackObject(int feedback_id, String feedback_title, String feedback_content, String feedback_fullname, String feedback_email, int feedback_product_id) {
        this.feedback_id = feedback_id;
        this.feedback_title = feedback_title;
        this.feedback_content = feedback_content;
        this.feedback_fullname = feedback_fullname;
        this.feedback_email = feedback_email;
        this.feedback_product_id = feedback_product_id;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_title() {
        return feedback_title;
    }

    public void setFeedback_title(String feedback_title) {
        this.feedback_title = feedback_title;
    }

    public String getFeedback_content() {
        return feedback_content;
    }

    public void setFeedback_content(String feedback_content) {
        this.feedback_content = feedback_content;
    }

    public String getFeedback_fullname() {
        return feedback_fullname;
    }

    public void setFeedback_fullname(String feedback_fullname) {
        this.feedback_fullname = feedback_fullname;
    }

    public String getFeedback_email() {
        return feedback_email;
    }

    public void setFeedback_email(String feedback_email) {
        this.feedback_email = feedback_email;
    }

    public int getFeedback_product_id() {
        return feedback_product_id;
    }

    public void setFeedback_product_id(int feedback_product_id) {
        this.feedback_product_id = feedback_product_id;
    }
    
}
