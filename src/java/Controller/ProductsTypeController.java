/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.ProductTypeDAO;

/**
 *
 * @author phanh
 */
public class ProductsTypeController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        try ( PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("utf-8");
            String mode = request.getParameter("mode");
            String destinate = "/Admin/ProductTypeManager";

            if (mode.equals("disable")) {
                int id = Integer.parseInt(request.getParameter("id"));
                ProductTypeDAO.disableProductType(id);
            }

            if (mode.equals("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                destinate = "/Admin/ProductTypeDetail?id=" + id;
                response.sendRedirect(request.getContextPath() + destinate);
                return;
            }

            if (mode.equals("do-update")) {
                String name = request.getParameter("type-name");
                String des = request.getParameter("type-des");
                int id = Integer.parseInt(request.getParameter("id"));
                ProductTypeDAO.updateProductType(id, des, name);
            }

            if (mode.equals("add")) {
                String name = request.getParameter("name");
                String des = request.getParameter("des");
                int status = Integer.parseInt(request.getParameter("status"));
                ProductTypeDAO.addProductType(name, des, status);
            }
            response.sendRedirect(request.getContextPath() + destinate);
//            RequestDispatcher reqDispatch = request.getRequestDispatcher(destinate);
//            reqDispatch.forward(request, response);
        }
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
