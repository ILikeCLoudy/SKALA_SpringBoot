package com.SKALA.LikeCloudy.Repository;

public class RealServiceImpl implements MyService {
    @Override
    public String getMessage(String name) {
        return "Hello, " + name;
    }
}