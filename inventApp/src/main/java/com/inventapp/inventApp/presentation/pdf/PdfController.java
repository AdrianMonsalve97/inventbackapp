package com.inventapp.inventApp.presentation.pdf;

import com.inventapp.inventApp.application.usecases.pdf.GenerarPDFInventarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {

    private final GenerarPDFInventarioUseCase generarPDFInventarioUseCase;

    @GetMapping("/inventario/{nombre}")
    public ResponseEntity<byte[]> descargarPDF(@PathVariable String nombre) {
        try {
            byte[] pdfBytes = generarPDFInventarioUseCase.ejecutar(nombre);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=inventario.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}