// package com.example.demo.servlet;

// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import java.io.IOException;
// import java.io.PrintWriter;

// public class SimpleStatusServlet extends HttpServlet {

//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response)
//             throws IOException {

//         response.setStatus(HttpServletResponse.SC_OK);
//         response.setContentType("text/plain");

//         PrintWriter writer = response.getWriter();
//         writer.write("Servlet Alive");
//     }
// }




package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write("Hello World!");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
