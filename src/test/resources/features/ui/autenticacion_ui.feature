Feature: Gestión de autenticación y contactos en Contact List App

  Background:
    Given que Ana abre la aplicación de Contact List

  Scenario: Login con credenciales válidas
    Given que existe un usuario registrado con contraseña "password123"
    When inicia sesión con la contraseña "password123"
    Then debería ver la lista de contactos

  Scenario: Login con contraseña incorrecta
    Given que existe un usuario registrado con contraseña "password123"
    When inicia sesión con la contraseña "claveIncorrecta"
    Then debería ver un mensaje de error de credenciales inválidas

  Scenario: Logout exitoso
    Given que existe un usuario registrado con contraseña "password123"
    And inicia sesión con la contraseña "password123"
    When cierra sesión
    Then debería ver el formulario de login

  Scenario: Crear contacto válido
    Given que existe un usuario registrado con contraseña "password123"
    And inicia sesión con la contraseña "password123"
    When crea un contacto con nombre "Luis" apellido "Lopez" fechaNacimiento "1990-01-01"
    Then debería ver el contacto "Luis Lopez" en la lista
