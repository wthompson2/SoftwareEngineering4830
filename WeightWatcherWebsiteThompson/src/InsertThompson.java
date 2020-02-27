
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertThompson")
public class InsertThompson extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertThompson() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String weight = request.getParameter("weight");
      String date = request.getParameter("date");

      Connection connection = null;
      String insertSql = " INSERT INTO myTableWeight (id, NAME, WEIGHT, DATE) values (default, ?, ?, ?)";

      try {
         DBConnectionThompson.getDBConnection(getServletContext());
         connection = DBConnectionThompson.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, name);
         preparedStmt.setString(2, weight);
         preparedStmt.setString(3, date);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name</b>: " + name + "\n" + //
            "  <li><b>Weight (lbs)</b>: " + weight + "\n" + //
            "  <li><b>Date</b>: " + date + "\n\n" + //
            "  <p>Keep working hard! Don't forget about your goal!</p>\n" + 

            "</ul>\n");

      out.println("<a href=/WeightWatcherWebsiteThompson/WebContent/insert_thompson.html>Insert Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
