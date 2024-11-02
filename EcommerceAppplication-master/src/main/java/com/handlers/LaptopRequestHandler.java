package com.handlers;

import com.entity.laptop;
import com.entity.tv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LaptopRequestHandler implements RequestHandler {
    private RequestHandler nextHandler;
    private Connection conn;

    public LaptopRequestHandler(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<laptop> handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("Laptop")) {
            // Process laptop request
            return getAllLaptop();
        } else {
            // Pass request to the next handler
            return (List<laptop>) nextHandler.handleRequest(requestType);
        }
    }

    @Override
    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private List<laptop> getAllLaptop() {
        List<laptop> listLaptop = new ArrayList<>();

        laptop l = null;

        try {
            String sql = "SELECT * FROM laptop";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                l = new laptop();
                l.setBname(rs.getString(1));
                l.setCname(rs.getString(2));
                l.setPname(rs.getString(3));
                l.setPprice(rs.getInt(4));
                l.setPquantity(rs.getInt(5));
                l.setPimage(rs.getString(6));
                listLaptop.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listLaptop;
    }
}
