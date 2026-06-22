Feature: Autenticación de usuarios por API en Contact List App
  Como consumidor de la API de Contact List
  Quiero registrar usuarios e iniciar sesión
  Para validar la autenticación y acceso al perfil

  Scenario: Crear usuario exitosamente
    When crea un usuario por API con nombre "Pedro" apellido "Lopez" email base "pedro1@test.com" y contraseña "password123"
    Then la respuesta API debería ser 201

  Scenario: Login exitoso
    Given existe un usuario API con nombre "Laura" apellido "Gomez" email "laura_api_ok1@test.com" y contraseña "password123"
    When inicia sesión por API con email "laura_api_ok1@test.com" y contraseña "password123"
    Then la respuesta API debería ser 200
    And debería recibir un token de autenticación

  Scenario: Obtener perfil del usuario autenticado
    Given existe un usuario API con nombre "Mario" apellido "Ruiz" email "mario_api_me1@test.com" y contraseña "password123"
    And inicia sesión por API con email "mario_api_me1@test.com" y contraseña "password123"
    When consulta su perfil por API
    Then la respuesta API debería ser 200
    And el email del perfil debería ser "mario_api_me1@test.com"

  Scenario: Login fallido con contraseña incorrecta
    Given existe un usuario API con nombre "Sofia" apellido "Diaz" email "sofia_api_fail1@test.com" y contraseña "password123"
    When inicia sesión por API con email "sofia_api_fail1@test.com" y contraseña "claveIncorrecta"
    Then la respuesta API debería ser 401