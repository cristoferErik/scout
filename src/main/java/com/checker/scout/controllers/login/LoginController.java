package com.checker.scout.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.checker.scout.security.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/perform-login")
    public String login(@RequestParam String email, @RequestParam String password,HttpServletRequest request) {
        try {
            /*
            Gestore gestore=new Gestore();
            gestore.setId(2L);
            gestore.setName("Davide");
            gestore.setEmail("davide.mangione@gmail.com");
            gestore.setPassword("Mangione.Capo");
            authService.saveGestore(gestore);
             */
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication=authenticationManager.authenticate(token);
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);  // Crear una sesión si no existe
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return "redirect:/";
        } catch (AuthenticationException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        new SecurityContextLogoutHandler().logout(request, response, null);

        return "redirect:/login?logout";
    }
}
