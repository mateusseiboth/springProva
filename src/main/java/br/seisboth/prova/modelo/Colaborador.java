package br.seisboth.prova.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Colaborador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome deve ser informado")
	private String nome;
	
	@NotBlank(message = "Função deve ser informada")
	private String funcao;
	
	@OneToOne(mappedBy = "colaborador", cascade = CascadeType.ALL)
    private Workstation workstation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Workstation getWorkstation() {
		return workstation;
	}

	public void setWorkstation(Workstation workstation) {
		this.workstation = workstation;
	}
	
	
}
