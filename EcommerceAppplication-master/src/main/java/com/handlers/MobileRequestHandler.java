package com.handlers;

import com.entity.mobile;
import com.entity.tv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MobileRequestHandler implements RequestHandler {
    private RequestHandler nextHandler;
    private Connection conn;

    public MobileRequestHandler(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<mobile> handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("Mobile")) {
            // Process mobile request
            return getAllMobile();
        } else {
            // Pass request to the next handler
            return (List<mobile>) nextHandler.handleRequest(requestType);
        }
    }

    @Override
    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private List<mobile> getAllMobile() {
        List<mobile> listMobile = new ArrayList<mobile>();

        mobile m = null;

        try {
            String sql = "select * from mobile";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                m = new mobile();
                m.setBname(rs.getString(1));
                m.setCname(rs.getString(2));
                m.setPname(rs.getString(3));
                m.setPprice(rs.getInt(4));
                m.setPquantity(rs.getInt(5));
                m.setPimage(rs.getString(6));
                listMobile.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listMobile;
    }
}
