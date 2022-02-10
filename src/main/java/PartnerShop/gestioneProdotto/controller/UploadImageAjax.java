package PartnerShop.gestioneProdotto.controller;
import PartnerShop.gestioneProdotto.service.GestioneProdottoService;
import PartnerShop.gestioneProdotto.service.GestioneProdottoServiceImp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

/**
 * Questa classe implemmenta la Servlet che si occupa del caricamento dell'immagine di un prodotto caricato da un venditore
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 * @version 1.0
 * @author Marco De Palma
 */
@WebServlet(urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadImageAjax extends HttpServlet {

    /**
     *
     * @param request Oggetto della servlet, che contiene l'immagine del prodotto caricato e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws IOException Un'eccezione lanciata quando si verifica un errore I/O
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("file");
        request.getSession().setAttribute("img", filePart);
    }

}