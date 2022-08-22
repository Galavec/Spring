package com.galavec.findstrings.report;

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

import com.galavec.findstrings.entity.RegistrosEncontrados;
import com.galavec.findstrings.entity.RegistrosNoEncontrados;
import com.galavec.findstrings.entity.RegistrosRepetidos;
import com.galavec.findstrings.entity.RequestService;
import com.galavec.findstrings.entity.ResponseService;

@Service
public class ReportLogs {
	static final Logger logger = LogManager.getLogger(ReportLogs.class.getName());

	public ResponseService elaboracionReporte(RequestService psRequestService)
			throws FileNotFoundException, IOException {

		String sThreadID = UUID.randomUUID().toString();
		ThreadContext.put("sid", sThreadID);

		logger.info("------------------------------------------------------------");
		logger.info("Inicio de servicio para elaboracion de reporte.");

		logger.info("Se recibe: " + psRequestService);

		ResponseService responseService = new ResponseService();
		RegistrosEncontrados registrosEncontrados = new RegistrosEncontrados();
		RegistrosNoEncontrados registrosNoEncontrados = new RegistrosNoEncontrados();
		RegistrosRepetidos registrosRepetidos = new RegistrosRepetidos();

		String sNumerosOrdenes = "";
		String sCadenaArchivo = "";
		String sRutaDelArchivo = "";

		String[] aNumerosOrdenesRequest;
		String[] aAuxNumerosOrdenesRequest;

		List<String> lsTextoEncontrado = new ArrayList<String>();
		List<String> lsTextoNoEncontrado = new ArrayList<String>();
		List<String> lsTextoRepetido = new ArrayList<String>();
		List<String> lsNumerosOrdenes = new ArrayList<String>();

		int iCantRegistrosRequest = 0;
		int iCantidadTextoEncontrado = 0;
		int iCantidadTextoNoEncontrado = 0;
		int iContRegRequestRepetidos = 0;
		int iCantRegistros = 0;

		sNumerosOrdenes = psRequestService.getOrdenesId();

		aNumerosOrdenesRequest = sNumerosOrdenes.split(",");

		aAuxNumerosOrdenesRequest = aNumerosOrdenesRequest;

		iCantRegistrosRequest = aNumerosOrdenesRequest.length;

		// INICIALIZO ARRAY
		// aNumerosOrdenes = new String[iCantRegistrosRequest];

		// APARTO REGISTROS REPETIDOS EN UNA LISTA, ADICIONAL CREO UN ARREGLO DONDE
		// NINGUN REGISTRO SE REPITA.
		for (int i = 0; i < iCantRegistrosRequest; i++) {
			iContRegRequestRepetidos = 0;

			for (int j = 0; j < iCantRegistrosRequest; j++) {
				if (aNumerosOrdenesRequest[i].equals(aAuxNumerosOrdenesRequest[j])) {
					iContRegRequestRepetidos = iContRegRequestRepetidos + 1;

					if (lsNumerosOrdenes.contains(aAuxNumerosOrdenesRequest[j])) {
						if (!(lsTextoRepetido.contains(aAuxNumerosOrdenesRequest[j]))) {
							lsTextoRepetido.add(aAuxNumerosOrdenesRequest[j]);
						}
					} else {
						lsNumerosOrdenes.add(aAuxNumerosOrdenesRequest[j]);
					}
				}
			}
		}

		iCantRegistros = lsNumerosOrdenes.size();

		sRutaDelArchivo = "D:/Proyectos/Spring/wsFindStringsInFlatFiles/config/" + psRequestService.getNombreLog();

		FileReader frObtenerArchivo = null;

		frObtenerArchivo = new FileReader(sRutaDelArchivo);

		BufferedReader brLeerArchivo = new BufferedReader(frObtenerArchivo);

		logger.info("Se imprimira las ordenes encontradas en el log.");

		while ((sCadenaArchivo = brLeerArchivo.readLine()) != null) {
			for (int i = 0; i < iCantRegistros; i++) {
				if (psRequestService.getLineaAEvitar().equals("")
						|| !(sCadenaArchivo.contains(psRequestService.getLineaAEvitar()))) {
					if (sCadenaArchivo.contains(lsNumerosOrdenes.get(i))) {
						if (!(lsTextoEncontrado.contains(lsNumerosOrdenes.get(i)))) {
							lsTextoEncontrado.add(lsNumerosOrdenes.get(i));

							iCantidadTextoEncontrado = iCantidadTextoEncontrado + 1;
						}
					}
				}
			}
		}

		brLeerArchivo.close();

		if ((lsTextoEncontrado != null) && (lsTextoEncontrado.size() != 0)) {
			for (int i = 0; i < iCantRegistros; i++) {
				if (!(lsTextoEncontrado.contains(lsNumerosOrdenes.get(i)))) {
					if (!(lsTextoNoEncontrado.contains(lsNumerosOrdenes.get(i)))) {
						lsTextoNoEncontrado.add(lsNumerosOrdenes.get(i));

						iCantidadTextoNoEncontrado = iCantidadTextoNoEncontrado + 1;
					}
				}
			}
		} else {
			lsTextoNoEncontrado.addAll(lsNumerosOrdenes);
			iCantidadTextoNoEncontrado = lsNumerosOrdenes.size();
		}

		registrosEncontrados.setCantidadEncontrada(iCantidadTextoEncontrado);
		registrosEncontrados.setTextoEncontrado(lsTextoEncontrado.toArray(new String[0]));

		registrosNoEncontrados.setCantidadNoEncontrada(iCantidadTextoNoEncontrado);
		registrosNoEncontrados.setTextoNoEncontrado(lsTextoNoEncontrado.toArray(new String[0]));

		registrosRepetidos.setCantidadTextoRepetido(lsTextoRepetido.size());
		registrosRepetidos.setTextoRepetido(lsTextoRepetido.toArray(new String[0]));

		responseService.setRegistrosEncontrados(registrosEncontrados);
		responseService.setRegistrosNoEncontrados(registrosNoEncontrados);
		responseService.setRegistrosRepetidos(registrosRepetidos);

		logger.info("responseService: " + responseService);

		return responseService;
	}
}
