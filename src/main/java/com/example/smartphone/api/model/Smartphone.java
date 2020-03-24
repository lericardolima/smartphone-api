package com.example.smartphone.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.smartphone.api.enumeration.ColorEnum;

/**
 * Representação do recurso celular.
 * 
 * @author Ricardo Rocha
 * @version 1.0.0
 */
@Entity
@Table(name = "smartphone", uniqueConstraints = @UniqueConstraint(name = "code_unique_key", columnNames = "code"))
public class Smartphone {

	/**
	 * Chave primária do recurso.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * Código de identificação do celular, composto por uma sequência alfanúmerica
	 * única de 8 dígitos.
	 */
	@Column(name = "code", length = 8, nullable = false)
	private String code;

	/**
	 * Modelo do celular.
	 */
	@Column(name = "model", nullable = false)
	private String model;

	/**
	 * Preço do celular.
	 */
	@Column(name = "price", precision = 2, nullable = false)
	private Float price;

	/**
	 * Marca do celular.
	 */
	@Column(name = "brand", nullable = false)
	private String brand;

	/**
	 * URL da imagem do celular.
	 */
	@Column(name = "photo_url", nullable = false)
	private String photo;

	/**
	 * Data de início da venda do celular.
	 */
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	/**
	 * Data final da venda do celular.
	 */
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	/**
	 * Cor do celular.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "color", nullable = false)
	private ColorEnum color;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ColorEnum getColor() {
		return color;
	}

	public void setColor(ColorEnum color) {
		this.color = color;
	}
}
