package br.com.gimenez.avaliacao.uteis;

import org.springframework.util.StringUtils;

/**
 * Classe utilizada para armazenar os validadores da aplicação
 */
public class Validadores {

    public static boolean validarCpf(String documento) {
        if ( StringUtils.isEmpty(documento) || documento.length() != 11)
            return false;


        if ( verificarRepeticao(documento) )
            return false;

        int soma1 = 0,
                soma2 = 0,
                val = 11;

        for ( var i = 0; i < 9; i++ ) {
            soma1 += Integer.parseInt(documento.substring(i, i + 1)) * ( val - 1 );
            soma2 += Integer.parseInt(documento.substring(i, i + 1)) * val;
            val--;
        }

        soma1 = (((soma1 * 10) % 11) == 10 ? 0 : ((soma1 * 10) % 11));
        soma2 = (((soma2 + (2 * soma1)) * 10) % 11);

        soma2 = soma2 >= 10 ? 0 : soma2;

        if ( Integer.parseInt(documento.substring(9, 10)) == soma1 && Integer.parseInt(documento.substring(10, 11)) == soma2 )
            return true;
        else
            return false;
    }

    private static boolean verificarRepeticao(String conteudo) {
        var caracterAntes = conteudo.charAt(0);
        var invalido = true;

        for (int i = 0;i < conteudo.length();i++) {
            if (caracterAntes != conteudo.charAt(i)) {
                invalido = false;
                break;
            }
            caracterAntes = conteudo.charAt(i);
        }

        return invalido;
    }

}
