/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */
public class RelationFollow {
    private int idFollower;
    private int idFollowed;

    public RelationFollow() {
    }
    
    

    public RelationFollow(int idFollower, int idFollowed) {
        this.idFollower = idFollower;
        this.idFollowed = idFollowed;
    }

    public int getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(int idFollower) {
        this.idFollower = idFollower;
    }

    public int getIdFollowed() {
        return idFollowed;
    }

    public void setIdFollowed(int idFollowed) {
        this.idFollowed = idFollowed;
    }

    @Override
    public String toString() {
        return "RelationFollow{" + "idFollower=" + idFollower + ", idFollowed=" + idFollowed + '}';
    }
    
}