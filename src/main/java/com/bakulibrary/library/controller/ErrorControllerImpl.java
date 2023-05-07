package com.bakulibrary.library.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControllerImpl implements ErrorController {

    @GetMapping("/error")
    public String showErrorPage(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String url = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("status", statusCode);
        }
        model.addAttribute("url", url);

        return "error";
    }

}
