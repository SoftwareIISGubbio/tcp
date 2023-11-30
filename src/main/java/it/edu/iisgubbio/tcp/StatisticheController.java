package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticheController {

    @Autowired
    MisuraRepository repoMisura;

    @CrossOrigin
    @GetMapping("/statistiche/ultimo")
    public Misura ultimo(@RequestParam(required = false) String quale) {
    	if(quale==null) {
    		return repoMisura.ultimo();
    	} else {
    		return repoMisura.ultimo(quale);
    	}
    }
    
    @CrossOrigin
    @GetMapping("/statistiche/giornata")
    public List<Misura> giornata(@RequestParam(required = true) String giorno) {
    	return repoMisura.giornata(giorno);
    }
    
    @CrossOrigin
    @GetMapping("/statistiche/conta")
    public long conta() {
    	return repoMisura.count();
    }
    
    @CrossOrigin
    @GetMapping("/statistiche/giorno")
    public List<Misura> giorno(@RequestParam(required = true) String data) {
        List<Misura> k = repoMisura.giornata(data);
        return k;
    }
    
}