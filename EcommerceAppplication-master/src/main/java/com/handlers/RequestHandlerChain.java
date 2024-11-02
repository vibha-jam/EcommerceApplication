package com.handlers;

import java.sql.Connection;
import java.util.List;

public class RequestHandlerChain {
    private RequestHandler handlerChain;

    public RequestHandlerChain(Connection conn) {
        // Create the chain of handlers
        RequestHandler tvHandler = new TVRequestHandler(conn);
        // Initialize the chain
        handlerChain = tvHandler;
//         Add other handlers as needed
         handlerChain.setNextHandler(new LaptopRequestHandler(conn));
         handlerChain.setNextHandler(new MobileRequestHandler(conn));
         handlerChain.setNextHandler(new WatchRequestHandler(conn));
    }

    // Method to handle requests
    public List<?> processRequest(String requestType) {
        // Start processing the request from the first handler in the chain
        return handlerChain.handleRequest(requestType);
    }
}
