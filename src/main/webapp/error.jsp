<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error de Conexión</title>
    <link rel="icon" type="image/png" href="img/im-iconoTeatro.png">
    <link rel="stylesheet" href="css/styles2.css">
   <style>
        body {
            background-color: #f8d9da; /* fondo suave rojo claro */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0 20px;
            color: #721c24; /* rojo oscuro */
        }

        .error-container {
            background-color: #f5c6cb; /* rojo más suave */
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(114, 28, 36, 0.4);
            max-width: 500px;
            text-align: center;
        }

        h2 {
            margin-top: 0;
            font-size: 2rem;
            font-weight: bold;
        }

        p {
            font-size: 1.2rem;
            margin: 15px 0 25px 0;
        }

        a {
            display: inline-block;
            background-color: #721c24;
            color: #fff;
            text-decoration: none;
            padding: 12px 25px;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #5a151b;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>No se pudo establecer conexión con la base de datos.</h2>
        <p>Por favor, verifique que el servidor XAMPP esté encendido.</p>
        <a href="index.jsp">Volver al inicio</a>
    </div>
</body>
</html>
