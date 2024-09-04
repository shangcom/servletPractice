package hello.servletPractice.web.frontcontroller.v5;

import hello.servletPractice.web.frontcontroller.ModelView;
import hello.servletPractice.web.frontcontroller.MyView;
import hello.servletPractice.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servletPractice.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servletPractice.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servletPractice.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servletPractice.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servletPractice.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servletPractice.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servletPractice.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlearMappingMap();
        initHandlerAdapters();
    }

    private void initHandlearMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(handler, request, response);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        Map<String, Object> model = mv.getModel();

        view.render(model, request, response);


    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("no adapter for this handler: " + handler);
    }
}
