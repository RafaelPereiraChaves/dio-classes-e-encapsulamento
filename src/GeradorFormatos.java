import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorFormatos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Map<String, String>> dados = new ArrayList<>();
        
        System.out.println("Digite os dados no formato NOME_CAMPO;VALOR;TIPO (ou 'sair' para terminar):");
        
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("sair")) break;
            
            String[] partes = entrada.split(";");
            if (partes.length != 3) {
                System.out.println("Formato inválido. Use NOME_CAMPO;VALOR;TIPO");
                continue;
            }
            
            Map<String, String> campo = new HashMap<>();
            campo.put("nome", partes[0].trim());
            campo.put("valor", partes[1].trim());
            campo.put("tipo", partes[2].trim().toLowerCase());
            
            dados.add(campo);
        }
        
        System.out.println("\nJSON:");
        System.out.println(gerarJson(dados));
        
        System.out.println("\nXML:");
        System.out.println(gerarXml(dados));
        
        System.out.println("\nYAML:");
        System.out.println(gerarYaml(dados));
        
        scanner.close();
    }
    
    private static String gerarJson(List<Map<String, String>> dados) {
        StringBuilder json = new StringBuilder("{\n");
        
        for (Map<String, String> campo : dados) {
            json.append("  \"").append(campo.get("nome")).append("\": ");
            
            switch (campo.get("tipo")) {
                case "texto":
                    json.append("\"").append(campo.get("valor")).append("\"");
                    break;
                case "numero inteiro":
                    json.append(campo.get("valor"));
                    break;
                case "numero com pontos flutuantes":
                    json.append(campo.get("valor"));
                    break;
                case "boleanos":
                    json.append(campo.get("valor").toLowerCase());
                    break;
                case "datas":
                    json.append("\"").append(formatarDataJson(campo.get("valor"))).append("\"");
                    break;
                default:
                    json.append("\"").append(campo.get("valor")).append("\"");
            }
            
            json.append(",\n");
        }
        
        if (json.length() > 2) {
            json.delete(json.length()-2, json.length());
        }
        
        json.append("\n}");
        return json.toString();
    }
    
    private static String gerarXml(List<Map<String, String>> dados) {
        StringBuilder xml = new StringBuilder("<dados>\n");
        
        for (Map<String, String> campo : dados) {
            xml.append("  <").append(campo.get("nome")).append(" tipo=\"").append(campo.get("tipo")).append("\">")
               .append(campo.get("valor"))
               .append("</").append(campo.get("nome")).append(">\n");
        }
        
        xml.append("</dados>");
        return xml.toString();
    }
    
    private static String gerarYaml(List<Map<String, String>> dados) {
        StringBuilder yaml = new StringBuilder();
        
        for (Map<String, String> campo : dados) {
            yaml.append(campo.get("nome")).append(": ");
            
            switch (campo.get("tipo")) {
                case "texto":
                case "datas":
                    yaml.append("\"").append(campo.get("valor")).append("\"");
                    break;
                default:
                    yaml.append(campo.get("valor"));
            }
            
            yaml.append("\n");
        }
        
        return yaml.toString();
    }
    
    private static String formatarDataJson(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(data);
            SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
            return out.format(date);
        } catch (Exception e) {
            return data;
        }
    }
}