package com.ledgerx.goods.entity;

import com.ledger.core.entity.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Goods entity representing physical items like oil, meat, etc.
 * 
 * <p>This entity represents the actual goods that can be purchased,
 * distinct from products which are plans or configurations.</p>
 */
@Entity
@Table(name = "goods")
public class Goods extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Goods name is required")
    @Size(max = 255, message = "Goods name must not exceed 255 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 50, message = "Category must not exceed 50 characters")
    @Column(name = "category", length = 50)
    private String category;

    @Size(max = 20, message = "Unit must not exceed 20 characters")
    @Column(name = "unit", length = 20)
    private String unit; // e.g., "kg", "liter", "piece", "box"

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    /**
     * Status enumeration for goods
     */
    public enum Status {
        ACTIVE,
        INACTIVE,
        DISCONTINUED
    }

    // Constructors
    public Goods() {
    }

    public Goods(String name, String description, String category, String unit) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.unit = unit;
        this.status = Status.ACTIVE;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", unit='" + unit + '\'' +
                ", status=" + status +
                '}';
    }
}
