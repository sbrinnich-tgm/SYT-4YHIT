<h1>Enterprise Application Integration</h1>
GRUPPE: Brinnich/Hohenwarter

"<i>The ETL (Extract, Transform, Load) is a mechanism for loading data into systems or databases using some kind of Data Format from a variety of sources; often files then using Pipes and Filters, Message Translator and possible other Enterprise Integration Patterns.
So you could query data from various Camel Components such as File, HTTP or JPA, perform multiple patterns such as Splitter or Message Translator then send the messages to some other Component.
To show how this all fits together, try the ETL Example.</i>" [1]

ETL ist ein wichtiger Prozess bei einem Datawarehouse. Zeigen Sie wie Enterprise Integration Patterns [2] dabei eingesetzt werden können (8 Punkte, nur jene, die in dem Beispiel vorkommen). Verwenden Sie dazu das ETL Example [3]. Dokumentieren Sie die Implementierung sowie alle notwendigen Schritte ausführlich in einem Protokoll (8 Punkte). Fügen Sie den verwendeten Code nach den Metaregeln an und geben Sie alles als ZIP-Archiv (Gesamtes Framework mit Anleitung, wie das System gestartet werden kann) ab.


<h4>Resources</h4>
[1] Extract Transform Load (ETL); Apache Camel; Online: http://camel.apache.org/etl.html; abgerufen 13.02.2015
[2] Enterprise Integration Patterns; G.Hohpe, B.Woolf; 2003; Online: http://www.enterpriseintegrationpatterns.com/toc.html; abgerufen 13.02.2015
[3] Extract Transform Load (ETL) Example; Apache Camel; Online: http://camel.apache.org/etl-example.html; abgerufen 13.02.2015

<h4>Starting the Example</h4>
Go to /example and type mvn compile. After it finished type mvn camel:run. It is required that you have installed Apache Camel and Maven1.