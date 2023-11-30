package it.edu.iisgubbio.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MisuraController {

    @Autowired
    MisuraRepository repoMisura;
    
    @CrossOrigin
    @GetMapping("/20")
    public List<Misura> ultimi20() {
        List<Misura> k = repoMisura.ultimi20();
        return k;
    }

    @GetMapping("/ins") // sarebbe un POST ma mi sa che i dati son comodi da ricevere così
    public String cerca(
        @RequestParam(required = true) double t, // tensione
        @RequestParam(required = true) double c, // corrente
        @RequestParam(required = true) double p, // potenza
        @RequestParam(required = false) Double r,// rpm
        @RequestParam(required = false) String f // fornitore
    ) {
    	if(Stato.talos) {
    		try {
	    		// https://www.baeldung.com/rest-template
	    		RestTemplate restTemplate = new RestTemplate();
	    		String uri = "http://kili.aspix.it:8008/ins?t="+t+"+&p="+p+"&c="+c+"&f=simulatore"; // or any other uri
	    		if(r!=null) {
	    			uri += "&r="+r.doubleValue();
	    		}
	    		restTemplate.getForEntity(uri, String.class);
    		}catch(Exception x) {
    			; // pazienza
    		}
    	}
        Misura m = new Misura(f,t,c,p,r);
        repoMisura.save(m);
        return "ok";
    }

}