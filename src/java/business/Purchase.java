/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Osama Kheireddine
 */
public class Purchase {
    private int id;
    private int userId;
    private int productId;
    private String isApproved;

    public Purchase(int id, int userId, int productId, String isApproved) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.isApproved = isApproved;
    }

    public Purchase() {
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    
}
