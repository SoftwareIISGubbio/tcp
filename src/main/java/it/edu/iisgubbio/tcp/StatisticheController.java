package it.edu.iisgubbio.tcp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticheController {

    @Autowired
    MisuraRepository repoMisura;

    @GetMapping("/statistiche/ultimo")
    public Misura ultimo() {
        List<Misura> k = repoMisura.ultimo();
        return k.get(0);
    }
    
}