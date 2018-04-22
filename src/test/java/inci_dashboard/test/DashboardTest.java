package inci_dashboard.test;

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
import org.springframework.test.context.junit4.SpringRunner;

import inci_dashboard.InciDashboardApplication;
import inci_dashboard.test.PO.PO_LoginView;
import inci_dashboard.test.PO.PO_NavView;
import inci_dashboard.test.PO.PO_View;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		InciDashboardApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DashboardTest {

	private static final Logger logger = Logger.getLogger(DashboardTest.class);

	private static WebDriver driver;
	@Value("${local.server.port:8090}")
	private int port;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private int timeout = 9;

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
		baseUrl = "http://localhost:" + port;
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
		PO_LoginView.fillForm(driver, "oper121223221@gmail.es", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "oper12@gmail.es");
	}
}
