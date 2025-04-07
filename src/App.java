public class App {
    
    public static void main(String[] args) {
        System.out.println("Selecione o programa a executar:");
        System.out.println("1 - Calculadora");
        System.out.println("2 - Formatador de Telefone");
        System.out.println("3 - Gerador de Formatos (JSON/XML/YAML)");
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcao = scanner.nextInt();
        
        switch (opcao) {
            case 1:
                CalculadoraBasica.main(args);
                break;
            case 2:
                FormatadorTelefone.main(args);
                break;
            case 3:
                GeradorFormatos.main(args);
                break;
            default:
                System.out.println("Opção inválida");
        }
        
        scanner.close();
    }
}

