package brinnichHohenwarter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Erstellt eine Verbindung zu einem Apache ActiveMQ
 * 
 * @author niklas hohenwarter
 * @author selina brinnich
 * @version 2014-11-19
 */
public class MOMConnection {
	
	private static String user = ActiveMQConnection.DEFAULT_USER;
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String url; //"failover://tcp://192.168.1.9:61616"
	private static String subject;
	
	private Session session;
	private Connection connection;
	private Destination destination;
	
	private MessageConsumer consumer;
	private MessageProducer producer;
	
	/**
	 * Erstellt ein MOM Objekt mit den übergebenen Parametern
	 * 
	 * @param url IP des MOM
	 * @param subject gewüschtest Topic/Queue
	 */
	public MOMConnection(String url, String subject){
		MOMConnection.url = "failover://tcp://"+url+":61616";
		MOMConnection.subject = subject;
	}

	/**
	 * Erstellt eine Verbindung zum MOM
	 */
	public void createConnection() {

		try {
			// Verbindung herstellen
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			connection = connectionFactory.createConnection();
			connection.start();

			// Session erstellen
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// Auf Topic zugreifen
			destination = session.createTopic(subject);

			// Sender und Empfaenger erstellen
			consumer = session.createConsumer(destination);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Schließt die Verbindung und alle dazugehörigen Streams
	 */
	public void closeConnection() {
		try {
			connection.stop();
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt ein Nachrichten-Empfaenger Objekt zurueck
	 * @return ein Nachrichten-Empfaenger Objekt, das auf eine bestimmte Connection registriert ist
	 */
	public MessageConsumer getConsumer(){
		return this.consumer;
	}
	
	/**
	 * Gibt ein Nachrichten-Sender Objekt zurueck
	 * @return ein Nachrichten-Sender Objekt, das auf eine bestimmte Connection registriert ist
	 */
	public MessageProducer getProducer(){
		return this.producer;
	}

	/**
	 * Gibt ein Nachrichten-Session Objekt zurueck
	 * @return ein Nachrichten-Session Objekt, das auf eine bestimmte Connection registriert ist
	 */
	public Session getSession() {
		return session;
	}
	
}
