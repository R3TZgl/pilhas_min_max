# Pilha Mínima e Máxima

Implementação de uma pilha com operações `min()` e `max()` em **O(1)**,
utilizando pilhas auxiliares para rastrear o menor e o maior valor a cada
instante, sem varrer toda a estrutura.

## Pré-requisitos

| Ferramenta | Versão mínima |
|------------|--------------|
| Java (JDK) | 17           |
| Maven      | 3.8          |

## Como executar

```bash
# 1. Clone o repositório
git clone https://github.com/<usuario>/pilha-minima-maxima.git
cd pilha-minima-maxima

# 2. Compile e execute com Maven
mvn compile exec:java

# Alternativa — gerar o JAR e rodá-lo diretamente
mvn package
java -jar out/pilha-minima-maxima.jar
```

No IntelliJ IDEA:
1. **File → Open** → selecione a pasta `pilha-minima-maxima`
2. Aguarde o Maven indexar o projeto
3. Abra `Main.java` → clique no botão **▶ Run**

## Estrutura

```
pilha-minima-maxima/
├── pom.xml
├── README.md
└── src/main/java/br/com/pilha/
    ├── MinMaxStack.java   ← implementação da pilha
    └── Main.java          ← demonstração com saída formatada
```

## Complexidade

| Operação | Tempo | Espaço auxiliar |
|----------|-------|-----------------|
| push     | O(1)  | O(n) total      |
| pop      | O(1)  | —               |
| peek     | O(1)  | —               |
| min      | O(1)  | —               |
| max      | O(1)  | —               |
