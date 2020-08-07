package br.com.springbootexercicio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 2907060113341292344L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Length(min = 2, max = 30, message = "O tamanho da descrição deve ser entre {min} e {max} caracteres.")
	private String descricao;

}
