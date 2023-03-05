package com.example.stech.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.stech.model.Address;
import com.example.stech.repository.AddressRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JReportService {
 
    @Autowired
    private AddressRepository repository;
 
 
    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
    	//String path = "C:\\Users\\Admin\\Downloads";
        List<Address> address = repository.findAll();
        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:Address_01.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Simplifying Tech");
        //Fill report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
         
        //JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Address21.pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
        //System.out.println("PDF Generated in path : " + path);
    }
}
