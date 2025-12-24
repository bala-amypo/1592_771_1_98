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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class SimpleStatusServlet {

    private DemoServlet servlet;

    @BeforeEach
    void setUp() {
        servlet = new DemoServlet();
    }

    @Test
    void testDoGet() throws Exception {
        // Create mock request and response
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Optional: set request parameters
        request.addParameter("param1", "value1");

        // Capture servlet output
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().write(""); // initialize writer
        response.setContentLength(0);
        
        // Call servlet method
        servlet.doGet(request, response);

        // Get output
        String result = response.getContentAsString();

        // Assertions
        assertNotNull(result);
        assertTrue(result.contains("ExpectedContent")); // replace with expected output
    }
}
