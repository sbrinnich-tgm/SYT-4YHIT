package brinnichHohenwarter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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
 * @author Niklas Hohenwarter
 * @author Selina Brinnich
 * @version 2014-11-23
 */
public class MOMConnection {
	
	private static String user = ActiveMQConnection.DEFAULT_USER;
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String url;
	private static String subject;
	private boolean isTopic;
	
	private TopicSession tsession;
	private TopicConnection tconnection;
	
	private Session qsession;
	private Connection qconnection;
	private Destination destination;
	
	private TopicSubscriber subscriber;
	private TopicPublisher publisher;
	
	private MessageProducer producer;
	private MessageConsumer consumer;
	
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
		this.isTopic = isTopic;
		this.createConnection();
	}
	
	/**
	 * Erstellt ein MOM Objekt mit den Uebergebenen Parametern
	 * 
	 * @param url IP des MOM
	 * @param isTopic gibt an ob die Connection eine Queue oder ein Topic ist
	 */
	public MOMConnection(String url, boolean isTopic){
		MOMConnection.url = "failover://tcp://"+url+":61616";
		this.isTopic = isTopic;
		this.createConnection();
	}

	/**
	 * Erstellt eine Verbindung zum MOM
	 */
	public void createConnection() {
		
		if (isTopic) {
			try {
				TopicConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			
				tconnection = connectionFactory.createTopicConnection();
//				tconnection.setClientID(User.username + User.userip+"CHAT");
				tconnection.start();

				tsession = tconnection.createTopicSession(false,
						Session.AUTO_ACKNOWLEDGE);

				Topic topic = tsession.createTopic(subject);
				publisher = tsession.createPublisher(topic);
				subscriber = tsession.createSubscriber(topic);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			try {
				// Verbindung herstellen
				ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
						user, password, url);
				qconnection = connectionFactory.createConnection();
//				qconnection.setClientID(User.username+User.userip+"MAIL");
				qconnection.start();

				
				// Session erstellen
				qsession = qconnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				
				// Auf Queue zugreifen
				destination = qsession.createQueue(subject);
				consumer = qsession.createConsumer(destination);
				producer = qsession.createProducer(destination);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Schliesst die Verbindung und alle dazugehoerigen Streams
	 */
	public void closeConnection() {
		try {
			if(isTopic){
				subscriber.close();
				publisher.close();
				tconnection.stop();
				tconnection.close();
				tsession.close();
			}else{
				consumer.close();
				producer.close();
				qconnection.stop();
				qconnection.close();
				qsession.close();
			}
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
	 * Gibt ein Nachrichten-Empfaenger Objekt zurueck
	 * @return ein Nachrichten-Empfaenger Objekt, das auf ein bestimmtes Topic registriert ist
	 */
	public TopicSubscriber getSubscriber(){
		return this.subscriber;
	}
	
	/**
	 * Gibt ein Nachrichten-Sender Objekt zurueck
	 * @return ein Nachrichten-Sender Objekt, das auf ein bestimmted Topic registriert ist
	 */
	public TopicPublisher getPublisher(){
		return this.publisher;
	}

	/**
	 * Gibt ein Topic-Session Objekt zurueck
	 * @return ein Topic-Session Objekt, das auf ein bestimmtes Topic registriert ist
	 */
	public TopicSession getTopicSession() {
		return tsession;
	}
	
	/**
	 * Gibt ein Queue-Session Objekt zurueck
	 * @return ein Queue-Session Objekt, das auf eine bestimmte Connection registriert ist
	 */
	public Session getQueueSession() {
		return qsession;
	}
	
	/**
	 * Setzt das Subject (Name des Topics/der Queue)
	 * @param newSubject der Name des neuen Topics/der neuen Queue
	 */
	public void setSubject(String newSubject){
		this.closeConnection();
		MOMConnection.subject = newSubject;
		this.createConnection();
	}
	
}
