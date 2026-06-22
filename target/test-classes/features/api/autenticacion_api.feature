Feature: Autenticación de usuarios por API en Contact List App

  Scenario: Crear usuario exitosamente
    When crea un usuario por API con nombre "Pedro" apellido "Lopez" y contraseña "password123"
    Then la respuesta API debería ser 201

  Scenario: Login exitoso
    Given existe un usuario API con nombre "Laura" apellido "Gomez" y contraseña "password123"
    When inicia sesión por API con la contraseña "password123"
    Then la respuesta API debería ser 200
    And debería recibir un token de autenticación

  Scenario: Obtener perfil del usuario autenticado
    Given existe un usuario API con nombre "Mario" apellido "Ruiz" y contraseña "password123"
    And inicia sesión por API con la contraseña "password123"
    When consulta su perfil por API
    Then la respuesta API debería ser 200
    And el email del perfil debería ser el generado

  Scenario: Login fallido con contraseña incorrecta
    Given existe un usuario API con nombre "Sofia" apellido "Diaz" y contraseña "password123"
    When inicia sesión por API con la contraseña "claveIncorrecta"
    Then la respuesta API debería ser 401
    