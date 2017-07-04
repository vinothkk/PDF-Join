import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfReader;

public class ItextMerge {
	
	
	
	public static void main(String[] args) {
		
		List<String> lstTdsTrackPrnQue = new ArrayList<String>();
		boolean isCombineSuccess = false;
		Map<String, List<String>> mapPrnGroupIds = new LinkedHashMap<String, List<String>>();
		try {
		String   source="C:\\InputFiles/";
		
		 File f = new File(source);
			String [] lst= f.list();
						
			for(String fileName:lst)
			{
				
				lstTdsTrackPrnQue.add(fileName);
			
			}
			
			
			String destination="C:\\OutputFiles/";
			mapPrnGroupIds.put("CAN_PEND_03072016_C", lstTdsTrackPrnQue);
			String groupId = "CAN_PEND_03072016_C";
			List<String> lstPdForGrdId = new ArrayList<String>();
			
				
			
			
			// for loop for lst groupid
			for (Map.Entry<String, List<String>> e : mapPrnGroupIds
					.entrySet()) {
				groupId = (String) e.getKey();
				lstPdForGrdId = (List<String>) e.getValue();

				String destinationFile=destination + groupId + ".pdf";
				if (new File(destinationFile).exists()
						&& new File(destinationFile).length() > 0) {
				//	logger.info("Already Exists this File: "+destinationFile);
					continue;
				}
				
				Document PDFCombine = new Document();
				PdfCopy copy = new PdfCopy(PDFCombine, new FileOutputStream(
						destination + groupId + ".pdf"));
				PDFCombine.open();
				PdfReader readInputPDF;
				
				int number_of_pages;
				// for loop for lst pdf files

				for (String columnListDisplayValue : lstPdForGrdId) {

					File file = new File(source
							+ columnListDisplayValue
							);

					if (file.exists()) {
						readInputPDF = new PdfReader(source
								+ columnListDisplayValue
								);			
						

						number_of_pages = readInputPDF.getNumberOfPages();
						for (int page = 0; page < number_of_pages;) {
							copy.addPage(copy.getImportedPage(readInputPDF,
									++page));
						}

					} else {

						break;
					}

				}
				PDFCombine.close();
				
				isCombineSuccess = true;
			System.out.println("This is combined PDF " + destination + groupId
						+ ".pdf"
						+ ".............BY The following documents SIZE    :"
						+ lstPdForGrdId.size());

			}
			
			
			

		} catch (Exception i) {
			
			
			StringWriter errors = new StringWriter();
			i.printStackTrace(new PrintWriter(errors));
			 errors.toString();
		}

	
	}
	}
