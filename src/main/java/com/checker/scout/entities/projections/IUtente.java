package com.checker.scout.entities.projections;

public interface IUtente {
    public interface UtenteP{
            Long getId();
            String getNome();
            String getCognome();
            String getIndirizzo();
            String getTelefono();
            String getEmail();
        }
}
