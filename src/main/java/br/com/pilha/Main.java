package br.com.pilha;

/**
 * Demonstração da MinMaxStack.
 *
 * Cada operação é seguida de uma linha mostrando o estado atual da pilha
 * e os valores de min() e max() vigentes.
 */
public class Main {

    // Largura do separador visual
    private static final String SEP = "─".repeat(65);

    public static void main(String[] args) {

        System.out.println("╔" + "═".repeat(63) + "╗");
        System.out.println("║         DEMONSTRAÇÃO — PILHA MÍNIMA E MÁXIMA              ║");
        System.out.println("╚" + "═".repeat(63) + "╝");

        MinMaxStack<Integer> pilha = new MinMaxStack<>();

        // ----------------------------------------------------------------
        // Bloco 1 — Inserções básicas
        // ----------------------------------------------------------------
        cabecalho("BLOCO 1 — Inserções básicas");

        push(pilha, 10);
        push(pilha, 3);
        push(pilha, 7);
        push(pilha, 1);
        push(pilha, 9);

        // ----------------------------------------------------------------
        // Bloco 2 — Remoções e recuperação de mínimo/máximo anterior
        // ----------------------------------------------------------------
        cabecalho("BLOCO 2 — Remoções (testando recuperação de min/max)");

        pop(pilha); // Remove 9 (topo atual — max continua 10)
        pop(pilha); // Remove 1 (era o mínimo — min volta para 3)

        // ----------------------------------------------------------------
        // Bloco 3 — Duplicatas
        // ----------------------------------------------------------------
        cabecalho("BLOCO 3 — Inserindo duplicatas");

        push(pilha, 3);  // Duplicata do valor 3 já existente
        push(pilha, 3);  // Mais uma cópia
        push(pilha, 7);

        pop(pilha);  // Remove 7
        pop(pilha);  // Remove 3 (duplicata)
        pop(pilha);  // Remove 3 (duplicata) — min ainda deve ser correto

        // ----------------------------------------------------------------
        // Bloco 4 — Valores extremos e verificação final
        // ----------------------------------------------------------------
        cabecalho("BLOCO 4 — Valores extremos e recuperação de min/max");

        MinMaxStack<Integer> p2 = new MinMaxStack<>();
        push(p2, 5);
        push(p2, 2);
        push(p2, 8);
        push(p2, 2);  // duplicata do mínimo atual
        push(p2, 1);  // novo mínimo absoluto

        System.out.println();
        System.out.println("  Removendo o topo (1 = mínimo): min deve subir para 2");
        pop(p2);       // Remove 1 → min deve ser 2 novamente

        System.out.println();
        System.out.println("  Removendo o topo (2 = duplicata do mínimo): min ainda 2");
        pop(p2);       // Remove duplicata 2 → min deve continuar 2

        System.out.println();
        System.out.println("  Removendo o topo (8 = máximo): max deve cair para 5");
        pop(p2);       // Remove 8 → max deve cair para 5

        // ----------------------------------------------------------------
        // Bloco 5 — Pilha vazia
        // ----------------------------------------------------------------
        cabecalho("BLOCO 5 — Testando exceção em pilha vazia");
        MinMaxStack<Integer> p3 = new MinMaxStack<>();
        testarExcecao(p3);

        System.out.println();
        System.out.println(SEP);
        System.out.println("  Demonstração concluída com sucesso.");
        System.out.println(SEP);
    }

    // ----------------------------------------------------------------
    // Helpers de exibição
    // ----------------------------------------------------------------

    private static void push(MinMaxStack<Integer> p, int valor) {
        p.push(valor);
        System.out.printf("  PUSH %-5d | Pilha: %-35s | Min: %-5d | Max: %d%n",
                valor, p.toString(), p.min(), p.max());
    }

    private static void pop(MinMaxStack<Integer> p) {
        int removido = p.pop();
        String minStr = p.isEmpty() ? "  —  " : String.valueOf(p.min());
        String maxStr = p.isEmpty() ? "  —  " : String.valueOf(p.max());
        System.out.printf("  POP  %-5d | Pilha: %-35s | Min: %-5s | Max: %s%n",
                removido, p.toString(), minStr, maxStr);
    }

    private static void cabecalho(String titulo) {
        System.out.println();
        System.out.println(SEP);
        System.out.println("  " + titulo);
        System.out.println(SEP);
    }

    private static void testarExcecao(MinMaxStack<Integer> p) {
        System.out.println();
        try {
            p.pop();
        } catch (java.util.EmptyStackException e) {
            System.out.println("  pop() em pilha vazia    → EmptyStackException capturada corretamente.");
        }
        try {
            p.min();
        } catch (java.util.EmptyStackException e) {
            System.out.println("  min() em pilha vazia    → EmptyStackException capturada corretamente.");
        }
        try {
            p.max();
        } catch (java.util.EmptyStackException e) {
            System.out.println("  max() em pilha vazia    → EmptyStackException capturada corretamente.");
        }
    }
}
