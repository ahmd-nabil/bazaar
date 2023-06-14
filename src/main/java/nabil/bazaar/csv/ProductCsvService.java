package nabil.bazaar.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import nabil.bazaar.domain.Category;
import nabil.bazaar.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ahmed Nabil
 */
@Service
public class ProductCsvService {

    public List<Product> getProductListFromCsv(String filename) throws FileNotFoundException {
        File csvFile = ResourceUtils.getFile("classpath:"+filename);
        List<ProductCSV> productCsvList = new CsvToBeanBuilder<ProductCSV>(new FileReader(csvFile)).withType(ProductCSV.class).build().parse();
        List<Product> products = new ArrayList<>();
        productCsvList.forEach(csv -> {
            Product p = Product.builder()
                    .name(csv.getName())
                    .description(csv.getDescription())
                    .unitPrice(csv.getUnitPrice())
                    .imageUrl(csv.getImageUrl())
                    .unitsInStock(csv.getUnitsInStock())
                    .active(csv.isActive())
                    .sku(csv.getSku())
                    .categories(Collections.singleton(Category.builder().name(csv.getCategory()).build()))
                    .build();
            products.add(p);
        });
        return products;
    }

}
