package com.example.shop.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.ForeignKey;

@Entity(name="importBill")
public class ImportBill {
    
    @Id
	@GeneratedValue
	private int importBillId;
	
	@NotNull
	private Date dateCreated = new Date((new java.util.Date()).getTime());
	
	
	  @ManyToOne
	  @JoinColumn( name = "supplierId", nullable = true, foreignKey
	  = @ForeignKey(name = "supplier_importBill")) private Supplier supplier;

	
	private int quantity;
	
	private double total;
	
	private String description;

    public ImportBill() {
    }

    public ImportBill(int importBillId, Date dateCreated, Supplier supplier, int quantity, double total, String description) {
        this.importBillId = importBillId;
        this.dateCreated = dateCreated;
        this.supplier = supplier;
        this.quantity = quantity;
        this.total = total;
        this.description = description;
    }

    public int getImportBillId() {
        return importBillId;
    }

    public void setImportBillId(int importBillId) {
        this.importBillId = importBillId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
