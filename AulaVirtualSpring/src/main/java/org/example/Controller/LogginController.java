package org.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class LogginController {
    @GetMapping("/")
    public String InicioSesion(HttpServletRequest request, Model model){
        return "Login";
    }

    @GetMapping("/Comprobar")
    public String ComprobarUsuario(HttpServletRequest request,Model model)throws SQLException {
        DataSource dataSource=null;
        Connection connection=null;
        String Email_Usuario=request.getParameter("Email_Acceso");
        String Clave_Usuario=request.getParameter("Clave_Acceso");
        connection=dataSource.getConnection();
        String Profesor="Select * from profesores where Email=? and Password=md5(?)";
        PreparedStatement preparedStatement=connection.prepareStatement(Profesor);
        preparedStatement.setString(1,Email_Usuario);
        preparedStatement.setString(2,Clave_Usuario);
        ResultSet resultSet;
        resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("Usuario Encontrado");
        }

        return "Login";
    }
}
