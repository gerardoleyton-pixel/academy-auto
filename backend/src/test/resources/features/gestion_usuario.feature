Feature: Gestión de usuarios por API

  Scenario: Flujo CRUD de usuario (crear, actualizar, eliminar)
    Given que quiero crear un usuario con username "juan" password "pass123" y role "STUDENT"
    When envío la petición de creación
    Then recibo código 201
    And guardo el id de la última respuesta

    When envío la petición de actualización con username "juan-upd" password "newpass" y role "STUDENT"
    Then recibo código 200

    When envío la petición de eliminación
    Then recibo código 204
