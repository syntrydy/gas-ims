package cm.gasmyr.app.ims.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.report.PdfGenerator;
import cm.gasmyr.app.ims.service.sale.SaleService;

@RestController
public class ReportController {

	@Autowired
	public ReportController(SaleService saleService) {
		this.saleService = saleService;
	}

	@Autowired
	final SaleService saleService;

	@RequestMapping(value = "/order/{id}/invoice", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> OrderInvoice(@PathVariable("id") Long id) throws IOException {
		if (id != null) {
			Sale sale = saleService.getById(id);
			ByteArrayInputStream bis = PdfGenerator.orderReport(sale,sale.getItems());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=orderInvoice.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		}else {
			return ResponseEntity.notFound().build();
			
		}
		
	}

}
