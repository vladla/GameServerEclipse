/*
 * 
 */
package deadlyspace.jelastic.servint.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vladla
 */
public abstract class ServletBased extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.err.println(request.getRemoteAddr());
        ApiObject ao = null;
        try {
            StringBuilder rqXML = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line = new String();
            while ((line = br.readLine()) != null) {
                rqXML.append(line);
            }
            System.err.println("GAPI REQ--->"+request.getServletPath()+" "+rqXML.toString());
            ao = parse(rqXML.toString());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
        finally {            
            System.err.println("GAPI RESP--->"+ao.toJson());
            out.print(ao.toJson());
            out.close();
        }
    }
    
    protected abstract ApiObject parse(String string);

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
