package testjdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: satin
 * Date: 28.08.13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class FirtsServlet extends HttpServlet{

    public  void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><head>");
        out.println("<title>HelpPage</title></haed><body>");
        out.println("<h1>Please Submit Your Information</h1>");


    }

}
