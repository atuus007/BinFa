package hu.ati.binfaupdate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/binfaupdate2")
@MultipartConfig
public class BinfaUpload extends HttpServlet {
    /**/
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        response.setContentType("text/html;charset=UTF-8");
        
        //byte tomb[] =new byte[1];
        //POST kéréshez kell
        String b=null;
        PrintWriter ki = response.getWriter();
        //Part myPart = request.getPart("bemenet");
        BinFa binfa=new BinFa();
        String bemenet = request.getParameter("bemenet");
        ki.println("bemenet: "+bemenet+"</br>");
        char []t=bemenet.toCharArray();
        //majd feldolgozom a bemenetet
        for(char be:t) {
            for(int i=0; i<8; ++i){
                if((be & 0x80) != 0){
                    binfa.addBit('1');
                }else {
                    binfa.addBit('0');
                }
                be<<=1;
            }
        }
        //és kiiratom
        ki.println(binfa.kiir());
        ki.println("<br>depth: "+binfa.getMelyseg());
        ki.println("<br>mean: "+binfa.getAtlag());
        ki.println("<br>var: "+binfa.getSzoras());
        
        //ki.println("dsadfsadfasfdasfdasdfas");
        
    }
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
