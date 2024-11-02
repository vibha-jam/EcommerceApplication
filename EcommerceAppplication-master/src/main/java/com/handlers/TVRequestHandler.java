package com.handlers;
import com.entity.tv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TVRequestHandler implements RequestHandler{
    private RequestHandler nextHandler;
    private Connection conn;

    public TVRequestHandler(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<tv> handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("TV")) {
            // Process TV request
            return getAllTV();
        } else {
            // Pass request to the next handler
            return (List<tv>) nextHandler.handleRequest(requestType);
        }
    }
    @Override
    public void setNextHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    private List<tv> getAllTV() {
        List<tv> listv = new ArrayList<tv>();

        tv v = null;

        try {
            String sql = "select * from tv";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                v = new tv();
                v.setBname(rs.getString(1));
                v.setCname(rs.getString(2));
                v.setPname(rs.getString(3));
                v.setPprice(rs.getInt(4));
                v.setPquantity(rs.getInt(5));
                v.setPimage(rs.getString(6));
                listv.add(v);

            }



        }catch (Exception e) {
            e.printStackTrace();
        }

        return listv;
    }
}
