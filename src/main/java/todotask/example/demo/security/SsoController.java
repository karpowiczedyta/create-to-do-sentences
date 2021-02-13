package todotask.example.demo.security;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/logout")
public class SsoController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index";
    }


}
