package brinnichHohenwarter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

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
	private static String url;
	private static String subject;
	private static boolean isTopic;
	
	private TopicSession session;
	private TopicConnection connection;
	private Destination destination;
	
	private TopicSubscriber consumer;
	private TopicPublisher producer;
	
	/**
	 * Erstellt ein MOM Objekt mit den Uebergebenen Parametern
	 * 
	 * @param url IP des MOM
	 * @param subject gewueschtest Topic/Queue
	 * @param isTopic gibt an ob die Connection eine Queue oder ein Topic ist
	 */
	public MOMConnection(String url, String subject, boolean isTopic){
		MOMConnection.url = "failover://tcp://"+url+":61616";
		MOMConnection.subject = subject;
		this.createConnection();
	}
	
	/**
	 * Erstellt ein MOM Objekt mit den Uebergebenen Parametern
	 * 
	 * @param url IP des MOM
	 * @param subject gewueschtest Topic/Queue
	 * @param isTopic gibt an ob die Connection eine Queue oder ein Topic ist
	 */
	public MOMConnection(String url, boolean isTopic){
		MOMConnection.url = "failover://tcp://"+url+":61616";
	}

	/**
	 * Erstellt eine Verbindung zum MOM
	 */
	public void createConnection() {
		/**
		try {
			// Verbindung herstellen
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			connection = connectionFactory.createConnection();
			connection.setClientID(User.username+User.userip);
			connection.start();

			
			// Session erstellen
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			// Auf Topic zugreifen
			if(isTopic==true){
				Topic topic = session.createTopic(subject);
				// Sender und Empfaenger erstellen
				consumer = session.createDurableSubscriber(topic, "SUB"+User.username+User.userip);
				producer = session.createProducer(topic);
			}else{
				destination = session.createQueue(subject);
				consumer = session.createConsumer(destination);
				producer = session.createProducer(destination);
			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		try {
			connection = connectionFactory.createTopicConnection();
			connection.setClientID(User.username+User.userip);
			connection.start();
		
			session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic topic = session.createTopic(subject);
			producer = session.createPublisher(topic);
			consumer = session.createSubscriber(topic);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Schliesst die Verbindung und alle dazugehoerigen Streams
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
	
	/**
	 * Setzt das Subject (Name des Topics/der Queue)
	 * @param newSubject der Name des neuen Topics/der neuen Queue
	 */
	public void setSubject(String newSubject){
		MOMConnection.subject = newSubject;
		this.createConnection();
	}
	
}
