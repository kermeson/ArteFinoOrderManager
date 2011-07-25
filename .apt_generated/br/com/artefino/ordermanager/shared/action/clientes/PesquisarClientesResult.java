package br.com.artefino.ordermanager.shared.action.clientes;

import com.gwtplatform.dispatch.shared.Result;

public class PesquisarClientesResult implements Result { 

  java.util.List<br.com.artefino.ordermanager.shared.vo.ClienteVo> clientes;

  public PesquisarClientesResult(java.util.List<br.com.artefino.ordermanager.shared.vo.ClienteVo> clientes) {
    this.clientes = clientes;
  }

  protected PesquisarClientesResult() {
    // Possibly for serialization.
  }

  public java.util.List<br.com.artefino.ordermanager.shared.vo.ClienteVo> getClientes() {
    return clientes;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PesquisarClientesResult other = (PesquisarClientesResult) obj;
    if (clientes == null) {
      if (other.clientes != null)
        return false;
    } else if (!clientes.equals(other.clientes))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (clientes == null ? 1 : clientes.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PesquisarClientesResult["
                 + clientes
    + "]";
  }
}
