package com.example.ecommerce;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

// https://www.javatpoint.com/how-to-encrypt-password-in-java

public class Login {

    // using hashing to change input by sha-256 algorithm
    public static byte[] getSHA(String input) {
        try {
            /* MessageDigest instance for hashing using SHA256 */
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            /* digest() method called to calculate message digest of a input and return array of byte */
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String toHexString (String password) throws NoSuchAlgorithmException {
        try {/* Convert byte array of password into digest */
            BigInteger number = new BigInteger(1, getSHA(password));
            /* Convert the digest into hex value */
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Customer customerLogin(String userEmail, String password) throws NoSuchAlgorithmException {
        // select * from customers where email = 's.arhamanis@gmail.com' and password = 'arham234';
        String encryptedPassword = toHexString(password);
        //System.out.println("This is the encrypted password --> " + encryptedPassword);
        String query = "select * from customers where email = '" + userEmail +"' and password = '" + encryptedPassword +"'";
        DataBaseConnection dbConn = new DataBaseConnection();
        try {
            ResultSet rs = dbConn.getQueryTable(query);
            if (rs != null && rs.next()) {
                return new Customer(rs.getInt("cid"), rs.getString("name"), rs.getString("email"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        //System.out.println(customerLogin("saimkhan@gmail.com", "yusuf"));
        //boolean isValid = customerLogin("saimkhan@gmail.com", "yusuf");
        //if (isValid) System.out.println("this user is in our record");
    }
}

