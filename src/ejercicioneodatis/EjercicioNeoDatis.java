/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioneodatis;

import org.neodatis.odb.ODB;

/**
 *
 * @author oracle
 */
public class EjercicioNeoDatis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ODB odb=Metodos.conectar();
        //Metodos.amosar_deportes(odb);
        //Metodos.consulta_xogadores(odb);
        //Metodos.actualiza_por_nome_xogador(odb,"alfonso","carlos");
        //Metodos.xogadoresDeporte(odb,"volley-ball");
        //Metodos.devoltar_equipos_con_xogadores_menos_dunha_cantidade(odb,1501);
        Metodos.desconectar(odb);
    }
    
}
