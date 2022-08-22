package com.galavec.findstrings.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galavec.findstrings.entity.RequestService;
import com.galavec.findstrings.entity.ResponseService;
import com.galavec.findstrings.report.ReportLogs;

@RestController
@RequestMapping(value = "/findStrings")
public class PrincipalController {

	@Autowired
	ReportLogs reportLogs;

	@PostMapping(value = "/willExist")
	public ResponseService report(@RequestBody @Valid RequestService request)
			throws FileNotFoundException, IOException {
		return reportLogs.elaboracionReporte(request);
	}

}
