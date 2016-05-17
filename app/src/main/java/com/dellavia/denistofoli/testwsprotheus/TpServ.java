package com.dellavia.denistofoli.testwsprotheus;

/**
 * Created by denis.tofoli on 27/04/2016.
 */
public class TpServ {
    private String codigo;
    private String descricao;

    public String getCodigo() { return codigo; }

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString(){
        return getCodigo() + "-" + getDescricao();
    }
}
