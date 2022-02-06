package PartnerShop.Exceptions;

import PartnerShop.model.entity.UtenteRegistrato;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MyServletException extends ServletException  {

    public MyServletException() {
        super();
    }

    public MyServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public MyServletException(String message) {
        super(message);
    }

    public MyServletException(Throwable rootCause) {
        super(rootCause);
    }

}
