import java.util.Scanner;
import java.util.regex.*;

@SuppressWarnings("unused")
public class FormatadorTelefone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o número de telefone:");
        String entrada = scanner.nextLine();
        
        String resultado = formatarTelefone(entrada);
        System.out.println(resultado);
        
        scanner.close();
    }
    
    public static String formatarTelefone(String entrada) {
        String numeros = entrada.replaceAll("[^0-9]", "");
        
        if (numeros.length() == 8) {
            return numeros.replaceFirst("(\\d{4})(\\d{4})", "$1-$2") + " (Telefone Fixo)";
        } else if (numeros.length() == 9) {
            return numeros.replaceFirst("(\\d{5})(\\d{4})", "$1-$2") + " (Celular)";
        } else if (numeros.length() == 10) {
            return numeros.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1)$2-$3") + " (Telefone Fixo)";
        } else if (numeros.length() == 11) {
            return numeros.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3") + " (Celular)";
        } else {
            return "Entrada inválida: não corresponde a nenhum formato de telefone conhecido";
        }
    }
}