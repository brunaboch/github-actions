// ValidadorSenha.java
public class ValidadorSenha {
    public boolean validar(String s) {
        if (s == null || s.length() < 8)
            return false;
        boolean digito = s.chars().anyMatch(Character::isDigit);
        boolean maiuscula = s.chars().anyMatch(Character::isUpperCase);
        return digito && maiuscula;
    }
}