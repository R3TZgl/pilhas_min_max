package br.com.pilha;

import java.util.EmptyStackException;

/**
 * Pilha com operações min() e max() em O(1).
 *
 * Estratégia: duas pilhas auxiliares (minStack e maxStack) acompanham
 * sincronizadamente a pilha principal. A cada push, empilhamos na pilha
 * auxiliar o menor (ou maior) valor corrente. A cada pop, desempilhamos
 * da auxiliar também, restaurando o mínimo/máximo anterior sem precisar
 * percorrer a pilha — garantindo O(1) para min() e max().
 *
 * Tratamento de duplicatas: usamos <= e >= nas comparações, garantindo
 * que valores repetidos sejam corretamente rastreados mesmo após remoções.
 *
 * @param <T> Tipo numérico comparável (Integer, Double, etc.)
 */
public class MinMaxStack<T extends Comparable<T>> {

    // ----- Nó interno da lista encadeada -----
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next  = next;
        }
    }

    // Pilha principal
    private Node<T> top;

    // Pilha auxiliar de mínimos
    private Node<T> minTop;

    // Pilha auxiliar de máximos
    private Node<T> maxTop;

    // Tamanho atual
    private int size;

    // --------------------------------------------------------
    // push — O(1)
    // --------------------------------------------------------

    /**
     * Empilha um valor.
     * Atualiza minStack e maxStack com o novo mínimo/máximo corrente.
     */
    public void push(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor nulo não é permitido.");
        }

        // Empilha na pilha principal
        top = new Node<>(value, top);
        size++;

        // Atualiza pilha de mínimos
        if (minTop == null || value.compareTo(minTop.value) <= 0) {
            minTop = new Node<>(value, minTop);
        } else {
            // Repete o mínimo atual para manter sincronia com a pilha principal
            minTop = new Node<>(minTop.value, minTop);
        }

        // Atualiza pilha de máximos
        if (maxTop == null || value.compareTo(maxTop.value) >= 0) {
            maxTop = new Node<>(value, maxTop);
        } else {
            // Repete o máximo atual para manter sincronia com a pilha principal
            maxTop = new Node<>(maxTop.value, maxTop);
        }
    }

    // --------------------------------------------------------
    // pop — O(1)
    // --------------------------------------------------------

    /**
     * Remove e retorna o elemento do topo.
     * As pilhas auxiliares são atualizadas sincronizadamente.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = top.value;
        top    = top.next;
        minTop = minTop.next;
        maxTop = maxTop.next;
        size--;
        return value;
    }

    // --------------------------------------------------------
    // peek — O(1)
    // --------------------------------------------------------

    /** Retorna o elemento do topo sem removê-lo. */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    // --------------------------------------------------------
    // min — O(1)
    // --------------------------------------------------------

    /**
     * Retorna o menor elemento presente na pilha.
     * NÃO remove o elemento. Complexidade: O(1).
     */
    public T min() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return minTop.value;
    }

    // --------------------------------------------------------
    // max — O(1)
    // --------------------------------------------------------

    /**
     * Retorna o maior elemento presente na pilha.
     * NÃO remove o elemento. Complexidade: O(1).
     */
    public T max() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return maxTop.value;
    }

    // --------------------------------------------------------
    // Utilitários
    // --------------------------------------------------------

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = top;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]  (topo -> base)");
        return sb.toString();
    }
}
