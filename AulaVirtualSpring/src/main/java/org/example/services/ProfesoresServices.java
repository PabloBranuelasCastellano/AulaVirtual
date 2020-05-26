package org.example.services;

import org.example.Entities.Grupos;
import org.example.Entities.GruposAlumno;
import org.example.Entities.Profesores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class ProfesoresServices {
    @Autowired
    LoginServices loginServices;
    Profesores profesores;
    Grupos grupos;
    GruposAlumno gruposAlumno;
    DataSource dataSource;
    Connection connection;
}
