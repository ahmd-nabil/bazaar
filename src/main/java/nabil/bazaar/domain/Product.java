package nabil.bazaar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class Product {
    // used in Lombok Builder
    public Product(Long id,
                   String name,
                   String description,
                   BigDecimal unitPrice,
                   String imageUrl,
                   Integer unitsInStock,
                   boolean active,
                   String sku,
                   LocalDateTime createdDate,
                   LocalDateTime lastUpdated,
                   Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.unitsInStock = unitsInStock;
        this.active = active;
        this.sku = sku;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
        setCategories(categories);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "units_in_stock", nullable = false)
    private Integer unitsInStock;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "sku")
    private String sku;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Builder.Default
    @ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        category.getProducts().add(this);
        this.getCategories().add(category);
    }

    public void removeCategory(Category category) {
        category.getProducts().remove(this);
        this.getCategories().remove(category);
    }

    public void setCategories(Set<Category> categories) {
        this.categories = new HashSet<>();
        categories.forEach(this::addCategory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
