package shop.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import shop.exception.CatalogLoadException;
import shop.model.PhysicalProduct;
import shop.model.Product;

/** Reads products from a semicolon-separated file: sku;name;price;weightGrams. */
public class CatalogReader {
    private static final Logger LOG = Logger.getLogger("shop.io.CatalogReader");

    private int skippedCount;

    /** Number of broken lines skipped by the last call to load(). */
    public int getSkippedCount() { return skippedCount; }

    public List<Product> load(Path file) throws CatalogLoadException {
        List<Product> products = new ArrayList<>();
        skippedCount = 0;
        try (BufferedReader reader = Files.newBufferedReader(file)) { // closed automatically
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) {
                    continue;
                }
                try {
                    products.add(parse(line));
                } catch (IllegalArgumentException e) { // also covers NumberFormatException
                    skippedCount++;
                    LOG.warning("Baris " + lineNumber + " dilewati: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CatalogLoadException("Katalog gagal dibaca: " + file, e);
        }
        return products;
    }

    private static Product parse(String line) {
        String[] parts = line.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("expected 4 fields, found " + parts.length);
        }
        long price = Long.parseLong(parts[2].trim());
        int weight = Integer.parseInt(parts[3].trim());
        return new PhysicalProduct(parts[0].trim(), parts[1].trim(), price, weight);
    }
}
