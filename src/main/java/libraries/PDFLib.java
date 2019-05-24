package libraries;

import java.io.IOException;

import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

import utilities.TestBase;

public class PDFLib extends TestBase {

	PDFUtil pdfUtil = new PDFUtil();

	public boolean checkPDFPageCount(String filePath, int length) throws IOException {
		int actual = pdfUtil.getPageCount(filePath);
		if (actual == length) {
			log.info("Page count of pdf file is matching with count provided");
			return true;
		} else {
			log.info("Page count of pdf file is not matching with count provided");
			return false;
		}
	}

	public boolean comparePDFText(String file1Path, String file2Path) throws IOException {
		String file1 = file1Path;
		String file2 = file2Path;
		pdfUtil.setCompareMode(CompareMode.TEXT_MODE);
		if (pdfUtil.compare(file1, file2))
			log.info("PDF comparison has passed");
		else
			log.error("PDF comparison has failed");
		return pdfUtil.compare(file1, file2);
	}

	public boolean comparePDFTextSpecificPage(String file1Path, String file2Path, int pageStart, int pageEnd)
			throws IOException {
		String file1 = file1Path;
		String file2 = file2Path;
		pdfUtil.setCompareMode(CompareMode.TEXT_MODE);
		if (pdfUtil.compare(file1, file2))
			log.info("PDF comparison has passed");
		else
			log.error("PDF comparison has failed");
		return pdfUtil.compare(file1, file2, pageStart, pageEnd);
	}

	public boolean comparePDFImageModeSame(String file1Path, String file2Path, int pageStart, int pageEnd)
			throws IOException {
		String file1 = file1Path;
		String file2 = file2Path;
		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
		if (pdfUtil.compare(file1, file2))
			log.info("PDF comparison has passed");
		else
			log.error("PDF comparison has failed");
		return pdfUtil.compare(file1, file2, pageStart, pageEnd);
	}

	public boolean comparePDFWithoutNumbers(String file1Path, String file2Path, int pageStart, int pageEnd)
			throws IOException {
		String file1 = file1Path;
		String file2 = file2Path;
		pdfUtil.excludeText("\\d+");
		pdfUtil.setCompareMode(CompareMode.TEXT_MODE);
		if (pdfUtil.compare(file1, file2))
			log.info("PDF comparison has passed");
		else
			log.error("PDF comparison has failed");
		return pdfUtil.compare(file1, file2, pageStart, pageEnd);
	}

}
