Feature: Registro de usuario

  Scenario: Usuario se registra exitosamente
    Given el usuario accede al formulario de registro
    When completa sus datos y envía el formulario
    Then el sistema confirma el registro exitoso
