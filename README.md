# LaTazza
_LaTazza_ è un programma per gestire la fornitura di bustine di bevande calde (ovvero caffè, caffè arabico, tè, tè al limone e tè alla camomilla) e la loro vendita ad impiegati aziendali e visitatori.

## Descrizione
In _LaTazza_, la segretaria aziendale può:
* Visualizzare il saldo in cassa;
* Visualizzare la quantità di bustine disponibili in deposito per ciascuna bevanda calda;
* Visualizzare l'elenco degli impiegati debitori;
* Registrare una fornitura di bustine di una bevanda calda;
* Registrare la vendita di bustine di una bevanda calda ad impiegati (i quali possono pagare a credito) e visitatori;
* Aggiornare il credito degli impiegati;
* Aggiornare gli impiegati registrati.

## Istruzioni per gli sviluppatori
Il progetto è basato su Maven. Seguono alcune istruzioni utili per gli sviluppatori.

| Per fare qusto | Fai questo |
| -----------|-----------|
| Per ripulire il progetto | Digita `mvn clean` |
| Per modificare il codice sorgente | Modifica un file, o più file, in `src/main/java`.<br> Il main file, *GUI.java*, si trova nel package *gui* |
| Per compilare il progetto | Digita `mvn compile` |
| Per eseguire il progetto | Digita `mvn compile exec:java` |

**Nota che:** _LaTazza_ è un'applicazione ad istanza singola, se si prova a avviare un seconda istanza di _LaTazza_ si ottiene un'eccezione di tipo ```org.h2.jdbc.JdbcSQLException``` e il seguente messaggio di errore ```Database may be already in use: Locked by another process. Possible solutions: close all other connection(s); use the server mode [90020-119]```.
