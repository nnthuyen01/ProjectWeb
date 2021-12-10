/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Item;
import entity.Product;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jni.Socket;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddCart", urlPatterns = {"/add"})
public class AddCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int soluong =1;
        int soluong1 =1;
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        Account a = new Account();
        HttpSession session = request.getSession();
        String id = request.getParameter("pid");
        Product product = dao.getProductByID(id);
        a=(Account) session.getAttribute("acc");
        if(session.getAttribute("item")==null)
        {
         Item item = new Item();
         List<Product> listProduct= new ArrayList<Product>();
         
         item.setSoluong(soluong);
         listProduct.add(product);
         item.setListProduct(listProduct);
         session.setAttribute("item", item);    
        }
        else{
            Item item=(Item) session.getAttribute("item");
            List<Product> list = item.getListProduct();
            boolean check = false;
            for(Product product1: list)
            {
                if(product1.getId()==product.getId())
                {
                    item.setSoluong(item.getSoluong()+soluong);
                    check=true;
                }
            }
            
            if(check==false)
            {
                Product product2= dao.getProductByID(id);
                list.add(product2);
                item.setSoluong(soluong);
            }
            session.setAttribute("item", item);
        }
        response.sendRedirect("cart");
        
        
        
        
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
