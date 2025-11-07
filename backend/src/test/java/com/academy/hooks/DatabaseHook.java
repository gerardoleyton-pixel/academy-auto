package com.academy.hooks;

import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
public class DatabaseHook {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void limpiarBaseDeDatos() {
        // Limpiamos la tabla users antes de cada escenario para evitar datos residuales
        try {
            jdbcTemplate.execute("DELETE FROM users");
        } catch (Exception e) {
            // Si la tabla no existe o hay error, lo ignoramos y dejamos que el contexto procese normalmente
            System.out.println("Advertencia al limpiar la base de datos antes del escenario: " + e.getMessage());
        }
    }
}
