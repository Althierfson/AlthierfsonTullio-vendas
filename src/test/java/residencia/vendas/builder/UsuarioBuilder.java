package residencia.vendas.builder;

import residencia.vendas.entidade.Usuario;

public class UsuarioBuilder {
    

    public static Usuario usuarioBuilder() {
        return new Usuario(
            "Usuario do sistema",
            "usuario@email.com",
            "Endereco do usuario. 145"
        );
    }
}
