package dtos;

public record AddressDTO(String cep, String logradouro, String complemento, String bairro, String localidade,
                         String uf, String estado, String regiao, String ddd) {
}
