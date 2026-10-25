# Validador de Senha

Uma senha é válida quando tem pelo menos **8 caracteres**, ao menos **1 dígito** e ao menos **1 letra maiúscula**.

Ferramentas: Java 21, JUnit 5, Maven e GitHub Actions.

## Como executar

```
mvn test
```

## 1. Casos de teste

| Categoria | Entrada | Esperado | Teste |
|---|---|---|---|
| Válido (limite inferior) | `Abcdefg1` (8 caracteres) | true | `senhaComOitoCaracteresValidosEhValida` |
| Válido | `Abcdefgh12345` | true | `senhaLongaValidaEhValida` |
| Valor-limite | `Abcde12` (7 caracteres) | false | `senhaComSeteCaracteresEhInvalida` |
| Inválido (sem maiúscula) | `abcdefg1` | false | `senhaSemMaiusculaEhInvalida` |
| Inválido (sem dígito) | `Abcdefgh` | false | `senhaSemDigitoEhInvalida` |
| Inválido (sem dígito e sem maiúscula) | `abcdefgh` | false | `senhaSemDigitoENemMaiusculaEhInvalida` |
| Inválido (nulo) | `null` | false | `senhaNulaEhInvalida` |
| Inválido (vazio) | `""` | false | `senhaVaziaEhInvalida` |

Todos os testes seguem o padrão **Arrange, Act, Assert**.

## 2. Defeitos inseridos

Cada defeito foi inserido isoladamente em `ValidadorSenha.java`, a suíte foi executada e a alteração foi revertida.

| # | Alteração | Teste(s) que falharam | Por que detectou |
|---|---|---|---|
| 1 | `s.length() < 8` → `s.length() <= 8` | `senhaComOitoCaracteresValidosEhValida` | O teste do valor-limite (8 caracteres) espera `true`, mas a senha de 8 caracteres passou a ser rejeitada. |
| 2 | `isUpperCase` → `isLowerCase` | `senhaSemMaiusculaEhInvalida` | `abcdefg1` passou a ser aceita, pois só tem minúsculas. As senhas válidas continuaram passando porque também contêm minúsculas. |
| 3 | `&&` → `\|\|` | `senhaSemDigitoEhInvalida`, `senhaSemMaiusculaEhInvalida` | Bastar um dos requisitos fez senhas com apenas um deles serem aceitas. |
| 4 | Remoção de `s == null \|\|` | `senhaNulaEhInvalida` (erro) | Gerou `NullPointerException`: aparece como *erro*, não como *falha* de asserção. |


## 3. GitHub Actions

O workflow `.github/workflows/tests.yml` roda `mvn -B test` a cada push e pull request, usando JDK 21 em `ubuntu-latest`.

**Execução com defeito (branch `defeito-teste`): falha**

![Falha](docs/falha.png)

**Execução na `main`: sucesso**

![Sucesso](docs/sucesso.png)