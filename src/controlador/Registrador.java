
package controlador;

import BaseDatos.conexion;
import java.sql.ResultSet;
import modelo.Cliente;

public class Registrador {

    Cliente cliente;
    
    public void registrarCliente(Cliente p) throws Exception {
        System.out.println("Empieza operación registrar");  
        conexion.conectar();
        String sql = "INSERT INTO cliente(dni, apellidos, nombres,estado) "
                + "VALUES('" + p.getDni() + "',"
                + "'" + p.getApellidos() + "',"
                + "'" + p.getNombres() + "',1)";
        System.out.println("Intentando registrar entidad de tipo "+p.getClass().getName());
        try {
            
            conexion.ejecutar(sql);
            System.out.println("Cliente registrado");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                conexion.desconectar();
            } catch (Exception e) {
            }
        }
        System.out.println("Finaliza operación registrar");
    }
    
    public Cliente buscarCliente(int dni) throws Exception {
        System.out.println("Empieza operacion actualizar");
        String sql="select * from cliente where dni="+dni+";";
        System.out.println("Intentando buscar entidad cliente");
        try{
            conexion.conectar();
            ResultSet dato = conexion.consultar(sql);
            while(dato.next()){
                cliente=new Cliente(dato.getString("dni"),
                        dato.getString("apellidos"),
                        dato.getString("nombres"));
            }
            System.out.println("Cliente Encontrado");
            return cliente;
        } catch(Exception e){
            throw e;
        } finally {
            conexion.desconectar();
        }
    }
}
