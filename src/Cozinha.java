import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Cozinha {

    static ArrayList<Prato> pratos = new ArrayList<>();
    static ArrayList<Ingrediente> ingredientes = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        //DECLARACAO DE VARIAVEIS

        //Variaveis de entrada
        int quantidadePratos;
        int quantidadeCozinheiros;
        int quantidadeIngredientes;

        //Variaveis auxiliares
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random();
        Prato novoPrato;
        Ingrediente novoIngrediente;
        Cozinheiro novoCozinheiro;
        String[] nomesPratos = {"Feijões", "Arroz com feijão", "Pizza", "Hambúrguer",
                "Pastel", "Macarrão", "Lasanha", "Sushi", "Bife à parmegiana", "Coxinha",
                "Strogonoff", "Churrasco", "Feijoada", "Tacos", "Empada", "Torta de frango",
                "Salada Caesar", "Escondidinho", "Frango à milanesa", "Sopa de legumes"};
        String[] nomeIngredientes = {"Massa", "Tomate", "Cenoura", "Alface", "Maionese", "Óleo", "Azeite", 
                                "Picanha", "Bife de Boi", "Coxa de Frango", "Asa de Frango", "Peixe", "Farinha", "Queijo", 
                                "Presunto", "Milho", "Ervilha", "Azeitona", "Chapignon", "Tempero", "Sal"};

        //Threads
        ArrayList<Cozinheiro> cozinheiros = new ArrayList<>(); /*array de tamanho dinamico com os cozinheiros,
                                                                *cada cozinheiro eh uma thread*/




        //ENTRADA

        System.out.print("Entre com o número de cozinheiros: ");
        quantidadeCozinheiros = leitor.nextInt();

        System.out.print("Entre com o número de pratos: ");
        quantidadePratos = leitor.nextInt();

        System.out.print("Entre com o número de ingredientes: ");
        quantidadeIngredientes = leitor.nextInt();

        long comeco = System.nanoTime();

        //Criando os cozinheiros
        for(int i = 1; i<=quantidadeCozinheiros; i++){

            novoCozinheiro = new Cozinheiro("RR");
            cozinheiros.add(novoCozinheiro);
        }

        //Preenchendo o array de pratos
        for(int i = 1; i <= quantidadePratos; i++){

            novoPrato = new Prato(nomesPratos[gerador.nextInt(nomesPratos.length)] + " (" +
                    i + ")", gerador.nextInt(11));
            pratos.add(novoPrato);
        }

        //Preenchendo o array de ingredientes
        for(int i = 1; i <= quantidadeIngredientes; i++){

            novoIngrediente = new Ingrediente(nomeIngredientes[gerador.nextInt(nomeIngredientes.length)] + " (" +
                    i + ")", gerador.nextInt(11));
            ingredientes.add(novoIngrediente);
        }

        //Iniciando os cozinheiros
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.start();
        }

        //Fazendo o join para que o tempo seja contado corretamente
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.join();
        }

        long fim = System.nanoTime();
        System.out.println("t: " + (double)(fim - comeco)/1000000000);
    }
}