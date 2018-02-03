package com.ebay.assignment;

import com.ebay.assignment.model.LoanersCreditLimitConflict;
import com.ebay.assignment.model.Workbook;
import com.ebay.assignment.services.WorkbookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication
@Controller
public class WorkbookApplication {

	final WorkbookService workbookService;

    public WorkbookApplication(WorkbookService workbookService) {
        this.workbookService = workbookService;
    }

    public static void main(String[] args) {
		SpringApplication.run(WorkbookApplication.class, args);
	}

	@RequestMapping("/workbooks2")
	public String displayWorkbooksAndConflicts(
                @RequestParam(value="sources", required=false, defaultValue="Workbook2.csv,Workbook2.prn") String sources,
                Model model) {

	    populateViewWithConflicts(model, populateViewWithWorkbooks(model, StringUtils.commaDelimitedListToStringArray(sources)));

		return "workbooks_and_conflicts";
	}

    @RequestMapping("/workbooks2/api/conflicts")
    public @ResponseBody List<LoanersCreditLimitConflict> fetchCreditLimitConflicts(
            @RequestParam(value="sources", required=false, defaultValue="Workbook2.csv,Workbook2.prn") String sources) {

        return workbookService.findLoanersWithCreditConflict(
                workbookService.loadWorkbooksFromFiles(StringUtils.commaDelimitedListToStringArray(sources)));
    }

	private List<Workbook> populateViewWithWorkbooks(Model model, String... sources) {
        List<Workbook> workbooks = workbookService.loadWorkbooksFromFiles(sources);
        workbooks.forEach(workbook -> {
            switch (workbook.getSourceType()) {
                case CSV:
                    model.addAttribute("cvsLoaners", workbook.getLoaners());
                    break;
                case PRN:
                    model.addAttribute("prnLoaners", workbook.getLoaners());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported sourceType " + workbook.getSourceType());
            }
        });
        return workbooks;
    }

    private void populateViewWithConflicts(Model model, List<Workbook> workbooks) {
        List<LoanersCreditLimitConflict> conflicts = workbookService.findLoanersWithCreditConflict(workbooks);
        model.addAttribute("conflicts", conflicts);
    }
}
