package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*************************************************************************************************
 * Le query native sono per H2, in alcuni casi c'è l'equivalente per postgreSQL
 ************************************************************************************************/
public interface MisuraRepository extends JpaRepository<Misura, Integer>{

    /*pg: SELECT misura.* FROM misura
      WHERE ts>(now() - interval '1 hour' )
      ORDER BY ts DESC LIMIT 20;*/
	  @Query(value="""
	    SELECT misura.*
      FROM misura
      WHERE ts>DATEADD(HOUR, -1, CURRENT_TIMESTAMP())
      ORDER BY ts DESC LIMIT 20""", 
       nativeQuery=true)
    List<Misura> ultimi20();
    
	  
    @Query(value="""
        SELECT misura.* 
        FROM misura 
        ORDER BY ts DESC LIMIT 1""", 
       nativeQuery=true)
    Misura ultimo();
    
    
    @Query(value="""
        SELECT misura.*
        FROM misura
    		WHERE fornitore=:quale
        ORDER BY ts DESC LIMIT 1""", 
       nativeQuery=true)
    Misura ultimo(@Param("quale") String quale);
    
    
    /*pg: SELECT extract (hour from ts) as id,
                avg(corrente) corrente,
                avg(tensione) tensione,
                avg(potenza) potenza,
                avg(rpm) rpm,
                max(ts) as ts,
                'stat' as fornitore
                FROM misura
                WHERE  CAST(ts AS DATE)= '2023-12-11'
                GROUP by extract(hour from ts);*/
    @Query(value="""
        SELECT HOUR (ts) as id,
    		avg(corrente) corrente,
    		avg(tensione) tensione,
    		avg(potenza) potenza,
    		avg(rpm) rpm,
    		max(ts) as ts,
    		'stat' as fornitore
    		FROM misura
    		WHERE  CAST(ts AS DATE)= :data
    		GROUP BY HOUR (ts)""",
       nativeQuery=true)
    List<Misura> giornata(@Param("data") String data);
    
    // uso percentile perché max non è significativo: becca anche dei numeri sballati
    @Query(value="""
        SELECT 0 id,
        PERCENTILE_DISC(0.99999) WITHIN GROUP (ORDER BY corrente) as corrente,  
        PERCENTILE_DISC(0.99999) WITHIN GROUP (ORDER BY tensione) as tensione,
        PERCENTILE_DISC(0.99999) WITHIN GROUP (ORDER BY potenza) as potenza,
        0 as rpm,
        max(ts) as ts,
        'stat' as fornitore
        FROM misura""",
       nativeQuery=true)
    Misura max(); // FIXME: servirebbe come parametro l'id del dispositivo
    
}