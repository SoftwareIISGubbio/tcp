package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MisuraRepository extends JpaRepository<Misura, Integer>{

	@Query(value="SELECT misura.* "+
            "FROM misura "+
            "ORDER BY ts DESC LIMIT 20", 
       nativeQuery=true)
    List<Misura> ultimi20();
    
    @Query(value="SELECT misura.* "+
            "FROM misura "+
            "ORDER BY ts DESC LIMIT 1", 
       nativeQuery=true)
    Misura ultimo();
    
    @Query(value="SELECT misura.* "+
            "FROM misura "+
    		"WHERE fornitore=:quale "+
            "ORDER BY ts DESC LIMIT 1", 
       nativeQuery=true)
    Misura ultimo(@Param("quale") String quale);
    
    @Query(value="SELECT HOUR (ts) as id, "+
    		"avg(corrente) corrente, "+
    		"avg(tensione) tensione, "+
    		"avg(potenza) potenza, "+
    		"avg(rpm) rpm, "+
    		"max(ts) as ts, "+
    		"'stat' as fornitore"+
    		" FROM misura"+
    		" WHERE  CAST(ts AS DATE)= :data "+
    		" GROUP BY HOUR (ts)",
       nativeQuery=true)
    List<Misura> giornata(@Param("data") String data);
    
    /*
     http://www.h2database.com/html/functions.html
     
     
SELECT  * from misura WHERE ts>DATEADD(SECOND, -1, CURRENT_TIMESTAMP()) 
SELECT  sum(potenza) from misura WHERE ts>DATEADD(SECOND, -1, CURRENT_TIMESTAMP()) 
     
SELECT hour(ts), minute(ts),avg(potenza) 
FROM misura 
WHERE hour(ts)=6
GROUP BY hour(ts), minute(ts)
ORDER BY hour(ts), minute(ts) DESC
limit 10
     */
}