# Prueba SQA - Automatización UI y API con Serenity BDD

Proyecto de automatización funcional para la aplicación **Contact List App**, implementado con **Java**, **Maven**, **Serenity BDD**, **Cucumber**, **Screenplay**, **Selenium WebDriver** y **Rest Assured**. Serenity soporta pruebas web con Screenplay y también pruebas REST, mientras que Cucumber permite expresar los escenarios en Gherkin y ejecutarlos como pruebas automatizadas.[1][2][3]

## Alcance

La suite cubre dos capas principales:

- **UI**: autenticación, cierre de sesión y creación de contactos con Selenium + Screenplay.[2][4]
- **API**: creación de usuario, login y consulta de perfil con Serenity REST / Rest Assured.[1][3][5]

Además, el proyecto usa generación dinámica de correos para evitar colisiones de datos entre ejecuciones, una práctica común en automatización cuando los escenarios crean usuarios nuevos.[6][7]

## Stack tecnológico

| Tecnología | Uso en el proyecto |
|---|---|
| Java | Lenguaje principal de automatización. |
| Maven | Gestión de dependencias y ejecución de pruebas. |
| Serenity BDD | Orquestación, reportes y soporte Screenplay/REST.[1] |
| Cucumber | Definición de escenarios en Gherkin.[8][9] |
| Screenplay | Patrón de diseño para tareas, interacciones y manejo de estado entre pasos.[4][10] |
| Selenium WebDriver | Automatización de interfaz web.[2] |
| Rest Assured | Validación y consumo de endpoints REST en pruebas API.[3] |

## Estructura sugerida

```text
src
├── test
│   ├── java
│   │   └── com/pruebas
│   │       ├── api
│   │       │   ├── stepdefinitions
│   │       │   ├── tasks
│   │       │   └── utils
│   │       ├── runners
│   │       │   ├── api
│   │       │   └── ui
│   │       ├── ui
│   │       │   ├── stepdefinitions
│   │       │   ├── tasks
│   │       │   └── userinterfaces
│   │       └── utils
│   └── resources
│       └── features
│           ├── api
│           └── ui
```

La organización por capas y por responsabilidades es consistente con el enfoque Screenplay, donde los actores ejecutan tareas y comparten datos recordándolos y recuperándolos entre pasos.[4][10]

## Requisitos previos

Antes de ejecutar el proyecto, conviene tener instalado:

- Java 17 o superior.
- Maven 3.9 o superior.
- Google Chrome.
- Conexión a internet para descargar dependencias Maven y acceder a la aplicación bajo prueba.

## Configuración del proyecto

1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA o el IDE de preferencia.
3. Esperar a que Maven descargue todas las dependencias.
4. Verificar que el archivo `pom.xml` tenga configuradas las dependencias de Serenity, Cucumber, Selenium y Rest Assured.

## Datos dinámicos

El proyecto genera correos únicos para UI y API mediante una clase utilitaria como `EmailGenerator`, evitando que un mismo correo cause errores de negocio al repetir escenarios que registran usuarios.[6][7]

Ejemplo:

```java
package com.pruebas.utils;

public final class EmailGenerator {

    private EmailGenerator() {
    }

    public static String unique(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "@test.com";
    }
}
```

En Screenplay, el valor generado se puede guardar con `remember()` y recuperar con `recall()`, lo que permite reutilizar el mismo email dentro del escenario tanto en UI como en API.[11][10][4]

## Ejecución de pruebas

### Ejecutar toda la suite

```bash
mvn clean verify
```

Maven ejecuta la suite y Serenity genera el reporte HTML consolidado al finalizar la corrida.[1]

### Ejecutar solo UI

```bash
mvn clean test -Dtest=AutenticacionUIRunner
```

### Ejecutar solo API

```bash
mvn clean test -Dtest=AutenticacionApiRunner
```

## Reportes

Después de la ejecución, el reporte principal queda disponible en:

```text
target/site/serenity/index.html
```

Serenity genera reportes detallados de resultados, pasos ejecutados y estado final de la suite.[1]

## Escenarios cubiertos

### UI

- Login con credenciales válidas.
- Login con contraseña incorrecta.
- Logout exitoso.
- Creación de contacto válido.

### API

- Creación de usuario.
- Login exitoso.
- Consulta de perfil autenticado.
- Login fallido.

## Buenas prácticas implementadas

- Uso de Screenplay para mejorar mantenibilidad y legibilidad de la automatización.[4]
- Separación entre UI y API por paquetes y runners independientes.[1][5]
- Datos de prueba dinámicos para evitar usuarios repetidos.[6][7]
- Reutilización del estado del actor con `remember()` y `recall()`.[11][10]
- Reportería ejecutiva y técnica con Serenity.[1]

## Posibles mejoras

- Agregar CRUD completo de contactos por API.
- Integrar ejecución en pipeline CI/CD.
- Incorporar tags de Cucumber para suites por smoke, regression o api.[9][12]
- Exportar colección complementaria en Postman para documentación y validación manual rápida.[13][14]

## Autor

Proyecto desarrollado como prueba técnica de automatización QA, cubriendo validaciones de UI y API sobre Contact List App con un enfoque mantenible basado en Serenity Screenplay.[1][4]