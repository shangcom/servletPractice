package hello.servletPractice.web.frontcontroller.v5;

import hello.servletPractice.web.frontcontroller.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean support(Object handler);

    ModelView handle(Object handler, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
