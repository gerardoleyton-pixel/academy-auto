Feature: Login de usuario

  Scenario: Usuario inicia sesión correctamente
    Given el usuario tiene credenciales válidas
    When intenta iniciar sesión
    Then recibe un token JWT
