import java.util.Scanner;

public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite os números separados por vírgula:");
        String entrada = scanner.nextLine();
        
        String[] numerosStr = entrada.split(",");
        double[] numeros = new double[numerosStr.length];
        
        for (int i = 0; i < numerosStr.length; i++) {
            numeros[i] = Double.parseDouble(numerosStr[i].trim());
        }
        
        System.out.println("Escolha a operação (1-Soma, 2-Subtração):");
        int operacao = scanner.nextInt();
        
        double resultado = 0;
        switch (operacao) {
            case 1:
                resultado = somar(numeros);
                System.out.println("Resultado da soma: " + resultado);
                break;
            case 2:
                resultado = subtrair(numeros);
                System.out.println("Resultado da subtração: " + resultado);
                break;
            default:
                System.out.println("Operação inválida");
        }
        
        scanner.close();
    }
    
    private static double somar(double[] numeros) {
        double soma = 0;
        for (double num : numeros) {
            soma += num;
        }
        return soma;
    }
    
    private static double subtrair(double[] numeros) {
        if (numeros.length == 0) return 0;
        double subtracao = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            subtracao -= numeros[i];
        }
        return subtracao;
    }
}