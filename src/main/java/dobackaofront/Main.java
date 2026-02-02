package dobackaofront;

import dobackaofront.controller.BancoDeDados;
import dobackaofront.model.Item;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de farmacia!");

        BancoDeDados banco = new BancoDeDados();
        ArrayList<Item> itens = banco.ler();

        banco.editar(1, itens);

    }
}