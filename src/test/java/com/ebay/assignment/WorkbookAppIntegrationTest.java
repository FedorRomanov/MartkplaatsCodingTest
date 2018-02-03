package com.ebay.assignment;

import com.ebay.assignment.model.LoanersCreditLimitConflict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WorkbookAppIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void fetchingConflictsFromWorkbookServiceShouldReturnSingleConflict() {
        List<LoanersCreditLimitConflict> actualConflicts =
                this.restTemplate.getForObject("/workbooks2/api/conflicts", List.class);

        assertThat("size of actual conflicts is not 1", actualConflicts, hasSize(1));
	}

}
