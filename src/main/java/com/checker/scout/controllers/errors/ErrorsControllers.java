package com.checker.scout.controllers.errors;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsControllers implements ErrorController {
    
    @RequestMapping("/error")
    public String handleError() {
        // Aquí puedes manejar otros tipos de errores si lo deseas
        return "error/error-view"; // Redirige a la página 404 personalizada
    }
}
