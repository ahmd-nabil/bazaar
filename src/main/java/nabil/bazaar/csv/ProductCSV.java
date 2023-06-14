package nabil.bazaar.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Ahmed Nabil
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCSV {

    @CsvBindByName(required = true)
    private String name;

    @CsvBindByName(required = true)
    private String description;

    @CsvBindByName(required = true)
    private BigDecimal unitPrice;

    @CsvBindByName(required = true)
    private String imageUrl;

    @CsvBindByName(required = true)
    private Integer unitsInStock;

    @CsvBindByName(required = true)
    private boolean active;

    @CsvBindByName(required = true)
    private String sku;

    @CsvBindByName(required = true)
    private String category;
}
