package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*************************************************************************************************
 * Le query native sono per H2, in alcuni casi c'è l'equivalente per postgreSQL
 ************************************************************************************************/
public interface MisuraRepository extends JpaRepository<Misura, Integer> {

    /*
     * pg: SELECT misura.* FROM misura
     * WHERE ts>(now() - interval '1 hour' )
     * ORDER BY ts DESC LIMIT 20;
     */
    @Query(value = """
            SELECT misura.*
            FROM misura
            WHERE ts>DATEADD(HOUR, -1, CURRENT_TIMESTAMP())
            ORDER BY ts DESC LIMIT 20""", nativeQuery = true)
    List<Misura> ultimi20();

    @Query(value = """
            SELECT misura.*
            FROM misura
            ORDER BY ts DESC LIMIT 1""", nativeQuery = true)
    Misura ultimo();

    @Query(value = """
            SELECT misura.*
            FROM misura
            WHERE fornitore=:quale
            ORDER BY ts DESC LIMIT 1""", nativeQuery = true)
    Misura ultimo(@Param("quale") String quale);

    @Query(value = """
            SELECT HOUR (ts) as id, 
            round(avg(corrente),3) as corrente, 
            round(avg(tensione),3) as tensione, 
            round(avg(potenza),3) as potenza, 
            round(avg(rpm),3) as rpm, 
            max(ts) as ts, 
            'stat' as fornitore 
            FROM misura 
            WHERE  CAST(ts AS DATE)= :data 
            GROUP BY HOUR (ts)""", nativeQuery = true)
    List<Misura> giornata(@Param("data") String data);

    // L'uso di PERCENTILE mi permette di scartare i valori anomali
    @Query(value = """
            SELECT 0 id, 
            PERCENTILE_DISC(0.98) WITHIN GROUP (ORDER BY corrente) as corrente, 
            PERCENTILE_DISC(0.98) WITHIN GROUP (ORDER BY tensione) as tensione, 
            PERCENTILE_DISC(0.98) WITHIN GROUP (ORDER BY potenza) as potenza, 
            0 as rpm, 
            max(ts) as ts, 
            'stat' as fornitore 
            FROM misura""", nativeQuery = true)
    Misura max(); // FIXME: servirebbe come parametro l'id del dispositivo

    /*
     * http://www.h2database.com/html/functions.html
     * 
     * select PERCENTILE_DISC(0.99999) WITHIN GROUP (ORDER BY corrente),
     * PERCENTILE_DISC(0.99999) WITHIN GROUP (ORDER BY tensione),
     * max(corrente)
     * FROM misura
     * 
     * SELECT * from misura WHERE ts>DATEADD(SECOND, -1, CURRENT_TIMESTAMP())
     * SELECT sum(potenza) from misura WHERE ts>DATEADD(SECOND, -1,
     * CURRENT_TIMESTAMP())
     * 
     * SELECT hour(ts), minute(ts),avg(potenza)
     * FROM misura
     * WHERE hour(ts)=6
     * GROUP BY hour(ts), minute(ts)
     * ORDER BY hour(ts), minute(ts) DESC
     * limit 10
     */
}
