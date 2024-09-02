package hello.servletPractice.web.frontcontroller.v3;

import hello.servletPractice.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
