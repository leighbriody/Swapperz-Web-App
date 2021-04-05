/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Osama Kheireddine
 */
public class UserDao extends Dao implements UserDaoInterface {
//    public static void main(String[] args) {
//        
//        UserDao uDao = new UserDao("swapperz_testdb");
//        
//       
//        
//        
//        
//    }
//     enum gender {MALE,FEMALE,OTHER};

    public UserDao(String databaseName) {
        super(databaseName);
    }

//    public static void main(String[] args) {
//        UserDao uDao = new UserDao("swapperz");
//        
//        System.out.println(uDao.getUserByUsername("osama"));
//    }
    //Method to get all products  of a category chosen
    @Override
    public ArrayList<User> getAllUsers() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList();

        try {
            con = getConnection();

            String query = "select * from users";
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {

                //String username , String passowrd , String fName , String lName , String email , String addressId , String phone , String sellerRating , String gender , String profilePicture){
                //Get all the data fields
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                String email = rs.getString("email");
                String addressId = rs.getString("addressId");
                String phone = rs.getString("phone");
                String sellerRating = rs.getString("sellerRating");
                String gender = rs.getString("gender");
                String profilePicture = rs.getString("profilePicture");

              

                //create the phone
                User u = new User(username, password, fName, lName, email, addressId, phone, sellerRating, gender, profilePicture);
                //Add it to the array list.
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getAllUsers() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllUsers() method: " + e.getMessage());
            }
        }

        return users;
    }

    /**
     * This method will be used when a user registers. It takes all of their
     * passed parameters & adds them to a new field in our db.
     *
     * @param fName
     * @param lName
     * @param email
     * @param username
     * @param password
     * @return RowsAffected
     */
    @Override
    public int register(String fName, String lName, String email, String username, String password, String phone, String gender, String profilePicture) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String query = "INSERT INTO users (id , username, password, fName, lName, email , addressId , phone , sellerRating , gender , profilePicture) VALUES (null , ? , ? , ? , ? , ? , null , ? , null , ? , ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fName);
            ps.setString(4, lName);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, gender);
            ps.setString(8, profilePicture);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception has occured in the register() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the register() method: " + e.getMessage());
                e.getMessage();
            }

        }
        return rowsAffected;
    }

    /**
     * This method will be used when validating a username from register
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM users WHERE username = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {

                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setfName(rs.getString("fName"));
                u.setlName(rs.getString("lName"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setProfilePicture(rs.getString("profilePicture"));
                u.setStatus(rs.getString("status"));

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getUserByUsername() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return u;
    }

    @Override
    public int removeUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int successs = -1;

        try {
            conn = getConnection();

            String query = "CALL removeUserByUsername(?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            successs = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getUserByUsername() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getUserByUsername() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return successs;
    }

    /**
     * This method will be used mainly to allow the user's security answers &
     * address to be added to the db
     *
     * @param username
     * @return the user's id
     */
    @Override
    public int getUserId(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            con = getConnection();

            String query = "CALL getUserId(?)";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserId() method: " + e.getMessage());
            }
        }
        return userId;
    }

    /**
     * This method will add the user's address to the users table
     *
     * @return A true/false on if the user's address was added
     */
    @Override
    public boolean addAddress(int userId, int addressId) {
        Connection conn = null;
        PreparedStatement ps = null;
        //set to false
        boolean successfulUpdate = false;
        int rowsAffected = 0;

        try {
            conn = getConnection();

            String query = "UPDATE users SET addressId = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, addressId);
            ps.setInt(2, userId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                successfulUpdate = true;
            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to edit a specific user in the addAddress() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the addAddress() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }
        // May be false (if it doesn't work)
        return successfulUpdate;
    }

    //This method will get the users address id 
    @Override
    public int getUserAddressId(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int addressId = -1;

        try {
            con = getConnection();

            String query = "CALL getUserAddressId(?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                addressId = rs.getInt("addressId");
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserAddressId() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getUserAddressId() method: " + e.getMessage());
            }
        }
        return addressId;
    }

    //get sellers username , will get the username of the seller given the seller id
    @Override
    public User getUserById(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "CALL getUserById(?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {

                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setfName(rs.getString("fName"));
                u.setlName(rs.getString("lName"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setProfilePicture(rs.getString("profilePicture"));

            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to select a specific user in the getUserById() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the resultset in the getUserById() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the getUserById() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }

        // May be null 
        // if no User matching supplied username and password is found
        return u;
    }

    public static void main(String[] args) {
        UserDao uDao = new UserDao("swapperz");

        System.out.println(uDao.updateUserDetails(3, "Yo - sama", "Kheireddine", "osamal@gmail.com", "1234567890"));
    }

    @Override
    public boolean updateUserDetails(int uId, String fName, String lName, String email, String phone) {
        Connection conn = null;
        PreparedStatement ps = null;
        //set to false
        boolean successfulUpdate = false;
        int rowsAffected = 0;

        try {
            conn = getConnection();

            String query = "UPDATE users SET fName = ?, lName = ?, email = ?, phone = ? WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, uId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                successfulUpdate = true;
            }
        } catch (SQLException ex) {
            System.out.println("A problem occurred while attempting to edit a specific user in the addAddress() method");
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("A problem occurred while attempting to close the prepared statement in the addAddress() method");
                    System.out.println("Error: " + ex.getMessage());
                }
            }
            if (conn != null) {
                freeConnection(conn);
            }
        }
        // May be false (if it doesn't work)
        return successfulUpdate;
    }
}
