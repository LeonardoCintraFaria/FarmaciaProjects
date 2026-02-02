package dobackaofront.controller;

import dobackaofront.model.Item;

import java.io.*;
import java.util.ArrayList;

public class BancoDeDados {

    private Item item;

    public BancoDeDados(){

    }

    public void cadastrar(Item item, boolean opcao) {

        try {
            //Da acesso ao aqrquivom e decide se apaga tudo ou adiciona no final
            OutputStream os = new FileOutputStream("medicamentos.txt", opcao);
            //Transforma texto em bystes para poder escrever no arquivo
            OutputStreamWriter osw = new OutputStreamWriter(os);
            //escreve os dados no arquivo so que mais rapido e com um "buffer"
            BufferedWriter bw = new BufferedWriter(osw);

            String linha = item.getNome()+ ", "+ item.getQuantidade()+", "+ item.getTipo();

            bw.write(linha);
            bw.newLine();

            bw.close();
            osw.close();
            os.close();

            System.out.println("O medicamento " + item.getNome() + "foi cadastrado com sucesso!");
        }catch (Exception e){
            System.out.println("Nao foi possivel cadastra o medicamento!");
            System.out.println(e);
        }

    }

    public void editar(int codigo, ArrayList<Item> itens){

        Item item = itens.get(codigo);

        itens.remove(codigo);
        item.setNome("Tilenol 200ml XPSKT2");
        item.setQuantidade( 300);
        item.setTipo(" Frasco de 200ml");

        itens.add(codigo,item);

        for (int i = 0; i < itens.size(); i++) {
            if (i == 0){
                cadastrar(itens.get(i), false);
            }else {
                cadastrar(itens.get(i), true);
            }
        }
    }

    public Item pesquisar(int codigo, ArrayList<Item> itens){
        try{
            Item item = itens.get(codigo);
            return item;
        }catch (Exception e){// Aqui criamos uma execao caso retorne null
            return null;
        }

    }
    public void excluir(int codigo, ArrayList<Item> itens){//O excluir nos normalmente podemos excluir pelo codigo do produto
        itens.remove(codigo);
        for (int i = 0; i < itens.size(); i++) {
            if (i == 0){
                cadastrar(itens.get(i), false);
            }else {
                cadastrar(itens.get(i), true);
            }

        }
    }
    public ArrayList<Item> ler() {
        try {
            // Abre e acessa o arquivo
            InputStream is = new FileInputStream("medicamentos.txt");
            //Transforma os dados do arquivo em texto legivel
            InputStreamReader isr = new InputStreamReader(is);
            //le uma linha inteira do arquivo
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine();
            ArrayList<String> linhas = new ArrayList<>();

            while (linha != null) {
                System.out.println(linha);
                linhas.add(linha);
                linha = br.readLine();
            }

            System.out.println("O arquivo medicamentos.txt foi lido com sucesso!");

            ArrayList<Item> itens = new ArrayList<>();
            Item item;

            String[] elementos = new String[3];

            for (int i = 0; i < linhas.size(); i++) {
                elementos = linhas.get(i).split(", ");
                int quantidade = Integer.parseInt(elementos[1]);
                item = new Item(elementos[0], quantidade, elementos[2]);
                itens.add(item);
            }

            System.out.println("Linhas convertidas em obejetos com sucesso!");
            return itens;

        } catch (Exception e) {
            System.out.println("Nao conseguiu ler o arquivo!");
            return null;
        }

    }
}
//Integer vai ser o ajudante onde é possivel fazer diversas coisas
//O parseInt vai transformar o texto em um inteiro