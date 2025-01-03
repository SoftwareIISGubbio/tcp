# Tensione Corrente Potenza

un semplice server per immagazzinare 3 misure e visualizzarle


il server parte sulla porta 8008

## restore di un db H2
ti serve quando fai ugrade: backup poi restore non macina i file vecchi!

2.3.232

http://h2database.com/html/tutorial.html#upgrade_backup_restore

~/.m2/repository/com/h2database/h2/2.3.232/h2-2.3.232.jar

# se serve console


java -cp ~/.m2/repository/com/h2database/h2/2.3.232/h2-2.3.232.jar org.h2.tools.Server
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

