package com.project.gabriel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

        public static void main(String[] args) {
            // 3.1 Inserir todos os funcionários
            List<Funcionario> funcionarios = new ArrayList<>();
            funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
            funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
            funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
            funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
            funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Diretor"));
            funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Diretor"));
            funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Diretor"));
            funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Diretor"));
            funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Diretor"));
            funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Diretor"));

            // 3.2 Remover o funcionário "João" da lista
            funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

            // 3.3 Imprimir todos os funcionários com informações formatadas
            System.out.println("Funcionários:");
            funcionarios.forEach(funcionario -> System.out.println(formatarFuncionario(funcionario)));

            // 3.4 Aplicar aumento de 10% nos salários
            System.out.println("\nFuncionários e seus novos salários após aumento de 10%:");
            funcionarios.forEach(funcionario -> {
                BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
                funcionario.setSalario(novoSalario);
                novoSalario = novoSalario.setScale(2, BigDecimal.ROUND_DOWN);
                System.out.println(funcionario.getNome() + ": " + novoSalario);
            });

            // 3.5 Agrupar funcionários por função em um MAP
            Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                    .collect(Collectors.groupingBy(Funcionario::getFuncao));

            // 3.6 Imprimir os funcionários agrupados por função
            System.out.println("\nFuncionários agrupados por função:");
            funcionariosPorFuncao.forEach((funcao, lista) -> {
                System.out.println(funcao + ":");
                lista.forEach(f -> System.out.println("  " + formatarFuncionario(f)));
            });

            // 3.8 Imprimir funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12)
            List<Funcionario> aniversariantes = funcionarios.stream()
                    .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                            funcionario.getDataNascimento().getMonthValue() == 12)
                    .collect(Collectors.toList());
            System.out.println("\nFuncionários que fazem aniversário em outubro ou dezembro:");
            aniversariantes.forEach(funcionario -> System.out.println(formatarFuncionario(funcionario)));

            // 3.9 Encontrar o funcionário com maior idade
            //Não desenvolvido

            // 3.10 Ordenar funcionários por ordem alfabética
            funcionarios.sort(Comparator.comparing(Funcionario::getNome));
            System.out.println("\nFuncionários em ordem alfabética:");
            funcionarios.forEach(funcionario -> System.out.println(formatarFuncionario(funcionario)));

            // 3.11 Imprimir o total dos salários dos funcionários
            BigDecimal totalSalarios = funcionarios.stream()
                    .map(Funcionario::getSalario)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);

            // 3.12 Imprimir quantos salários mínimos ganha cada funcionário (considerando salário mínimo de R$1212.00)
            System.out.println("\nSalários mínimos que cada funcionário ganha:");
            funcionarios.forEach(funcionario -> {
                BigDecimal salarioMinimo = new BigDecimal("1212.00");
                int salariosMinimos = funcionario.getSalario().divide(salarioMinimo, BigDecimal.ROUND_DOWN).intValue();
                System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
            });
        }
        //Método para Formatação para Impressão da Lista de Funcionários
        private static String formatarFuncionario(Funcionario funcionario) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = funcionario.getDataNascimento().format(formatter);
            return "Nome: " + funcionario.getNome() + ", Data de Nascimento: " + dataFormatada +
                    ", Salário: " + funcionario.getSalario().toString().replace(".", ",") +
                    ", Função: " + funcionario.getFuncao();
        }

        private static long calcularIdade(LocalDate dataNascimento) {
            LocalDate hoje = LocalDate.now();
            return dataNascimento.until(hoje).getYears();
        }
    }

