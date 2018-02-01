/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioneodatis;

import java.util.ArrayList;
import java.util.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;

/**
 *
 * @author oracle
 */
public class Metodos {

    public static final String ODB_NAME = "base.neodatis";

    public static ODB conectar() {
        //Abrir base de datos
        ODB odb = ODBFactory.open(ODB_NAME);
        return odb;
    }

    public static void desconectar(ODB odb) {
        odb.close();
    }

    public static void añadirDeporte(ODB odb, String deporte) throws Exception {
        //Crear instancia
        Sport sport = new Sport(deporte);
        //Almacenar el objeto
        odb.store(sport);
        odb.commit();
    }

    public static void amosar_deportes(ODB odb) throws Exception {
        int x = 1;
        /*IQuery query = odb.criteriaQuery(Sport.class, Where.or()
         .add(Where.equal("name", "volley-ball"))
         .add(Where.equal("name", "tennis")));
         Objects<Sport> deportes = odb.getObjects(query);
         System.out.println("  --Deportes--");
         while (deportes.hasNext()) {
         System.out.println((x++) + "\t" + deportes.next());
         }*/
        
        //Coger todos los objetos de tipo sport
        Objects<Sport> sports = odb.getObjects(Sport.class);
        //Mostrar cada objeto
        Sport sport = null;
        while (sports.hasNext()) {
            sport = sports.next();
            System.out.println((x++) + "\t" + sport.getName());
        }
        //Cerrar base de datos
        odb.commit();
    }

    public static void consulta_xogadores(ODB odb) {
        int x = 1;
        Objects<Player> jugadores = odb.getObjects(Player.class);
        Player jugador = null;
        System.out.println("    --JUGADORES--");
        while (jugadores.hasNext()) {
            jugador = jugadores.next();
            System.out.println((x++) + "\t" + jugador.getName() + "," + jugador.getFavoriteSport() + "," + jugador.getSalario());
        }
    }

    public static void añadirPartidos(ODB odb) throws Exception {
        //Crear instancia
        Sport volleyball = new Sport("volley-ball");
        Sport tennis = new Sport("tennis");
        //Crear 8 jugadores
        Player xogador_1 = new Player("oliver", new Date(), volleyball, 1000);
        Player xogador_2 = new Player("pierre", new Date(), volleyball, 1500);
        Player xogador_3 = new Player("elohim", new Date(), volleyball, 2000);
        Player xogador_4 = new Player("minh", new Date(), volleyball, 1300);
        Player xogador_5 = new Player("luis", new Date(), tennis, 1600);
        Player xogador_6 = new Player("carlos", new Date(), tennis, 2000);
        Player xogador_7 = new Player("luis", new Date(), tennis, 1500);
        Player xogador_8 = new Player("jose", new Date(), tennis, 3000);

        //Crear cuatro equipos
        Team team1 = new Team("Paris");
        Team team2 = new Team("Montepellier");
        Team team3 = new Team("Bordeux");
        Team team4 = new Team("Lion");
        //Añadir jugadores al equipo 1
        team1.addPlayer(xogador_1);
        team1.addPlayer(xogador_2);
        //Añadir jugadores al equipo 2
        team2.addPlayer(xogador_3);
        team2.addPlayer(xogador_4);
        //Añadir jugadores al equipo 3
        team3.addPlayer(xogador_5);
        team3.addPlayer(xogador_6);
        //Añadir jugadores al equipo 4
        team4.addPlayer(xogador_7);
        team4.addPlayer(xogador_8);

        //Añadimos 2 equipos
        Game partido1 = new Game(new Date(), volleyball, team1, team2, "2-0");
        Game partido2 = new Game(new Date(), tennis, team3, team4, "1-2");

        odb.store(partido1);
        odb.store(partido2);
        odb.commit();

    }

    public static void actualiza_por_nome_xogador(ODB odb, String nomeAntigo, String nomeNovo) {
        IQuery query = odb.criteriaQuery(Player.class, Where.equal("name", nomeAntigo));
        Objects<Player> jugadores = odb.getObjects(query);
        Player jugador = null;
        while (jugadores.hasNext()) {
            jugador = jugadores.next();
            jugador.setName(nomeNovo);
            odb.store(jugador);
        }
        odb.commit();
    }
    public static void xogadoresDeporte(ODB odb,String deporte){
        int x = 1;
        IQuery query=odb.criteriaQuery(Player.class,Where.equal("favoriteSport.name",deporte));
        Objects<Player> jugadores = odb.getObjects(query);
        Player jugador = null;
        System.out.println("    --JUGADORES--");
        while (jugadores.hasNext()) {
            jugador = jugadores.next();
            System.out.println((x++)+"\t"+jugador.getName());
        }
    }
    public static void devoltar_equipos_con_xogadores_menos_dunha_cantidade(ODB odb,int cantidade){
        int x = 1;
        Objects<Team> equipos = odb.getObjects(Team.class);
        ArrayList<Player> jugadores= null;
        Team equipo=null;
        System.out.println("    --JUGADORES--");
        while (equipos.hasNext()) {
            equipo=equipos.next();
            jugadores=(ArrayList<Player>) equipo.getPlayers();
            for(int i=0;i<jugadores.size();i++){
                int salario=jugadores.get(i).getSalario();
                if(salario<cantidade){
                    System.out.println(jugadores.get(i).getName()+","+equipo.getName());
                }
            }
        }
    }
}
