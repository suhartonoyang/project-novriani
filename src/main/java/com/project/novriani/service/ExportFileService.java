package com.project.novriani.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.novriani.bean.PdfRequest;
import com.project.novriani.model.Student;

@Service
public class ExportFileService {

	private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private String[] headers = new String[] { "No", "Nama", "Hasil" };

	public String writeToPDF(String path, List<PdfRequest> results, String username) {
		String fileName = "Result.pdf";
		String file = path + fileName;

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));

			document.open();
			addMetaData(document, username);
			addTitlePage(document);
			addTableResult(document, results);
			document.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return file;
	}

	private void addMetaData(Document document, String username) {
		document.addTitle("Result Processing K-Means");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor(username);
		document.addCreator(username);
	}

	private void addTitlePage(Document document) throws DocumentException {

		Chunk chunk = new Chunk("Laporan Data Hasil Clustering Siswa", catFont);

		Paragraph preface = new Paragraph();
		preface.setAlignment(Paragraph.ALIGN_CENTER);
		preface.add(chunk);
		addEmptyLine(preface, 1);

		document.add(preface);
	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of(headers).forEach(p -> {
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase(p));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table, List<PdfRequest> results) {
		results.stream().forEach(p -> {
			table.addCell(String.valueOf(p.getNo()));
			table.addCell(p.getNama());
			table.addCell(p.getHasil());
		});
	}

	private void addTableResult(Document document, List<PdfRequest> results) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table, results);
		document.add(table);
	}
}
