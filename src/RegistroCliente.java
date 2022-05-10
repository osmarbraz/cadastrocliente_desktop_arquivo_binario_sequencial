
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Classe que realiza as operações de salvamento dos atributos cliente.
 *
 */
public class RegistroCliente extends Cliente {

    /**
     * Construtor sem parâmetros.
     */
    public RegistroCliente() {
        super();
    }

    /**
     * Construtor com parâmetros
     *
     * @param codigo Código de um cliente
     * @param nome Nome de um cliente
     * @param cpf CPF de um cliente
     */
    public RegistroCliente(int codigo, String nome, String cpf) {
        super(codigo, nome, cpf);
    }

    /**
     * Realiza a leitura dos dados do arquivo especificado.
     *
     * Preenche os atributos de cliente utilizado o arquivo especificado.
     *
     * @param arquivo Referência ao arquivo com os dados do cliente.
     * @throws IOException
     */
    public void leitura(RandomAccessFile arquivo) throws IOException {
        setCodigo(arquivo.readInt());
        //Retira os espaços da leitura da String com método trim
        setNome(montaPalavra(arquivo, 30).trim());
        //Retira os espaços da leitura da String com o método trim
        setCpf(montaPalavra(arquivo, 11).trim());
    }

    /**
     * Lê do arquivo caracter a caracter e monta uma string.
     *
     * @param arquivo Referência ao arquivo com os caracteres a serem lidos.
     * @return
     * @throws IOException
     */
    private String montaPalavra(RandomAccessFile arquivo, int tamanho) throws IOException {
        //Vetor de char da palavra a ser montada
        char palavra[] = new char[tamanho];
        char temp;
        // Recupera do arquivo 15 caracteres
        for (int i = 0; i < palavra.length; i++) {
            temp = arquivo.readChar();
            palavra[i] = temp;
        }
        return new String(palavra).replace('\0', ' ');
    }

    /**
     * Escreve os dados no arquivo especificado.
     *
     * Recuera os dados do cliente e escreve no arquivo especificado.
     *
     * @param arquivo Arquivo a ser gravado os dados.
     * @throws IOException
     */
    public void escrita(RandomAccessFile arquivo) throws IOException {
        arquivo.writeInt(getCodigo());
        // Escreve 15 caracteres do nome no arquivo
        escrevePalavra(arquivo, getNome(), 30);
        // Escreve 15 caracteres do cpf no arquivo
        escrevePalavra(arquivo, getCpf(), 11);
    }

    /**
     * Escreve a palavra no arquivo através de um Buffer de String.
     *
     * @param arquivo Arquivo a ser gravado os dados.
     * @param palavra Palavra a ser escrita no arquivo.
     * @param tamanho Tamanho da palavra a ser escrita no arquivo.
     * @throws IOException
     */
    private void escrevePalavra(RandomAccessFile arquivo, String palavra, int tamanho) throws IOException {
        StringBuffer buffer = null;
        if (palavra != null) {
            buffer = new StringBuffer(palavra);
        } else {
            buffer = new StringBuffer(tamanho);
        }
        // Tamanho da String a ser gravada
        buffer.setLength(tamanho);
        arquivo.writeChars(buffer.toString());
    }

    /**
     * Retorna o tamanho do registro de cliente.
     *
     * @return Um inteiro com o tamanho do registro do cliente.
     */
    public int getTamanhoRegistro() {
        // 4 código + 30 nome + 11 cpf
        // código int = 4 bytes
        // nome String = 30 caracteres = 1 caracter ocupada 2 bytes = 60 bytes
        // cpf String = 11 caracteres = 1 caracter ocupada 2 bytes = 22 bytes        
        //Total 106 bytes
        return (4 + (2 * 30) + (2 * 11));
    }
}
