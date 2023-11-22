package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MisuraController {

    @Autowired
    MisuraRepository repoMisura;

    /* insensato
    @GetMapping("/misura")
    public List<Misura> elenco() {
        List<Misura> k = repoMisura.findAll();
        return k;
    }*/
    
    @GetMapping("/20")
    public List<Misura> ultimi20() {
        List<Misura> k = repoMisura.ultimi20();
        return k;
    }

    @GetMapping("/ins") // sarebbe un POST ma mi sa che i dati son comodi da ricevere così
    public void cerca(
        @RequestParam(required = true) double t, // tensione
        @RequestParam(required = true) double c, // corrente
        @RequestParam(required = true) double p, // potenza
        @RequestParam(required = false) double r,// rpm
        @RequestParam(required = false) String f // fornitore
    ) {
        Misura m = new Misura(f,t,c,p,r);
        repoMisura.save(m);
    }

}