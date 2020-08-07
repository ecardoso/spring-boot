package br.com.springbootexercicio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;

@Data
@Entity
public class Despesa implements Serializable {

	private static final long serialVersionUID = -7687301368488444112L;

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;

	@NotNull
	@Length(min = 2, max = 30, message = "O tamanho da descrição deve ser entre {min} e {max} caracteres.")
	private String descricao;

	private String parcela;

	private String observacao;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;

	@Digits(integer = 8, fraction = 2)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal valor;
}
