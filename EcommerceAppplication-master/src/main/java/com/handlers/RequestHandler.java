package com.handlers;

import java.util.List;


public interface RequestHandler {
    List<?> handleRequest(String requestType);
    void setNextHandler(RequestHandler nextHandler);
}
