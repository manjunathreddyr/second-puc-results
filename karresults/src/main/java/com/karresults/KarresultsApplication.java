package com.karresults;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class KarresultsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarresultsApplication.class, args);
		// 459336
		for (int i=4_00_000;i<=4_01_000;i++){
            getReslut(String.valueOf(i));
        }
	}


	public static void getReslut(String regNo)  {
		MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
		formData.add("frmpuc_tokens","0.7744257");
		formData.add("reg",regNo);
		formData.add("ddlsub","S");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String,String>> requestEntity = new HttpEntity<>(formData,httpHeaders);

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject("https://karresults.nic.in/slakresfirst.asp",requestEntity,String.class);


		Document document = Jsoup.parse(response);
        List<Map<String,Object>> students = new ArrayList<>();
        if (!document.select("table").isEmpty()){
            Map<String,Object> student = new HashMap<>();

            if (!document.select("table#details tr:eq(0) td:eq(1)").isEmpty()){
                String candidateName = 	document.select("table#details  tr:eq(0) td:eq(1)").first().text().trim();
                student.put("name",candidateName);
            }


            if (!document.select("table#details tr:eq(1)  td:eq(1)").isEmpty()){
                String registrationNo = document.select("table#details  tr:eq(1) td:eq(1)").first().text().trim();
                student.put("regNo",registrationNo);
            }



            if (!document.select("table:eq(1) tr:eq(1)").isEmpty()){
                String subject1 = document.select("table:eq(1) tr:eq(1) td:eq(0)").first().text().trim();
                String subject1Marks = document.select("table:eq(1) tr:eq(1) td:eq(1)").first().text().trim();
                student.put(subject1,subject1Marks);
            }


            if(!document.select("table:eq(1) tr:eq(2)").isEmpty()){
                String subject2 = document.select("table:eq(1) tr:eq(2) td:eq(0)").first().text().trim();
                String subject2Marks = document.select("table:eq(1) tr:eq(2) td:eq(1)").first().text().trim();
                student.put(subject2,subject2Marks);
            }


            if (!document.select("table:eq(1) tr:eq(3)").isEmpty()){
                String partATotalName = document.select("table:eq(1) tr:eq(3) td:eq(0)").first().text().trim();
                String partATotalMarks = document.select("table:eq(1) tr:eq(3) td:eq(1)").first().text().trim();
                student.put(partATotalName,partATotalMarks);
            }



            if (!document.select("table").eq(2).isEmpty()){
                Map<String,String> sub3 = new HashMap<>();
                String subject3 =  document.select("table").eq(2).select("tr:eq(1) td:eq(0)").first().text().trim();
                String subject3Theory =  document.select("table").eq(2).select("tr:eq(1) td:eq(1)").first().text().trim();
                String subject3Practical =  document.select("table").eq(2).select("tr:eq(1) td:eq(2)").first().text().trim();
                String subject3total  =  document.select("table").eq(2).select("tr:eq(1) td:eq(3)").first().text().trim();
                sub3.put("theory",subject3Theory);
                sub3.put("practical",subject3Practical);
                sub3.put("total",subject3total);
                student.put(subject3,sub3);
            }

            if (!document.select("table").eq(2).isEmpty()){
                Map<String,String> sub4 = new HashMap<>();
                String subject4 =  document.select("table").eq(2).select("tr:eq(2) td:eq(0)").first().text().trim();
                String subject4Theory =  document.select("table").eq(2).select("tr:eq(2) td:eq(1)").first().text().trim();
                String subject4Practical =  document.select("table").eq(2).select("tr:eq(2) td:eq(2)").first().text().trim();
                String subject4total  =  document.select("table").eq(2).select("tr:eq(2) td:eq(3)").first().text().trim();
                sub4.put("theory",subject4Theory);
                sub4.put("practical",subject4Practical);
                sub4.put("total",subject4total);
                student.put(subject4,sub4);
            }


            if (!document.select("table").eq(2).isEmpty()){
                Map<String,String> sub5 = new HashMap<>();
                String subject5 =  document.select("table").eq(2).select("tr:eq(3) td:eq(0)").first().text().trim();
                String subject5Theory =  document.select("table").eq(2).select("tr:eq(3) td:eq(1)").first().text().trim();
                String subject5Practical =  document.select("table").eq(2).select("tr:eq(3) td:eq(2)").first().text().trim();
                String subject5total  =  document.select("table").eq(2).select("tr:eq(3) td:eq(3)").first().text().trim();
                sub5.put("theory",subject5Theory);
                sub5.put("practical",subject5Practical);
                sub5.put("total",subject5total);
                student.put(subject5,sub5);
            }

            if (!document.select("table").eq(2).isEmpty()){
                Map<String,String> sub6 = new HashMap<>();
                String subject6 =  document.select("table").eq(2).select("tr:eq(4) td:eq(0)").first().text().trim();
                String subject6Theory =  document.select("table").eq(2).select("tr:eq(4) td:eq(1)").first().text().trim();
                String subject6Practical =  document.select("table").eq(2).select("tr:eq(4) td:eq(2)").first().text().trim();
                String subject6total  =  document.select("table").eq(2).select("tr:eq(4) td:eq(3)").first().text().trim();
                sub6.put("theory",subject6Theory);
                sub6.put("practical",subject6Practical);
                sub6.put("total",subject6total);
                student.put(subject6,sub6);
            }


            if (!document.select("table").eq(2).isEmpty()){
                String partBTotalName = document.select("table").eq(2).select("tr:eq(5) td:eq(0)").first().text().trim();
                String partBTotalMarks = document.select("table").eq(2).select("tr:eq(5) td:eq(1)").first().text().trim();
                student.put(partBTotalName,partBTotalMarks);
            }

            if (!document.select("table").eq(3).isEmpty()){
                String totalMaxName = document.select("table").eq(3).select("tr:eq(0) td:eq(0)").first().text().trim();
                String totalMaxMarks = document.select("table").eq(3).select("tr:eq(0) td:eq(1)").first().text().trim();
                student.put(totalMaxName,totalMaxMarks);

                String totalObtainedMarksName = document.select("table").eq(3).select("tr:eq(1) td:eq(0)").first().text().trim();
                String totalObtainedMarks = document.select("table").eq(3).select("tr:eq(1) td:eq(1)").first().text().trim();
                student.put(totalObtainedMarksName,totalObtainedMarks);

                String finalResultName = document.select("table").eq(3).select("tr:eq(2) td:eq(0)").first().text().trim();
                String finalResult = document.select("table").eq(3).select("tr:eq(2) td:eq(1)").first().text().trim();
                student.put(finalResultName,finalResult);
            }


            System.out.println(student);
		}
	}

}
