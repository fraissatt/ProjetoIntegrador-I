package projetoIntegrador1;
import java.util.*;
public class CineABC {
private List<Filme> filmes;
private List<Sala> salas;
private List<Sessao> sessoes;
public CineABC() {
filmes = new ArrayList<>();
salas = new ArrayList<>();
sessoes = new ArrayList<>();
}
public void cadastrarFilme(String titulo, String genero) {
Filme filme = new Filme(titulo, genero);
filmes.add(filme);
System.out.println("--- Filme cadastrado com sucesso! ---");
}
public void cadastrarSala(int numeroSala) {
Sala sala = new Sala(numeroSala);
salas.add(sala);
System.out.println("--- Sala cadastrada com sucesso! ---");
}
public void cadastrarSessao(Filme filme, Sala sala, String dia, String hora) {
Sessao sessao = new Sessao(filme, sala, dia, hora);
sessoes.add(sessao);
sala.setSessao(sessao);
System.out.println("--- Sessão cadastrada com sucesso! ---");
}
public void venderIngresso(Filme filme, Sessao sessao, Poltrona poltrona, String
tipoIngresso) {
if (sessao == null) {
System.out.println("Sessão não encontrada. Verifique o filme e a sessão selecionados.");
return;
}
if (poltrona.isOcupada()) {
System.out.println("Poltrona já está ocupada. Por favor, selecione outra poltrona.");
return;
}
Ingresso ingresso = new Ingresso(filme, sessao, poltrona, tipoIngresso);
sessao.venderIngresso(ingresso);
System.out.println("--- Ingresso vendido com sucesso! ---");
}
public void imprimirIngresso(Ingresso ingresso) {
if (ingresso == null) {
System.out.println("Ingresso não encontrado. Verifique o número da poltrona.");
return;
}
System.out.println("======= Ingresso =======");
System.out.println("Filme: " + ingresso.getFilme().getTitulo());
System.out.println("Sessão: " + ingresso.getSessao().getNumeroSessao());
System.out.println("Sala: " + ingresso.getSessao().getSala().getNumeroSala());
System.out.println("Poltrona: " + ingresso.getPoltrona().getNumeroPoltrona());
System.out.println("Tipo: " + ingresso.getTipoIngresso());
}
public void listarFilmes() {
for (int i = 0; i < filmes.size(); i++) {
System.out.println((i + 1) + " - " + filmes.get(i).getTitulo());
listarSessoes(filmes.get(i));
}
}
public void listarSalas() {
for (int i = 0; i < salas.size(); i++) {
System.out.println((i + 1) + " - Sala " + salas.get(i).getNumeroSala());
}
}
public void listarSessoes(Filme filme) {
for (Sessao sessao : sessoes) {
if (sessao.getFilme().equals(filme)) {
System.out.println(" ");
System.out.println(" [" + sessao.getDia() + "] - (" + sessao.getHora() + ") - Sala (" +
sessao.getSala().getNumeroSala() + ") ");
System.out.println(" ");
}
}
}
public void exibirEstatisticas() {
Filme filmeMaisVendido = null;
int maiorVendas = 0;
Sessao sessaoMaiorOcupacao = null;
int maiorOcupacao = 0;
Sessao sessaoMenorOcupacao = null;
int menorOcupacao = Integer.MAX_VALUE;
for (Filme filme : filmes) {
int totalVendasFilme = 0;
for (Sessao sessao : sessoes) {
if (sessao.getFilme().equals(filme)) {
totalVendasFilme += sessao.getVendas();
int ocupacaoSessao = sessao.getOcupacao();
if (ocupacaoSessao > maiorOcupacao) {
maiorOcupacao = ocupacaoSessao;
sessaoMaiorOcupacao = sessao;
}
if (ocupacaoSessao < menorOcupacao) {
menorOcupacao = ocupacaoSessao;
sessaoMenorOcupacao = sessao;
}
}
}
if (totalVendasFilme > maiorVendas) {
maiorVendas = totalVendasFilme;
filmeMaisVendido = filme;
}
}
System.out.println("======= Estatísticas de Vendas =======");
System.out.println("Filme mais vendido: " + filmeMaisVendido.getTitulo());
System.out.println("Sessão com maior ocupação: " +
sessaoMaiorOcupacao.getNumeroSessao() + " - Sala " +
sessaoMaiorOcupacao.getSala().getNumeroSala());
System.out.println("Sessão com menor ocupação: " +
sessaoMenorOcupacao.getNumeroSessao() + " - Sala " +
sessaoMenorOcupacao.getSala().getNumeroSala());
}
public static void main(String[] args) {
CineABC cine = new CineABC();
Scanner scanner = new Scanner(System.in);
boolean sair = false;
while (!sair) {
System.out.println("======= Bem-vindo ao Cine ABC! =======");
System.out.println("Selecione uma opção:");
System.out.println("[1] - Entrar como funcionário");
System.out.println("[2] - Entrar como usuário");
System.out.println("[0] - Sair");
System.out.println("======================================");
int opcao = scanner.nextInt();
switch (opcao) {
case 1:
cine.menuFuncionario(scanner);
break;
case 2:
cine.menuUsuario(scanner);
break;
case 0:
sair = true;
break;
default:
System.out.println("Opção inválida. Por favor, selecione novamente.");
break;
}
}
System.out.println("=== Obrigado por utilizar o Cine ABC! ===");
scanner.close();
}
public void menuFuncionario(Scanner scanner) {
boolean sair = false;
while (!sair) {
System.out.println("=========== Funcionário ===========");
System.out.println("Selecione uma opção:");
System.out.println("1 - Cadastrar Filme");
System.out.println("2 - Cadastrar Sala");
System.out.println("3 - Cadastrar Sessão");
System.out.println("4 - Listar Filmes, Salas e Sessões");
System.out.println("5 - Exibir Estatísticas de Vendas");
System.out.println("0 - Sair");
System.out.println("===================================");
int opcao = scanner.nextInt();
scanner.nextLine();
switch (opcao) {
case 1:
System.out.println("======= Cadastrar Filme =======");
System.out.println("Digite o título do filme:");
String titulo = scanner.nextLine();
System.out.println("Digite o gênero do filme:");
String genero = scanner.nextLine();
cadastrarFilme(titulo, genero);
break;
case 2:
System.out.println("======= Cadastrar Sala =======");
System.out.println("Digite o número da sala:");
int numeroSala = scanner.nextInt();
cadastrarSala(numeroSala);
break;
case 3:
listarFilmes();
System.out.println("======= Cadastrar Sessão =======");
System.out.println("Digite o número do filme:");
int numeroFilme = scanner.nextInt();
listarSalas();
System.out.println("Digite o número da sala:");
int numeroSalaSessao = scanner.nextInt();
System.out.println("Digite o dia da sessão (Dia/Mês/Ano):");
String dia = scanner.next();
System.out.println("Digite a hora da sessão (Hora:Minuto):");
String hora = scanner.next();
Filme filme = filmes.get(numeroFilme - 1);
Sala sala = salas.get(numeroSalaSessao - 1);
cadastrarSessao(filme, sala, dia, hora);
break;
case 4:
System.out.println("======= Filmes | Salas | Sessões =======");
listarFilmes();
break;
case 5:
exibirEstatisticas();
break;
case 0:
sair = true;
break;
default:
System.out.println("Opção inválida. Por favor, selecione novamente.");
break;
}
}
}
public void menuUsuario(Scanner scanner) {
boolean sair = false;
while (!sair) {
System.out.println("======= Usuário =======");
System.out.println("Selecione uma opção:");
System.out.println("1 - Comprar Ingresso");
System.out.println("2 - Imprimir Ingresso");
System.out.println("0 - Sair");
System.out.println("=======---------=======");
System.out.println(" ");
int opcao = scanner.nextInt();
scanner.nextLine();
switch (opcao) {
case 1:
System.out.println("======= Comprar Ingresso =======");
listarFilmes();
System.out.println("Digite o número do filme:");
int numeroFilme = scanner.nextInt();
Filme filme = filmes.get(numeroFilme - 1);
listarSessoes(filme);
System.out.println("Digite o número da sessão:");
int numeroSessao = scanner.nextInt();
Sessao sessao = null;
for (Sessao s : sessoes) {
if (s.getFilme().equals(filme) && s.getNumeroSessao() == numeroSessao) {
sessao = s;
break;
}
}
if (sessao == null) {
System.out.println("Sessão não encontrada. Verifique o número da sessão.");
break;
}
sessao.exibirMapaPoltronas();
System.out.println("Digite o número da poltrona:");
int numeroPoltrona = scanner.nextInt();
Poltrona poltrona = sessao.getPoltrona(numeroPoltrona);
if (poltrona == null) {
System.out.println("Poltrona inválida. Verifique o número da poltrona.");
break;
}
System.out.println("---- Selecione o tipo de ingresso ----");
System.out.println("1 - Inteira (R$30)");
System.out.println("2 - Meia (R$15)");
int tipoIngresso = scanner.nextInt();
scanner.nextLine();
String tipoIngressoString = tipoIngresso == 1 ? "Inteira (R$30)" : "Meia (R$15)";
venderIngresso(filme, sessao, poltrona, tipoIngressoString);
break;
case 2:
System.out.println("======= Impressão do ingresso =======");
System.out.println("Digite o número da poltrona:");
int numeroPoltronaIngresso = scanner.nextInt();
System.out.println("=======-----------------------=======");
scanner.nextLine();
Ingresso ingresso = null;
for (Sessao s : sessoes) {
ingresso = s.getIngresso(numeroPoltronaIngresso);
if (ingresso != null) {
break;
}
}
imprimirIngresso(ingresso);
break;
case 0:
sair = true;
break;
default:
System.out.println("Opção inválida. Por favor, selecione novamente.");
break;
}
}
}
}
class Filme {
private String titulo;
private String genero;
public Filme(String titulo, String genero) {
this.titulo = titulo;
this.genero = genero;
}
public String getTitulo() {
return titulo;
}
public String getGenero() {
return genero;
}
}
class Sala {
private int numeroSala;
private Sessao sessao;
public Sala(int numeroSala) {
this.numeroSala = numeroSala;
}
public int getNumeroSala() {
return numeroSala;
}
public Sessao getSessao() {
return sessao;
}
public void setSessao(Sessao sessao) {
this.sessao = sessao;
}
}
class Sessao {
private static int contadorSessoes = 1;
private int numeroSessao;
private Filme filme;
private Sala sala;
private String dia;
private String hora;
private List<Poltrona> poltronas;
private List<Ingresso> ingressos;
public Sessao(Filme filme, Sala sala, String dia, String hora) {
this.numeroSessao = contadorSessoes++;
this.filme = filme;
this.sala = sala;
this.dia = dia;
this.hora = hora;
this.poltronas = new ArrayList<>();
this.ingressos = new ArrayList<>();
inicializarPoltronas();
}
private void inicializarPoltronas() {
for (int i = 0; i < 10; i++) {
for (int j = 0; j < 10; j++) {
Poltrona poltrona = new Poltrona(i + 1, j + 1);
poltronas.add(poltrona);
}
}
}
public int getNumeroSessao() {
return numeroSessao;
}
public Filme getFilme() {
return filme;
}
public Sala getSala() {
return sala;
}
public String getDia() {
return dia;
}
public String getHora() {
return hora;
}
public void venderIngresso(Ingresso ingresso) {
ingressos.add(ingresso);
Poltrona poltrona = ingresso.getPoltrona();
poltrona.ocuparPoltrona();
}
public int getVendas() {
return ingressos.size();
}
public int getOcupacao() {
int ocupadas = 0;
for (Poltrona poltrona : poltronas) {
if (poltrona.isOcupada()) {
ocupadas++;
}
}
return ocupadas;
}
public Poltrona getPoltrona(int numeroPoltrona) {
for (Poltrona poltrona : poltronas) {
if (poltrona.getNumeroPoltrona() == numeroPoltrona) {
return poltrona;
}
}
return null;
}
public Ingresso getIngresso(int numeroPoltrona) {
for (Ingresso ingresso : ingressos) {
if (ingresso.getPoltrona().getNumeroPoltrona() == numeroPoltrona) {
return ingresso;
}
}
return null;
}
public void exibirMapaPoltronas() {
System.out.println("Mapa de Poltronas (Da esquerda -> direita | cima -> baixo poltronas de 1 - 100):");
for (Poltrona poltrona : poltronas) {
if (poltrona.isOcupada()) {
System.out.print("X ");
} else {
System.out.print("O ");
}
if (poltrona.getNumeroColuna() == 10) {
System.out.println();
}
}
}
}
class Poltrona {
private int numeroFila;
private int numeroColuna;
private boolean ocupada;
public Poltrona(int numeroFila, int numeroColuna) {
this.numeroFila = numeroFila;
this.numeroColuna = numeroColuna;
this.ocupada = false;
}
public int getNumeroFila() {
return numeroFila;
}
public int getNumeroColuna() {
return numeroColuna;
}
public int getNumeroPoltrona() {
return (numeroFila - 1) * 10 + numeroColuna;
}
public boolean isOcupada() {
return ocupada;
}
public void ocuparPoltrona() {
ocupada = true;
}
}
class Ingresso {
private Filme filme;
private Sessao sessao;
private Poltrona poltrona;
private String tipoIngresso;
public Ingresso(Filme filme, Sessao sessao, Poltrona poltrona, String tipoIngresso) {
this.filme = filme;
this.sessao = sessao;
this.poltrona = poltrona;
this.tipoIngresso = tipoIngresso;
}
public Filme getFilme() {
return filme;
}
public Sessao getSessao() {
return sessao;
}
public Poltrona getPoltrona() {
return poltrona;
}
public String getTipoIngresso() {
return tipoIngresso;
}
}