# Tensione Corrente Potenza

un semplice server per immagazzinare 3 misure e visualizzarle


il server parte sulla porta 8008

## query

raggruppa le misure per mese

SELECT FORMATDATETIME(TS,'yyyy-MM-dd') AS mese, count(*) from misura group by FORMATDATETIME(TS,'yyyy-MM-dd');

cancellare un mese

delete from misura where FORMATDATETIME(TS,'yyyy-MM')='2024-01';

per compattare il file di H2

SHUTDOWN COMPACT

per testare velocità query

SELECT misura.*
FROM misura
WHERE ts>DATEADD(HOUR, -1, CURRENT_TIMESTAMP())
ORDER BY ts DESC LIMIT 20
