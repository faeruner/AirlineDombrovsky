package by.pvt.module3.command;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ActionCommand {
    String execute(Map<String, String> paramMap, Model model, HttpSession httpSession);
}