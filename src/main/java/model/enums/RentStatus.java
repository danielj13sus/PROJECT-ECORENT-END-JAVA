package model.enums;

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
