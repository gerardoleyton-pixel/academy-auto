Feature: Matricula de cursos

  Scenario: Estudiante se matricula en un curso
    Given existe un curso disponible
    And existe un usuario registrado
    When el usuario solicita matricularse en el curso
    Then la matricula se realiza con éxito
