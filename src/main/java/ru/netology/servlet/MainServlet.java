package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
  private static final String GET = "GET";
  private static final String POST ="POST";
  private static final String DELETE = "DELETE";
  private static final String API_POST_PATH ="/api/posts";
  private PostController controller;
  private AnnotationConfigApplicationContext context;

  @Override
  public void init() {
    context = new AnnotationConfigApplicationContext("ru.netology");
    controller = context.getBean(PostController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    // если деплоились в root context, то достаточно этого
    try {
      final var path = req.getRequestURI();
      final String method = req.getMethod();
      // primitive routing
      if (method.equals(GET) && path.equals(API_POST_PATH)) {
        controller.all(resp);
        return;
      }
      if (method.equals(GET) && path.matches(API_POST_PATH + "/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        controller.getById(id, resp);
        return;
      }
      if (method.equals(POST) && path.equals(API_POST_PATH)) {
        controller.save(req.getReader(), resp);
        return;
      }
      if (method.equals(DELETE) && path.matches( API_POST_PATH + "/\\d+")) {
        // easy way
        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

