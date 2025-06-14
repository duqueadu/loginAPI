##  Flujo de Autenticación

                            ###  Endpoints Públicos:
                            - POST /api/authorization/CrearUsuario
                            - POST /api/authorization/login
                            
                            ###  Endpoints Protegidos:
                            - Todos los demás endpoints bajo /api/**
                            
                            ### Instrucciones:

                            USUARIOS DE PRUEBA 
                                {usuario = prueba2@prueba.com,clve = 12345678}
                                {usuario = prueba1@prueba.com,clave =12345678}

                                

                            1. Registrar usuario en /api/authorization/CrearUsuario
                            Ejemplo 
                            {
                                nombre: Juan Pérez,
                                email: juan.perez@example.com,
                                contrasena: segura123,
                                rol: 1,
                                fechaCreacion: 2025-06-14T10:00:00.000Z
                             }
                            2. Iniciar sesión en /api/authorization/login
                            Ejemplo 
                            {
                                email: juan.perez@example.com,
                                contrasena: segura123
                             }
                          
                            3. Introducir el token JWT recibido en el botón Authorize
                            4. Acceder a los endpoints protegidos
                            
                            ###  Validación de Sesión:
                            Todos los endpoints protegidos validan:
                            - Token JWT válido en el header Authorization
                            - Sesión activa con token almacenado
                            """