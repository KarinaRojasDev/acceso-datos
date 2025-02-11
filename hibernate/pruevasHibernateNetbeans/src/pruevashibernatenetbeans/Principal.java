
package pruevashibernatenetbeans;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import pruebas.Departamentos;
import pruebas.Empleados;

public class Principal {

    public static void main(String[] args) {
       
        SessionFactory ses = HibernateUtil.getSessionFactory();
        Session session = ses.openSession();
        String hql="from Departamentos";
        Query query = session.createQuery(hql);
        List<Departamentos> listaDepartamentos = query.list();
        System.out.println("Número de departamentos: "+listaDepartamentos.size());
        
        System.out.println("===============================");

        for(Departamentos d: listaDepartamentos){
           System.out.println("Departamento: "+d.getDeptNo()+"\n Nombre departamento: "+d.getDnombre()+"\n Local: "+d.getLoc());
           System.out.println("===============================");  
       }
       
        System.out.println(" ");
        String hql1 = "from Empleados as e where e.departamentos.deptNo = 20";
        Query query1 = session.createQuery(hql1);
        List<Empleados> listaEmpleados = query1.list();
        for(Empleados e: listaEmpleados){
           
           System.out.println("Los empleados del departamento : "+e.getDepartamentos().getDnombre()+" "
                   +e.getApellido()+" "+e.getOficio()+" "+e.getSalario());
           System.out.println("=======================");
       }
       
        System.out.println(" ");
        String hql2="from Empleados as e, Departamentos as d where e.departamentos.deptNo = d.deptNo order by Apellido";
        Query query2 = session.createQuery(hql2);
        List<Object[]> lista = query2.list();
        for(Object[] object:lista){
           Empleados e = (Empleados) object[0];
           Departamentos d =(Departamentos)object[1];
           System.out.println("Empleados: "+e.getApellido()+" \nDepartamento: "+d.getDnombre());
           System.out.println("=========================");
       }
       
        
        System.out.println(" ");
        String hql3 ="select  new pruevashibernatenetbeans.Totales(d.deptNo, count(e.empNo), "
               + "coalesce(avg(e.salario),0), d.dnombre) "
               + "from Empleados as e right join e.departamentos as d group by d.deptNo, d.dnombre";
       
        Query query3 = session.createQuery(hql3);
        List<Totales> lista1 = query3.list();
        for(Totales t: lista1){
           System.out.println("Departamento: "+t.getNumero()+
                   "\nCuenta: "+t.getCuenta()+"\nMedia: "+t.getMedia()+"\nNombre: "+t.getNombre());
           System.out.println("=======================");
       }

        System.out.println(" ");
        String hql4 ="select avg(e.salario) from Empleados as e";
        Query query4 = session.createQuery(hql4);
        Double media = (Double) query4.uniqueResult();
        System.out.println("La media del salario de los empleados es: "+media);            
       
        
        System.out.println(" ");
        
        String hql5="from Empleados as e where  e.departamentos.deptNo = :dep and e.oficio =:ofi";
        Query query5 = session.createQuery(hql5);
        query5.setParameter("dep",(byte)30);
        query5.setParameter("ofi","VENDEDOR");
        
        List<Empleados> lista3= query5.list();
        for(Empleados e:lista3){
            System.out.println("Lista de empleados: "+e.getApellido());
            System.out.println("===========================");
        }
        System.out.println("consulta parametrizada");
        System.out.println("Empleados del departamento 30");
        String hql6 ="from Empleados as e where e.departamentos.deptNo = 30";
        Query query6 = session.createQuery(hql6);
        List<Empleados> lista4 = query6.list();
        for(Empleados e: lista4){
            System.out.println("Apellido: "+e.getApellido()+
                    "\nOficio: "+e.getOficio()+"\nSalario: "+e.getSalario());
            System.out.println("============================");
        }
        
        String hql7 ="select avg(e.salario) from Empleados as e";
        Query query7 = session.createQuery(hql7);        
        Double mediaSalario = (Double) query7.uniqueResult();
        System.out.println("La media del salario de los empleados es: "+mediaSalario);
        System.out.println(" ");
        
        
        String hql8 = "select count(e.empNo) from Empleados as e";
        Query query8 = session.createQuery(hql8);
        Long cuentaEmpleados = (Long)query8.uniqueResult();
        System.out.println("Total de empleados: "+cuentaEmpleados);       //IMPRIME BIEN
        System.out.println(" ");
        
        String hql9="select d.dnombre, count(e.empNo) as numEmpleados "
                + "from Departamentos d join d.empleadoses e group by d.deptNo, d.dnombre order by numEmpleados desc";
        Query query9 = session.createQuery(hql9);
     
        List<Object[]> lista5= query9.list();
        for(Object[] object: lista5){
            String nombreDepartamento = (String)object[0];
            Long cuenta = (Long)object[1];
            System.out.println("Departamentos: "+nombreDepartamento+"\nCuenta: "+cuenta);
            System.out.println("==================================");
        }
        
        String hql10 = "from Empleados where empNo = :numEmpleados";
        Query query10 = session.createQuery(hql10);
        query10.setParameter("numEmpleados",(short)4525);
        Empleados e = (Empleados)query10.uniqueResult();
        System.out.println("Empleados: "+e.getApellido()+" "+e.getOficio()+" "+e.getComision());
        System.out.println("===================================");
       
        String hql11 ="select new pruevashibernatenetbeans.Totales(d.deptNo, count(e.empNo),"
                + "coalesce(avg(e.salario),0), d.dnombre)"
                + "from Empleados as e right join e.departamentos as d group by d.deptNo, d.dnombre";
        Query query11 = session.createQuery(hql11);
        List<Totales> lista11 = query11.list();
        for(Totales t: lista11){
           System.out.println("Departamento: "+t.getNumero()+"\nCuenta: "
                   +t.getCuenta()+"\nSalarioMedia: "+t.getMedia()+"\nNombre: "+t.getNombre());
            System.out.println("=================================");
        }
        
        String hql12 = "from Libro as l, Autor as a where l.autor.idAutor= a.idAutor";
        Query query12 = session.createQuery(hql12);
        List<Object[]> lista12 = query12.list();
        for(Object[] object: lista12){
            //Libro l = (Libro)object[0];
            //Autor a = (Autor)object[1];
            System.out.println(" ");
            
            
        }
        
        
       ses.close();
       session.close();
       
       
    }  
}
