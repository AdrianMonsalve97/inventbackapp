package com.inventapp.inventApp.application.usecases.pdf;

import com.inventapp.inventApp.domain.dtos.producto.InventarioProductoDTO;
import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenerarPDFInventarioUseCase {

    private final IProductoRepository productoRepository;

    public byte[] ejecutar(String nombre) throws Exception {
        List<InventarioProductoDTO> productos = productoRepository.findByEmpresaNombre(nombre).stream()
                .map(producto -> InventarioProductoDTO.builder()
                        .codigo(producto.getCodigo())
                        .nombre(producto.getNombre())
                        .caracteristicas(producto.getCaracteristicas() != null ? producto.getCaracteristicas() : "N/A")
                        .precio(producto.getPrecio())
                        .moneda(producto.getMoneda())
                        .cantidad(producto.getCantidad())
                        .empresaNit(producto.getEmpresa().getNit())
                        .activo(producto.isActivo())
                        .categorias(producto.getCategorias().stream().map(Categoria::getNombre).collect(Collectors.toSet()))
                        .build()
                ).collect(Collectors.toList());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.beginText();
                contentStream.newLineAtOffset(200, 750);
                contentStream.showText("Inventario de Productos");
                contentStream.endText();

                // Posiciones iniciales
                int margin = 50;
                int yPosition = 700;
                int rowHeight = 20;
                int colWidth = 100;

                // Encabezados de tabla
                String[] headers = {"Código", "Nombre", "Características", "Precio", "Moneda", "Cantidad"};

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.setLineWidth(1f);
                contentStream.moveTo(margin, yPosition);
                contentStream.lineTo(margin + (colWidth * headers.length), yPosition);
                contentStream.stroke();

                yPosition -= rowHeight;

                for (String header : headers) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(header);
                    contentStream.endText();
                    margin += colWidth;
                }

                // Dibujar los datos de los productos
                margin = 50;
                yPosition -= rowHeight;
                contentStream.setFont(PDType1Font.HELVETICA, 10);

                for (InventarioProductoDTO producto : productos) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(producto.getCodigo());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + colWidth, yPosition);
                    contentStream.showText(producto.getNombre());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + (colWidth * 2), yPosition);
                    contentStream.showText(producto.getCaracteristicas());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + (colWidth * 3), yPosition);
                    contentStream.showText("$" + producto.getPrecio());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + (colWidth * 4), yPosition);
                    contentStream.showText(producto.getMoneda());
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + (colWidth * 5), yPosition);
                    contentStream.showText(String.valueOf(producto.getCantidad()));
                    contentStream.endText();

                    yPosition -= rowHeight;
                    if (yPosition < 50) break;
                }
            }

            document.save(outputStream);
        }
        return outputStream.toByteArray();
    }
}
