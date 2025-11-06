Feature: Gestión de usuarios por API

  Scenario: Crear un usuario
    Given que quiero crear un usuario con nombre "juan" y rol "STUDENT"
    When envío la petición de creación
    Then recibo código 201
