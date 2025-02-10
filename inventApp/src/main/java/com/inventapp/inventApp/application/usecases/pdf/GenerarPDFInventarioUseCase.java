package com.inventapp.inventApp.application.usecases.pdf;

                        import com.inventapp.inventApp.domain.models.write.Producto;
                        import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
                        import lombok.RequiredArgsConstructor;
                        import org.apache.pdfbox.pdmodel.PDDocument;
                        import org.apache.pdfbox.pdmodel.PDPage;
                        import org.apache.pdfbox.pdmodel.PDPageContentStream;
                        import org.apache.pdfbox.pdmodel.font.PDType1Font;
                        import org.springframework.stereotype.Service;

                        import java.io.ByteArrayOutputStream;
                        import java.util.List;
                        import java.util.UUID;

                        @Service
                        @RequiredArgsConstructor
                        public class GenerarPDFInventarioUseCase {

                            private final IProductoRepository productoRepository;

                            public byte[] ejecutar(String nombre) throws Exception {
                                List<Producto> productos = productoRepository.findByEmpresaNombre(nombre);
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                try (PDDocument document = new PDDocument()) {
                                    PDPage page = new PDPage();
                                    document.addPage(page);

                                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                                        contentStream.beginText();
                                        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                                        contentStream.newLineAtOffset(100, 700);
                                        contentStream.showText("Inventario de Productos");
                                        contentStream.endText();

                                        int yPosition = 650;
                                        for (Producto producto : productos) {
                                            contentStream.beginText();
                                            contentStream.setFont(PDType1Font.HELVETICA, 12);
                                            contentStream.newLineAtOffset(100, yPosition);
                                            contentStream.showText(producto.getNombre() + " - $" + producto.getPrecio());
                                            contentStream.endText();
                                            yPosition -= 20;
                                        }
                                    }

                                    document.save(outputStream);
                                }
                                return outputStream.toByteArray();
                            }
                        }