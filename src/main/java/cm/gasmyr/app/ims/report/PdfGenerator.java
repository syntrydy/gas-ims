package cm.gasmyr.app.ims.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import cm.gasmyr.app.ims.core.sale.Sale;
import cm.gasmyr.app.ims.core.sale.SaleItem;

public class PdfGenerator {

	public static ByteArrayInputStream orderReport(Sale sale, Set<SaleItem> value) {

		java.util.List<SaleItem> items = new ArrayList<>(value);
		Document document = new Document();
		document.addAuthor("https://www.linkedin.com/in/gasmyrmougang/");
		document.addCreator("https://www.linkedin.com/in/gasmyrmougang/");
		document.setMargins(50, 50, 50, 50);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = new PdfPTable(5);
			table.spacingBefore();
			table.spacingAfter();
			table.setHeaderRows(1);
			table.setFooterRows(0);
			table.setWidthPercentage(90);
			table.setWidths(new int[] { 1, 3, 3, 3, 3 });
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("No", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GREEN);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("PRODUCT", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GREEN);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("QUANTITY", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GREEN);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("UNIT PRICE", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GREEN);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("AMOUNT", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GREEN);
			table.addCell(hcell);

			for (SaleItem item : items) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(item.getId().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(item.getItemMovement().getPurchaseItem().getItem().getName()));
				cell.setPaddingLeft(1);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getQuantity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getUnitPrice())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getAmount())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);
			}
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			document.add(addHeader(sale));
			document.add(new Paragraph("\n"));
			document.add(getSeparator());
			document.add(new Paragraph("\n"));
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(getSeparator());
			document.add(new Paragraph("\n"));
			document.add(addFooter(sale));
			document.add(getSeparator());
			document.setPageSize(PageSize.A4);
			setBackground(writer);
			document.close();

		} catch (DocumentException ex) {

			Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static void setBackground(PdfWriter writer) {
		PdfContentByte canvas = writer.getDirectContentUnder();
		Image image=null;
		try {
			image = Image.getInstance("src/main/resources/static/images/fond.jpeg");
			image.scaleAbsolute(PageSize.A4);
	        image.setAbsolutePosition(0, 0);
	        canvas.saveState();
	        PdfGState state = new PdfGState();
	        state.setFillOpacity(0.6f);
	        canvas.setGState(state);
	        canvas.addImage(image);
	        canvas.restoreState();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	private static PdfPTable addHeader(Sale sale) {
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(90);

		table.addCell(getBoldCell("SHOP DETAILS:", PdfPCell.ALIGN_LEFT));
		table.addCell(getBoldCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getBoldCell("CUSTOMER DETAILS:", PdfPCell.ALIGN_RIGHT));

		table.addCell(getBoldCell("\n", PdfPCell.ALIGN_LEFT));
		table.addCell(getBoldCell("\n", PdfPCell.ALIGN_CENTER));
		table.addCell(getBoldCell("\n", PdfPCell.ALIGN_RIGHT));

		table.addCell(getCell(sale.getPointOfSale().getName(), PdfPCell.ALIGN_LEFT));
		table.addCell(getImageCell(PdfPCell.ALIGN_CENTER));
		table.addCell(getCell(sale.getCustomer().getFullName(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getCell(sale.getPointOfSale().getPhone(), PdfPCell.ALIGN_LEFT));
		table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getCell(sale.getCustomer().getPhone(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getCell(sale.getPointOfSale().getAddress(), PdfPCell.ALIGN_LEFT));
		table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getCell(sale.getCustomer().getAddress(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getCell("Currency:" + sale.getPointOfSale().getCurrency().getCode(), PdfPCell.ALIGN_LEFT));
		table.addCell(getCell("", PdfPCell.ALIGN_CENTER));
		table.addCell(getCell(sale.getCustomer().getEmail(), PdfPCell.ALIGN_RIGHT));
		return table;
	}

	public static PdfPCell getCell(String text, int alignment) {
		Font headFont = FontFactory.getFont(FontFactory.COURIER);
		PdfPCell cell = new PdfPCell(new Phrase(text, headFont));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getBoldCell(String text, int alignment) {
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		PdfPCell cell = new PdfPCell(new Phrase(text, headFont));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getRedCell(String text, int alignment) {
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headFont.setColor(BaseColor.RED);
		PdfPCell cell = new PdfPCell(new Phrase(text, headFont));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getImageCell(int alignment) {
		Image i = null;
		try {
			i = Image.getInstance("src/main/resources/static/images/report.png");
		} catch (BadElementException e) {
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		i.setAlignment(Element.ALIGN_RIGHT);
		i.scalePercent(30);
		PdfPCell cell = new PdfPCell(i, false);
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static PdfPTable addFooter(Sale sale) {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(90);

		table.addCell(getBoldCell("DATE:", PdfPCell.ALIGN_LEFT));
		table.addCell(getBoldCell("" + sale.getDate(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getBoldCell("TOTAL AMOUNT:", PdfPCell.ALIGN_LEFT));
		table.addCell(getBoldCell("" + sale.getAmount(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getBoldCell("PAY AMOUNT:", PdfPCell.ALIGN_LEFT));
		table.addCell(getBoldCell("" + sale.getPayAmount(), PdfPCell.ALIGN_RIGHT));

		table.addCell(getBoldCell("STATUS:", PdfPCell.ALIGN_LEFT));
		table.addCell(getRedCell("" + sale.getPaymentStatus().name(), PdfPCell.ALIGN_RIGHT));

		return table;
	}

	private static DottedLineSeparator getSeparator() {
		DottedLineSeparator separator = new DottedLineSeparator();
		separator.setOffset(-4);
		separator.setGap(2f);
		separator.setPercentage(90);
		separator.setLineColor(BaseColor.CYAN);
		return separator;
	}

}
