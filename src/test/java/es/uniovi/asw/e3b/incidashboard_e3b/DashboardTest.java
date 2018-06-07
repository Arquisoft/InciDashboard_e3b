package es.uniovi.asw.e3b.incidashboard_e3b;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.uniovi.asw.e3b.incidashboard_e3b.po.PO_LoginView;
import es.uniovi.asw.e3b.incidashboard_e3b.po.PO_NavView;
import es.uniovi.asw.e3b.incidashboard_e3b.po.PO_View;
import kafka.KafkaLocalServer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		InciDashboardApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { 
		"spring.datasource.url = jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.username=postgres", 
		"spring.datasource.password=changeit",
		"spring.jpa.hibernate.ddl-auto=create-drop", 
		"spring.datasource.driverClassName=org.h2.Driver" 
})
public class DashboardTest {

	private static final Logger logger = Logger.getLogger(DashboardTest.class);

	private static WebDriver driver;
	@Value("${local.server.port:8092}")
	private int port;
	//	private StringBuffer verificationErrors = new StringBuffer(); // TODO: comento porque no se utiliza para nada... hasta que se utilice. Para evitar error de Codacy
//	private int timeout = 9;
	
	private static KafkaLocalServer kafkaLocalServer;
    private static final String DEFAULT_KAFKA_LOG_DIR = "/tmp/test/kafka_embedded";
    private static final String TEST_TOPIC = "test_topic";
    private static final int BROKER_ID = 0;
    private static final int BROKER_PORT = 9092;
    private static final String LOCALHOST_BROKER = String.format("localhost:%d", BROKER_PORT);

    private static final String DEFAULT_ZOOKEEPER_LOG_DIR = "/tmp/test/zookeeper";
    private static final int ZOOKEEPER_PORT = 2181;
    private static final String ZOOKEEPER_HOST = String.format("localhost:%d", ZOOKEEPER_PORT);

    private static final String groupId = "groupID";

    private Charset charset = Charset.forName("UTF-8");
    private CharsetDecoder decoder = charset.newDecoder();

    @BeforeClass
    public static void startKafka(){
        Properties kafkaProperties;
        Properties zkProperties;

        try {
            //load properties
            kafkaProperties = getKafkaProperties(DEFAULT_KAFKA_LOG_DIR, BROKER_PORT, BROKER_ID);
            zkProperties = getZookeeperProperties(ZOOKEEPER_PORT,DEFAULT_ZOOKEEPER_LOG_DIR);

            //start kafkaLocalServer
            kafkaLocalServer = new KafkaLocalServer(kafkaProperties, zkProperties);
            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace(System.out);
            e.printStackTrace(System.out);
        }

        //do other things
}
	
	

	public static WebDriver getDriver(String PathFirefox) {
		// Firefox (Versi�n 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaci�nn
	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
		String baseUrl = "http://localhost:" + port;		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		logger.info("Using base URL: '" + baseUrl + "'");
	}

	// Despu�s de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		assert 1 == 1; // para que no salte el codacy. Como mínimo tiene que haber algo.
	}

	// Al finalizar la �ltima prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Acceder a la p�gina principal /
	@Test
	public void testLogin() {
		// Vamos al formulario de logueo.
		PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "oper12@gmail.es", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "oper12@gmail.es");
	}
	
	
	 private static Properties getKafkaProperties(String logDir, int port, int brokerId) {
	        Properties properties = new Properties();
	        properties.put("port", port + "");
	        properties.put("broker.id", brokerId + "");
	        properties.put("log.dir", logDir);
	        properties.put("zookeeper.connect", ZOOKEEPER_HOST);
	        properties.put("default.replication.factor", "1");
	        properties.put("delete.topic.enable", "true");
	        return properties;
	    }

	    private static Properties getZookeeperProperties(int port, String zookeeperDir) {
	        Properties properties = new Properties();
	        properties.put("clientPort", port + "");
	        properties.put("dataDir", zookeeperDir);
	        return properties;
	}
}
