
/**
 * Classe base de cliente.
 */
public class Cliente {

    private int codigo;
    private String nome;
    private String cpf;

    /**
     * Construtor sem parâmetro.
     */
    public Cliente() {
        this(0, "", "");
    }

    /**
     * Construtor com parâmetros.
     *
     * @param codigo Código de um cliente.
     * @param nome Nome de um cliente.
     * @param cpf CPF de um cliente.
     */
    public Cliente(int codigo, String nome, String cpf) {
        setCodigo(codigo);
        setNome(nome);
        setCpf(cpf);
    }

    // Get´s e Set´s
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna os dados de cliente em uma String.
     *
     * @return Uma String com os dados.
     */
    public String paraString() {
        return (getCodigo() + " -  " + getNome() + " - " + getCpf());
    }

    /**
     * Retorna os dados de cliente em uma String.
     *
     * @return Uma String com os dados.
     */
    @Override
    public String toString() {
        return ("Código: " + getCodigo() + " \\ Nome: " + getNome() + "\\ CPF: " + getCpf());
    }
}
