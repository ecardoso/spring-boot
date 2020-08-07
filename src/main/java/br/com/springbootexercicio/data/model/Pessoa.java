package br.com.springbootexercicio.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Pessoa extends ResourceSupport implements Serializable {

	private static final long serialVersionUID = -4302595679036557785L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "PESSOA_ID", sequenceName = "PESSOA_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_ID")
	private Long key;
	private String nome;
	private String sobreNome;
	private String email;

}
