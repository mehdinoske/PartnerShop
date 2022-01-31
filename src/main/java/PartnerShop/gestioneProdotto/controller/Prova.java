package PartnerShop.gestioneProdotto.controller;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;
import PartnerShop.model.entity.Prodotto;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Prova extends HttpServlet {

    private final GestioneProdottoService PrDAO = new GestioneProdottoServiceImp();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Receive file uploaded to the Servlet from the HTML5 form */
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        ArrayList<Prodotto> prodotti = PrDAO.getAllProdotti();
        getServletContext().setAttribute("prodotti",prodotti);
        Prodotto p = prodotti.get(prodotti.size() - 1);
        int i = p.getId();
        for (Part part : request.getParts()) {
            part.write("C:\\Users\\depal\\Desktop\\img\\" + i +".jpg");
            Logger.getLogger("PORCO").log(Level.SEVERE, Integer.toString(i));
        }
        //response.getWriter().print("The file uploaded sucessfully.");
    }

}