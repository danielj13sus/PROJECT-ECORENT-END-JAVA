package model.enums;

import org.springframework.stereotype.Component;

@Component

public enum RentStatus {

    RESERVED("Reservado"),
    IN_PROGRESS("Em processo"),
    FINISHED("Finalizado"),
    CANCELED("Cancelado");

    private final String descricao;

    RentStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
