# PortalVax

Configurazione ambiente di Sviluppo
Se si desidera aprire il progetto in un ambiente di sviluppo per implementare/modificare codice o semplicemente per visionarlo segui i seguenti passaggi.
Requisiti Sistema
Per lo sviluppo sono state usate le tecnologie sopra elencati, assicurati di averli installati tutti e configurati in maniera giusta.
Java (JDK 16)
per verificare la versione apri il terminale ed esegui il comando
java -version

Se la versione risulta differente, consigliamo di installare questa versione per eseguire e leggere le classi del progetto.
Maven
per verificare la versione apri il terminale ed esegui il comando
mvn -version

IDE
È importante avere un IDE, per esempio Intellij IDEA (che ha già preinstallato Maven) o Eclipse.
PostgreSQL
Per la base dati serve avere un RDBMS, ad esempio noi abbiamo usato PostgreSQL (in ascolto sulla sua porta default la 5432).
Configurazione Ambiente
Maven Project Load
Apri il progetto nel tuo IDE e assicurati che venga caricato bene le configurazioni del Maven Project.
Configurare il Compiler
Per eseguire e leggere correttamente i file java all’interno dell’IDE è importante settare per il progetto il jdk 16.
Creazione Database
Per creare il database in postgresql è presente uno script maven apposito, si collegherà al DBMS alla porta di default 5432 ed eseguirà le istruzioni sql presenti nel file CreateDatabase.sql
Il comando maven per far partire questo script è
mvn install -DUSER=<user> -DPWD=<pwd> -f creaDBpom.xml

La variabile:
<user> è l’utente postgres che vuoi usare
<pwd> è la password dell’utente postgres
creaDBpom.xml è il file dove è descritto lo script maven
Esecuzione ed Uso Ambiente
Per eseguire i 2 applicativi del progetto si possono avviare le classi main dall’IDE oppure generare gli eseguibili.
Creazione Eseguibili
Per creare gli eseguibili di un progetto maven bisogno eseguire 2 comandi: clean e install
Comando Clean
Permette di eliminare le classi target che contiene i file .class e gli altri dati usati per l’esecuzione del codice sorgente, ecco la sintassi del comando
mvn clean -f pom.xml

Comando Install
Permette di far partire lo script di generazione dei 2 eseguibili .jar, che poi si troveranno nella cartella out
mvn install -f pom.xml

Popolazione del Database per Test (DataSet)
Per popolare il database in postgresql con dati utili per effettuare dei test è presente uno script maven apposito, si collegherà al DBMS alla porta di default 5432 ed eseguirà le istruzioni sql presenti nel file LoadTestDatabase.sql
Il comando maven per far partire questo script è
mvn install -DUSER=<user> -DPWD=<pwd> -f testDBpom.xml

La variabile:
<user> è l’utente postgres che vuoi usare
<pwd> è la password dell’utente postgres
testDBpom.xml è il file dove è descritto lo script maven

le librerie usate sono:
org.openjfx:javafx e derivate, usate per UI
org.postgresql usata per collegamento e utilizzo db postgres