This project has a `Makefile` to run the project.

```bash
make compile # generates a jar-with-dependencies inside the target folder
make test
make format
```

## Arquitectura

1. Servidor:
    - Maneja conexiones entrantes usando WebSockets o TCP.
    - Autentica usuarios (con JWT).
    - Procesa mensajes y los distribuye a los destinatarios correctos (usuarios o grupos).
    - Persiste datos (mensajes, usuarios, grupos).
2. Base de Datos:
    - Usuarios:
      id, nombre, contraseña, estado (en línea o fuera).
    - Grupos:
      id, nombre, miembros.
    - Mensajes:
      id, remitente, receptor (usuario/grupo), contenido, timestamp.
3. Cliente:
    - Usa WebSockets (browser) para enviar y recibir mensajes en tiempo real.
    - Cli que soporta comandos como iniciar sesión, enviar mensajes, crear grupos, etc.

```txt
    +-------------------+
    |   Connection Hub  |
    +-------------------+
    |  WebSocket Module |
    |     TCP Module    |
    |                   |
    | (Netty Framework) |
    +-------------------+
            |
    +------------------+
    | Shared Services  |
    +------------------+
    | - Authentication |
    | - Message Logic  |
    | - Group Logic    |
    +------------------+
             |
  +-----------------------+
  | Database (PostgreSQL) |
  +-----------------------+
```
