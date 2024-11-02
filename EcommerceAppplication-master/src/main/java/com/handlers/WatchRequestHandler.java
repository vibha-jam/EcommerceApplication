package com.handlers;

import com.entity.tv;
import com.entity.watch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WatchRequestHandler implements RequestHandler {
    private Connection conn;
    private RequestHandler nextHandler;

    public WatchRequestHandler(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<watch> handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("Watch")) {
            // Process watch request
            return getAllWatches();
        } else {
            // Pass request to the next handler
            return (List<watch>) nextHandler.handleRequest(requestType);
        }
    }
    @Override
    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    private List<watch> getAllWatches() {
        List<watch> listWatches = new ArrayList<>();

        watch w = null;

        try {
            String sql = "SELECT * FROM watch";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                w = new watch();
                w.setBname(rs.getString(1));
                w.setCname(rs.getString(2));
                w.setPname(rs.getString(3));
                w.setPprice(rs.getInt(4));
                w.setPquantity(rs.getInt(5));
                w.setPimage(rs.getString(6));
                listWatches.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listWatches;
    }
}
