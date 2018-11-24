/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provapedro2111.model;

import java.util.Date;

/**
 *
 * @author fag
 */
public class Motorista extends Pessoa {
    
    private Funcionario funcionario;
    private String cnh;
    private Date dtVencimento;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    @Override
    public String toString() {
        return  Motorista.super.getId() + " - " + funcionario.getNome() + " - " + cnh;
    }
    
    
    
}
