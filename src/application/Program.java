package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),Integer.parseInt(fields[1]),fields[2],Integer.parseInt(fields[3]),Double.parseDouble(fields[4])));
                line = br.readLine();
            }

            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio ");

            List<Sale> pm = list.stream()
                    .filter(x -> x.getYear().equals(2016))
                    .sorted(Comparator.comparing(Sale::averagePrice).reversed())
                    .limit(5)
                    .collect(Collectors.toList());

            pm.forEach(System.out::println);

            double sum = list.stream()
                    .filter(x -> x.getSeller().equals("Logan"))
                    .filter(x -> x.getMonth().equals(1) || x.getMonth().equals(7))
                    .mapToDouble(Sale::getTotal)
                    .sum();
            System.out.println("Total vendido pelo Logan nos meses 1 e 7: " + sum);

        } catch (IOException e) {

            System.out.println("Erro: c:\\temp\\in.txt (O sistema não pode encontrar o arquivo especificado)");
        }


        sc.close();
    }
}
