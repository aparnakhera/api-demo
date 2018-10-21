import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*This class is used to test the acceptance criteria for API using TestNG. */
public class ApiTest {

	private ConnectionUtil connectUtil;

	private ApiTestData apiTestData;

	final static Logger logger = Logger.getLogger(ApiTest.class);

	@BeforeClass
	public void beforeClass() {
		logger.info("Inside Before Class.");
		connectUtil = new ConnectionUtil();
		apiTestData = new ApiTestData();
	}

	/*
	 * Scenario: Test API
	 * "https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false"
	 * Acceptance Criteria: Name = "Carbon credits", CanRelist = true, The
	 * Promotions element with Name = "Gallery" has a Description that contains the
	 * text "2x larger image"
	 */
	@Test
	public void apiScenarioTest() throws Exception {
		logger.info("Validation to test API started.");

		logger.info("Connecting to API url and fetching response.");
		String response = connectUtil.connect(new URL(apiTestData.apiUrl));
		JSONObject obj = new JSONObject(response);

		logger.info("Validating Acceptance Criteria #1- Name = 'Carbon credits'");
		String responseName = obj.getString("Name");
		Assert.assertEquals(responseName, apiTestData.name, "Name is Carbon credits");

		logger.info("Validating Acceptance Criteria #2- CanRelist = 'true'");
		boolean responseCanRelist = obj.getBoolean("CanRelist");
		Assert.assertEquals(responseCanRelist, apiTestData.canRelist, "CanRelist is true");

		logger.info(
				"Validating Acceptance Criteria #3- Promotions element with Name = 'Gallery' has a Description that contains the text '2x larger image'");
		boolean validationResult = false;
		JSONArray arr = obj.getJSONArray("Promotions");
		Assert.assertNotNull(arr, "Promotions Array is not null");
		for (int i = 0; i < arr.length(); i++) {
			String name = arr.getJSONObject(i).getString("Name");
			if (name != null && name.equals(apiTestData.promotionName)) {
				String description = arr.getJSONObject(i).getString("Description");
				if (description != null && description.contains(apiTestData.promotionDesc)) {
					validationResult = true;
					break;
				}
			}
		}
		Assert.assertTrue(validationResult, "Description contains \"2x larger image\"");

		logger.info("Disconnecting to API url.");
		connectUtil.disconnect();
		logger.info("API Connection closed.");
		logger.info("Validation to test API finished.");
	}

	@AfterClass
	public void afterClass() {
		logger.info("Inside After Class.");
	}
}
