import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ValidadorSenhaTest {

    @Test
    void senhaComSeteCaracteresEhInvalida() {
        var validador = new ValidadorSenha();                  // Arrange
        boolean resultado = validador.validar("Abcde12");      // Act
        assertFalse(resultado);                                // Assert
    }

    @Test
    void senhaComOitoCaracteresValidosEhValida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("Abcdefg1");
        assertTrue(resultado);
    }

    @Test
    void senhaLongaValidaEhValida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("Abcdefgh12345");
        assertTrue(resultado);
    }

    @Test
    void senhaSemMaiusculaEhInvalida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("abcdefg1");
        assertFalse(resultado);
    }

    @Test
    void senhaSemDigitoEhInvalida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("Abcdefgh");
        assertFalse(resultado);
    }

    @Test
    void senhaSemDigitoENemMaiusculaEhInvalida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("abcdefgh");
        assertFalse(resultado);
    }

    @Test
    void senhaNulaEhInvalida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar(null);
        assertFalse(resultado);
    }

    @Test
    void senhaVaziaEhInvalida() {
        var validador = new ValidadorSenha();
        boolean resultado = validador.validar("");
        assertFalse(resultado);
    }
}
