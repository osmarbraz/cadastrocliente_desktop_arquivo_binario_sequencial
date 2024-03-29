
/**
 * Programa que armazena um registro de tamanho fixo em um arquivo binário.
 *
 * Realiza as operações de inclusão, exclusão(lógica e física), alteração, pesquisa, posição e listagem dos registros.
 *
 */
import javax.swing.JOptionPane;

public class Principal {

    /**
     * Realiza o preenchimento de um objeto do tipo cliente.
     *
     * @param mensagem Mensagem inícia antes da leitura.
     * @return Um objeto preenchido.
     */
    public static RegistroCliente leitura(String mensagem) {
        //Mostra a mensagem se ela for diferente de vazio
        if (!mensagem.equals("")) {
            JOptionPane.showMessageDialog(null, mensagem);
        }
        //Instancia o cliente a ser preenchido
        RegistroCliente cliente = new RegistroCliente();
        //Preenche o cliente com os dados lidos
        cliente.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o Código")));
        cliente.setNome(JOptionPane.showInputDialog("Digite o Nome"));
        cliente.setCpf(JOptionPane.showInputDialog("Digite o CPF"));
        //Retorna o cliente preenchido
        return cliente;
    }

    /**
     * Programa principal
     */
    public static void main(String Arg[]) {
        //Classe que gerencia o arquivo de cliente
        GerenciadorCliente gerente = new GerenciadorCliente();
        //Recebe a opção do menu
        int opcao = -1;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("\t### Arquivo Binário Sequencial ###\n"
                    + " 1 - Incluir \n "
                    + " 2 - Incluir com verificação \n "
                    + " 3 - Atualizar \n "
                    + " 4 - Excluir Lógico\n "
                    + " 5 - Excluir Físico\n "
                    + " 6 - Pesquisar Chave\n "
                    + " 7 - Pesquisar Posição\n "
                    + " 8 - Pesquisar Nome\n "
                    + " 9 - Listar Lógico\n "
                    + "10 - Listar Físico \n "
                    + "11 - Informações \n "
                    + "12 - Zera Arquivo \n "
                    + "99 - Sair\n"
                    + "Digite uma Opção: "));

            switch (opcao) {
                case 1: {
                    //Chama o método leitura para retornar um cliente instanciado e preenchido
                    RegistroCliente cliente = leitura("");
                    if (gerente.inserirFimArquivo(cliente) == true) {
                        JOptionPane.showMessageDialog(null, "Registro inserido com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro não foi inserido.");
                    }
                    break;
                }
                case 2: {
                    //Chama o método leitura para retornar um cliente instanciado e preenchido
                    RegistroCliente cliente = leitura("Cadastro de cliente com verificação do código");

                    // Procura a posição do registro com o código no arquivo
                    int posicao = gerente.posicaoRegistro(cliente.getCodigo());

                    //Enquanto não for localizado(posição != -1) e o usuário desejar proseguir
                    while (posicao != -1 && cliente.getCodigo() != -2) {
                        cliente.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Este código já existe digite um novo código para o cliente ou -2 para sair:")));
                        // Procura a posição do registro com o código no arquivo
                        posicao = gerente.posicaoRegistro(cliente.getCodigo());
                    }
                    //Se código diferente de -2 realiza a a inclusão
                    if (cliente.getCodigo() != -2) {
                        if (gerente.inserirFimArquivo(cliente) == true) {
                            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Registro não foi inserido.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro não foi inserido.");
                    }
                    break;
                }
                case 3: {
                    //Pergunta qual o código a ser atualizado
                    int codigoAtualizar = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser atualizado: "));
                    //Instancia um novo cliente com os novos dados
                    RegistroCliente cliente = leitura("Digite os dados do novo cliente");
                    //Atualiza o arquivo cliente com os dados para o código especificado
                    if (gerente.atualizarArquivo(codigoAtualizar, cliente) == true) {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoAtualizar + " atualizado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoAtualizar + " não foi atualizado.");
                    }
                    break;
                }
                case 4: {
                    //Pergunta qual o código a ser excluído logicamente
                    int codigoExcluir = Integer.parseInt(JOptionPane.showInputDialog(" Digite o codigo a ser excluído: "));
                    //Excluí o registro com o código especificado                    
                    if (gerente.excluirLogico(codigoExcluir) == true) {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoExcluir + " excluído com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoExcluir + " não foi excluído.");
                    }
                    break;
                }
                case 5: {
                    //Pergunta qual o código a ser excluído fisicamente
                    int codigoExcluir = Integer.parseInt(JOptionPane.showInputDialog(" Digite o codigo a ser excluído: "));
                    //Excluí o registro com o código especificado                    
                    if (gerente.excluirFisico(codigoExcluir) == true) {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoExcluir + " excluído com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro " + codigoExcluir + " não foi excluído.");
                    }
                    break;
                }
                case 6: {
                    // Pergunta qual a chave do cliente deve ser procurada no arquivo
                    int chave = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser perquisado: "));
                    // Procura o registro do cliente com a chave no arquivo
                    RegistroCliente cliente = gerente.pesquisarRegistro(chave);
                    // Se cliente != null encontrou o registro
                    if (cliente != null) {
                        JOptionPane.showMessageDialog(null, "Achei o registro \n" + cliente.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não Achei o registro com a chave " + chave);
                    }
                    break;
                }
                case 7: {
                    // Pergunta qual a chave do cliente deve ser procurada sua posição no arquivo
                    int chave = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código ser procurado a posição: "));
                    // Procura a posição do registro com chave no arquivo
                    int posicao = gerente.posicaoRegistro(chave);
                    //Se posição é -1 não encontrou
                    if (posicao != -1) {
                        JOptionPane.showMessageDialog(null, "Achei o registro na posição " + (posicao + 1));
                    } else {
                        JOptionPane.showMessageDialog(null, "Não Achei o registro com a chave " + chave);
                    }
                    break;
                }
                case 8: {
                    // Pergunta qual o nome do cliente deve ser procurada no arquivo
                    String chave = JOptionPane.showInputDialog(" Digite o nome a ser perquisado: ");
                    // Procura o registro do cliente com a chave no arquivo
                    RegistroCliente cliente = gerente.pesquisarRegistroNome(chave);
                    // Se cliente != null encontrou o registro
                    if (cliente != null) {
                        JOptionPane.showMessageDialog(null, "Achei o registro \n" + cliente.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não Achei o registro com a chave " + chave);
                    }
                    break;
                }
                case 9: {
                    //Lista logicamente os dados do arquivo. Não inclui chave com -1                    
                    String saida = gerente.listarLogico();
                    JOptionPane.showMessageDialog(null, "Lista Lógico:\n" + saida);
                    break;
                }
                case 10: {
                    //Lista fisicamente os dados do arquivo
                    String saida = gerente.listarFisico();
                    JOptionPane.showMessageDialog(null, "Lista Físico:\n" + saida);
                    break;
                }
                case 11: {
                    //Retorna as informações do arquivo
                    String informacoes = gerente.informacoes();
                    JOptionPane.showMessageDialog(null, informacoes);
                    break;
                }
                case 12: {
                    //Esvazia o arquivo de dados
                    if (gerente.zeraArquivo() == true) {
                        JOptionPane.showMessageDialog(null, "Arquivo zerado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Arquivo não foi zerado!");
                    }
                    break;
                }
                case 99: {
                    System.out.println("Saindo do Sistema!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } while (opcao != 99);
    }
}
