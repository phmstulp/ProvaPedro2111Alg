/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provapedro2111.model;

/**
 *
 * @author fag
 */
public class Funcionario extends Pessoa {
    
    private int matricula;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return Funcionario.super.getId() + " - " + Funcionario.super.getNome() + " - " + matricula;
    }
    
    
    
}
