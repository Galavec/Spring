package com.galavec.findstrings.services.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Service;

import com.galavec.findstrings.dto.RegistrosEncontrados;
import com.galavec.findstrings.dto.RegistrosNoEncontrados;
import com.galavec.findstrings.dto.RegistrosRepetidos;
import com.galavec.findstrings.dto.RequestFindStrings;
import com.galavec.findstrings.dto.ResponseWillExist;
import com.galavec.findstrings.services.SearchResultsService;

@Service
public class SearchResultsServiceImpl implements SearchResultsService {
	static final Logger logger = LogManager.getLogger(SearchResultsServiceImpl.class.getName());

	private List<String> lRepeatedText;
	private List<String> lNonRepeatingText;
	private List<String> lAddFoundText;

	private void initialize() {
		this.lRepeatedText = new ArrayList<>();
		this.lNonRepeatingText = new ArrayList<>();
		this.lAddFoundText = new ArrayList<>();
	}

	@Override
	public ResponseWillExist groupResults(RequestFindStrings requestFindStrings) {

		var sThreadID = UUID.randomUUID().toString();
		ThreadContext.put("sid", sThreadID);

		logger.info("------------------------------------------------------------");
		logger.info("Start in SearchResults.groupResults.");
		logger.info("Received: {}", requestFindStrings);

		initialize();

		ResponseWillExist responseWillExist;
		var registrosEncontrados = new RegistrosEncontrados();
		var registrosNoEncontrados = new RegistrosNoEncontrados();
		var registrosRepetidos = new RegistrosRepetidos();

		String[] aNumerosOrdenesRequest;

		List<String> lsTextoNoEncontrado = new ArrayList<>();

		aNumerosOrdenesRequest = requestFindStrings.getOrdenesId().split(",");

		for (String dataToSearch : aNumerosOrdenesRequest) {
			verifyAndStoreNonRepeatingData(dataToSearch);
		}

		responseWillExist = readFile(requestFindStrings.getFileWhereToLook(), requestFindStrings.getLineaAEvitar());

		if (responseWillExist != null)
			return responseWillExist;

		lsTextoNoEncontrado.addAll(this.lNonRepeatingText);

		if (!(this.lAddFoundText.isEmpty())) {
			lsTextoNoEncontrado.clear();

			for (String nonRepeatingText : this.lNonRepeatingText) {
				if (!(this.lAddFoundText.contains(nonRepeatingText))) {
					lsTextoNoEncontrado.add(nonRepeatingText);
				}
			}
		}

		registrosEncontrados.setCantidadEncontrada(lAddFoundText.size());
		registrosEncontrados.setTextoEncontrado(this.lAddFoundText.toArray(new String[0]));

		registrosNoEncontrados.setCantidadNoEncontrada(this.lNonRepeatingText.size());
		registrosNoEncontrados.setTextoNoEncontrado(lsTextoNoEncontrado.toArray(new String[0]));

		registrosRepetidos.setCantidadTextoRepetido(this.lRepeatedText.size());
		registrosRepetidos.setTextoRepetido(this.lRepeatedText.toArray(new String[0]));

		logger.info("Ends in SearchResults.groupResults.");

		return new ResponseWillExist(1, "Successful", registrosEncontrados, registrosNoEncontrados, registrosRepetidos);
	}

	private void storeRepeatedData(String data) {
		if (!(this.lRepeatedText.contains(data) || data.equals("")))
			this.lRepeatedText.add(data);
	}

	private void verifyAndStoreNonRepeatingData(String data) {
		if (!(this.lNonRepeatingText.contains(data) || data.equals("")))
			this.lNonRepeatingText.add(data);
		else
			storeRepeatedData(data);
	}

	private ResponseWillExist readFile(String fileWhereToLook, String contentInlineToAvoid) {
		String sLineContent;

		FileReader frObtenerArchivo = null;

		try {
			frObtenerArchivo = new FileReader(fileWhereToLook);
		} catch (FileNotFoundException e) {
			logger.error("File not found error: ", e);
			return errorInProcess("File not found.");
		}

		try (var brFile = new BufferedReader(frObtenerArchivo)) {
			while ((sLineContent = brFile.readLine()) != null) {
				for (String nonRepeatingText : this.lNonRepeatingText) {
					if ((contentInlineToAvoid.equals("") || !(sLineContent.contains(contentInlineToAvoid)))
							&& sLineContent.contains(nonRepeatingText)) {
						storeFoundText(nonRepeatingText);
					}
				}
			}

			return null;
		} catch (IOException e) {
			logger.error("Error reading lines from file: ", e);
			return errorInProcess("Error reading lines from file.");
		}
	}

	private void storeFoundText(String data) {
		if (!(this.lAddFoundText.contains(data) || data.equals("")))
			this.lAddFoundText.add(data);
	}

	private ResponseWillExist errorInProcess(String message) {
		return new ResponseWillExist(0, message);
	}

}
