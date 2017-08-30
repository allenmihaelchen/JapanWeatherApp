package unitTestCase;

import junit.framework.*;
import main.ForecastDao;

import org.junit.Test;

//Using JUnit to do the unit test
public class TestForecastDao extends TestCase{
	
	public ForecastDao myForecast;
	String dTestCityURL1, dTestCityResult1, dTestCityURL2, dTestCityResult2, dTestCityURL3, dTestCityResult3, dTestCityURL4, dTestCityResult4; 
	
	
	//Start method	
	public void setUp() {
		myForecast = new ForecastDao();
	}
	
	//End method
	public void tarDown() {
		myForecast = null;
	}

	@Test
	public void testTestForecast01() {
		//福岡県 久留米
		dTestCityURL1="http://weather.livedoor.com/forecast/webservice/json/v1?city=400040";
		dTestCityResult1="久留米";
		assertEquals(dTestCityResult1,myForecast.fetchForecast(dTestCityURL1).getLocation().getCity());
	}
	
	@Test
	public void testTestForecast02() {
		//高知県 室戸岬
		dTestCityURL2="http://weather.livedoor.com/forecast/webservice/json/v1?city=390020";
		dTestCityResult2="室戸岬";
		assertEquals(dTestCityResult2,myForecast.fetchForecast(dTestCityURL2).getLocation().getCity());
		
	}
	
	@Test
	public void testTestForecast03() {
		//京都府 舞鶴
		dTestCityURL3="http://weather.livedoor.com/forecast/webservice/json/v1?city=260020";
		dTestCityResult3="舞鶴";
		assertEquals(dTestCityResult3,myForecast.fetchForecast(dTestCityURL3).getLocation().getCity());
	}
	
	@Test
	public void testTestForecast04() {
		//東京都 東京
		dTestCityURL4="http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
		dTestCityResult4="東京";
		assertEquals(dTestCityResult4,myForecast.fetchForecast(dTestCityURL4).getLocation().getCity());
	}

}
