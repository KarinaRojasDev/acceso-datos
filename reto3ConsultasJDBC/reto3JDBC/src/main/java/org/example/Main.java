package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static final String URL ="jdbc:mysql://localhost:3306/biblioteca";
    public static void main(String[] args)  {

        Scanner snc = new Scanner(System.in);
        String user = "user-biblioteca";
        String pass = "biblioteca12345";
        try(Connection con= DriverManager.getConnection(URL,user,pass);
            Statement sentencia = con.createStatement();){

            System.out.println("Conexión correctamente");

            String isbn;
            String tituloEditorial;
            int numeroEjemplares;
            String nombreAutor;
            String nombreEditorial;
            String nombreTema;

            System.out.println();

            String consultaTotalLibros = "SELECT COUNT(*) FROM libro";
            try(ResultSet librosRegistrados = sentencia.executeQuery(consultaTotalLibros)){
                if(librosRegistrados.next()){
                    System.out.println("Libros registrados: "+librosRegistrados.getInt(1));
                }
            }

            System.out.println("Inserte el ISBN del libro");
            isbn = snc.nextLine();

            String selectLibro = "SELECT * FROM libro WHERE isbn= ?";
            try(PreparedStatement preparedStatement = con.prepareStatement(selectLibro)){
                preparedStatement.setString(1,isbn);
                try(ResultSet sentenciaSelectLibro = preparedStatement.executeQuery()){

                    if(sentenciaSelectLibro.next()){
                        System.out.println("El libro con "+isbn+" ya existe");
                        System.out.println(" ");

                    }else {
                        System.out.println("Titulo");
                        tituloEditorial = snc.nextLine();

                        System.out.println("Numeros de ejemplares");
                        numeroEjemplares = snc.nextInt();
                        snc.nextLine();

                        System.out.println("El nombre del autor ");
                        nombreAutor = snc.nextLine();

                        System.out.println("Nombre editorial");
                        nombreEditorial = snc.nextLine();

                        System.out.println("Nombre del tema");
                        nombreTema = snc.nextLine();


                        //AUTOR
                        if(!existeAutor(nombreAutor,con)){
                            altaAutor(nombreAutor,con);
                        }
                        int autor = buscarIdAutor(nombreAutor,con);

                        //EDITORIAL
                        if(!existeEditorial(nombreEditorial,con)){
                            System.out.println("Ingrese la dirección de la editorial: ");
                            String direccion = snc.nextLine();
                            System.out.println("Ingrese la teléfono de la editorial:");
                            String telefono = snc.nextLine();
                            System.out.println(" ");
                            altaEditorial(nombreEditorial,direccion,telefono,con);
                            System.out.println("Editorial dada de alta correctamente mediante procedimiento almacenado");
                        }
                        int editorial = buscarIdEditorial(nombreEditorial,con);

                        //TEMA
                        if(!exiteTema(nombreTema,con)){
                            altaTema(nombreTema,con);
                        }
                        int tema = buscarIdTema(nombreTema,con);

                        //INSERTAMOS DATOS

                        String insertLibro = "INSERT INTO libro(ISBN, Titulo, NumeroEjemplares, idAutor, idEditorial, idTema) " +
                                "VALUES (?,?,?,?,?,?)";
                        try(PreparedStatement preparedStatement1 = con.prepareStatement(insertLibro)){
                            preparedStatement1.setString(1,isbn);
                            preparedStatement1.setString(2,tituloEditorial);
                            preparedStatement1.setInt(3,numeroEjemplares);
                            preparedStatement1.setInt(4,autor);
                            preparedStatement1.setInt(5,editorial);
                            preparedStatement1.setInt(6,tema);

                            preparedStatement1.executeUpdate();
                            System.out.println("Libro insertado correctamente");
                        }
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Error en la conexión "+e.getMessage());
            e.printStackTrace();
        }

    }

    private static boolean existeAutor(String nombreAutor, Connection con) throws SQLException {
        String selectAutor ="SELECT * FROM autor WHERE NombreAutor = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(selectAutor)){
            preparedStatement.setString(1, nombreAutor);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }
    }
    private static int buscarIdAutor(String nombreAutor, Connection con) throws SQLException {

        String selectBuscar = "SELECT  idAutor FROM autor WHERE  NombreAutor = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(selectBuscar)){
            preparedStatement.setString(1,nombreAutor);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("idAutor");
                }
            }
        }
        System.out.println("No se encontró el autor");
        return -1;
    }
    private static void altaAutor(String nombreAutor, Connection con) throws SQLException {

        try(CallableStatement callableStatement = con.prepareCall("{CALL altaAutor(?)}")){
            callableStatement.setString(1,nombreAutor);
            callableStatement.execute();
            System.out.println("Autor dado de alta correctamente usando procedimiento almacenado.");
        }
    }
    private static boolean existeEditorial (String nombreEditorial , Connection con) throws SQLException {
        String selectEditorial = "SELECT * FROM editorial WHERE  NombreEditorial = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(selectEditorial)){
            preparedStatement.setString(1,nombreEditorial);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }
    }
    private static int buscarIdEditorial(String nombreEditorial,Connection con) throws SQLException {

        String selectEditorial = "SELECT idEditorial FROM editorial WHERE NombreEditorial = ?";


        try(PreparedStatement preparedStatement = con.prepareStatement(selectEditorial)){
            preparedStatement.setString(1,nombreEditorial);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        }
        System.out.println("Error al buscar editorial");
        return -1;
    }
    private static void altaEditorial(String nombreEditorial,String direccion,String telefono,Connection connection) throws SQLException {

        try(CallableStatement callableStatement = connection.prepareCall("{CALL altaEditorial(?,?,?)}")){
            callableStatement.setString(1,nombreEditorial);
            callableStatement.setString(2,direccion);
            callableStatement.setString(3,telefono);
            callableStatement.execute();
            System.out.println("Editorial dada de alta correctamente usando procedimiento almacenado.");
        }
    }
    private static boolean exiteTema(String tema, Connection con) throws SQLException {

        String selectTema = "SELECT * FROM tema WHERE NombreTema = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(selectTema)){
            preparedStatement.setString(1,tema);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }
    }
    private static int buscarIdTema(String tema,Connection con) throws SQLException {

        String selectIdTema = "SELECT idTema FROM tema WHERE  NombreTema = ?";

        try(PreparedStatement preparedStatement = con.prepareStatement(selectIdTema)){
            preparedStatement.setString(1,tema);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return  resultSet.getInt("idTema");
                }
            }
        }
        System.out.println("Error al buscar tema");
        return -1;
    }
    private static void altaTema(String tema, Connection con) throws SQLException {

        try(CallableStatement callableStatement = con.prepareCall("{CALL altaTema(?)}")){
            callableStatement.setString(1,tema);
            callableStatement.execute();
            System.out.println("Autor dado de alta correctamente usando procedimiento almacenado.");
        }
    }
}


















