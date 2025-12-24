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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SimpleStatusServletTest {

    private DemoServlet servlet;

    @BeforeEach
    void setUp() {
        servlet = new DemoServlet();
    }

    @Test
    void testDoGet() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("param1", "value1");

        servlet.doGet(request, response);

        String result = response.getContentAsString();

        assertNotNull(result);
        assertTrue(result.contains("ExpectedContent")); // must match servlet output
        assertEquals(200, response.getStatus());
    }
}
