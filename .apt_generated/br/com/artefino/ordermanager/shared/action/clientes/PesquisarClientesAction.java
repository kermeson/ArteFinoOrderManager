package br.com.artefino.ordermanager.shared.action.clientes;

import com.gwtplatform.dispatch.shared.Action;

public class PesquisarClientesAction implements Action<PesquisarClientesResult> { 

  int maxResults;
  int firstResult;

  public PesquisarClientesAction(int maxResults, int firstResult) {
    this.maxResults = maxResults;
    this.firstResult = firstResult;
  }

  protected PesquisarClientesAction() {
    // Possibly for serialization.
  }

  public int getMaxResults() {
    return maxResults;
  }

  public int getFirstResult() {
    return firstResult;
  }

  @Override
  public String getServiceName() {
    return "dispatch/";
  }

  @Override
  public boolean isSecured() {
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PesquisarClientesAction other = (PesquisarClientesAction) obj;
    if (maxResults != other.maxResults)
        return false;
    if (firstResult != other.firstResult)
        return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + new Integer(maxResults).hashCode();
    hashCode = (hashCode * 37) + new Integer(firstResult).hashCode();
    return hashCode;
  }

  @Override
  public String toString() {
    return "PesquisarClientesAction["
                 + maxResults
                 + ","
                 + firstResult
    + "]";
  }
}
